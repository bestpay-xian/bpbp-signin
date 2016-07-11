<%@ page import="com.bestpay.bpbp.signin.dal.models.Employee" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    Employee employee = (Employee) session.getAttribute("employee");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/resources/css/select.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery.js"></script>
    <script type="text/javascript">
        //操作确认函数
        function firm(title) {//利用对话框返回的值（true 或者 false）
            if (confirm("确定要提的操作吗？")) {//如果选择是，返回true ，那么就把页面转向指定链接
                return true;
            }
            else {//否则返回false
                return false;
            }
        }
    </script>

</head>

<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">员工签到</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div id="tab2" class="tabson">
            <form action="<%=path%>/record/insertRecord.do" method="post">
                <table class="signinlist">
                    <thead>
                    <tr style="height: 20px;">
                        <th colspan="5" style="color: red;font-size: 24px;">
                            上班签到
                        </th>
                    </tr>
                    <tr>
                        <th>员工编号</th>
                        <th>员工姓名</th>
                        <th>所属公司</th>
                        <th>签到/签退</th>
                        <th>注意事项</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><%=employee.getEmployeeId()%>
                        </td>
                        <td><%=employee.getName()%>
                        </td>
                        <td>
                            <c:if test="<%=employee.getPlatformId() == 0 %>">
                                <span>超级管理员</span>
                            </c:if>
                            <c:if test="<%=employee.getPlatformId() == 1 %>">
                                <span>华腾</span>
                            </c:if>
                        </td>
                        <td><input type="submit" class="signinbut" value="签到/签退" onclick="return firm();"/></td>
                        <td style="color: red;">请注意，第一次为签到，第二次为签退</td>
                    </tr>
                    </tbody>
                </table>
                <%--<form action="<%=path%>/record/insertRecord.do" method="post">--%>
                <%--<input type="submit" class="btn" value="签到" onclick="return firm();"/>--%>
            </form>
        </div>
    </div>
</div>
</body>
</html>
