package com.bestpay.bpbp.signin.dal.interceptor;
import com.bestpay.bpbp.signin.common.PageParameter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 分页拦截器
 *
 * Author: zhaoss
 * Time: 2015-07-15.
 */
@Slf4j
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class QueryPageInterceptor implements Interceptor {
    /**
     * 需要拦截的ID(正则匹配)
     */
    private final static String defaultDatabase  = "oracle";
    /**
     * 需要拦截的ID(正则匹配)
     */
    private static String       defaultPageSqlId = ".*Page$";
    /**
     * 需要拦截的ID(正则匹配)
     */
    private String              sqlmapId         = "";
    /**
     * 默认是oracle
     */
    private String              databaseType;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler,
            "delegate");
        MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate,
            "mappedStatement");

        // 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的MappedStatement的sql
        if (mappedStatement.getId().matches(sqlmapId)) {
            BoundSql boundSql = delegate.getBoundSql();
            Object parameterObject = boundSql.getParameterObject();
            if (parameterObject == null) {
                throw new NullPointerException("parameterObject is null!");
            } else {
                PageParameter page = (PageParameter) PropertyUtils.getProperty(parameterObject,
                    "page");
                String sql = boundSql.getSql();
                // 重设分页参数里的总页数等
                Connection connection = (Connection) invocation.getArgs()[0];
                setPageParameter(sql, connection, mappedStatement, boundSql, page);

                // 重写sql
                String pageSql = buildPageSql(sql, page);
                ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
                // 采用物理分页后，就不需要mybatis的内存分页了
                RowBounds rowBounds = (RowBounds) ReflectUtil.getFieldValue(delegate, "rowBounds");
                ReflectUtil.setFieldValue(rowBounds, "offset", 0);

            }
        }
        // 将执行权交给下一个拦截器
        return invocation.proceed();
    }

    /**
     * 从数据库里查询总的记录数并计算总页数，回写进分页参数<code>PageParameter</code>,这样调用者就可用通过 分页参数
     * <code>PageParameter</code>获得相关信息。
     *
     * @param sql             sql
     * @param connection      数据库连接对象
     * @param mappedStatement mappedStatement
     * @param boundSql        boundSql
     * @param page            分页信息
     */
    private void setPageParameter(String sql, Connection connection,
                                  MappedStatement mappedStatement, BoundSql boundSql,
                                  PageParameter page) {
        // 记录总记录数
        String reg = "(?i)order\\s*(?i)BY(\\s*.*)*";
        String countSql = "select count(0)  as total from (" + sql.replaceAll(reg, "") + ")";
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            countStmt = connection.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
                boundSql.getParameterMappings(), boundSql.getParameterObject());
            //给countBs中的参数metaParameters赋值；解决因物理分页，setParameters时，metaObject.getValue(propertyName)报的空指针异常的问题
            Object metaParamsField = ReflectUtil.getFieldValue(boundSql, "metaParameters");
            if (metaParamsField != null) {
                MetaObject mo = (MetaObject) ReflectUtil.getFieldValue(boundSql, "metaParameters");
                ReflectUtil.setFieldValue(countBS, "metaParameters", mo);
            }
            setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
            rs = countStmt.executeQuery();
            int totalCount = 0;
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
            page.setTotalCount(totalCount);
            long totalPage = totalCount / page.getPageSize()
                             + ((totalCount % page.getPageSize() == 0) ? 0 : 1);
            //page.setTotalPage(totalPage);

        } catch (SQLException e) {
            log.error("处理异常:{}", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                log.error("处理异常:{}", e);
            }
            try {
                if (countStmt != null) {
                    countStmt.close();
                }
            } catch (SQLException e) {
                log.error("处理异常:{}", e);
            }
        }

    }

    /**
     * 对SQL参数(?)设值
     *
     * @param ps              ps
     * @param mappedStatement mappedStatement
     * @param boundSql        boundSql
     * @param parameterObject 参数
     * @throws java.sql.SQLException
     */
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement,
                               BoundSql boundSql, Object parameterObject) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement,
            parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }

    /**
     * 根据数据库类型，生成特定的分页sql
     *
     * @param sql  sql脚本
     * @param page 分页信息
     * @return String
     */
    private String buildPageSql(String sql, PageParameter page) {
        StringBuilder pageSql = new StringBuilder();
        if ("oracle".equals(databaseType)) {
            pageSql = buildPageSqlForOracle(sql, page);
        } else {
            throw new RuntimeException("目前只支持oracle数据分页");
        }
        return pageSql.toString();
    }

    /**
     * 参考hibernate的实现完成oracle的分页
     *
     * @param sql  sql脚本
     * @param page 分页信息
     * @return String
     */
    public StringBuilder buildPageSqlForOracle(String sql, PageParameter page) {
        StringBuilder pageSql = new StringBuilder(100);
        long beginrow = (page.getCurrentPage() - 1) * page.getPageSize();
        long endrow = page.getCurrentPage() * page.getPageSize();

        pageSql.append("select * from ( select row_.*, rownum rownum_ from ( ");
        pageSql.append(sql);
        pageSql.append(" ) row_ where rownum <= " + endrow + ") where rownum_ > " + beginrow);
        return pageSql;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        sqlmapId = properties.getProperty("sqlmapId");
        if (StringUtils.isEmpty(sqlmapId)) {
            throw new RuntimeException("sqlmapId property is not found!");
        }
        databaseType = properties.getProperty("databaseType");
        if (StringUtils.isEmpty(databaseType)) {
            databaseType = defaultDatabase;
        }
    }
}
