package com.bestpay.bpbp.signin.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CheckLoginFilter
 * Author Liyang
 * Version 1.0
 * Date 2016/5/19 10:42
 */
public class CheckLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 判断用户是否登录，session是否存在employee
     * 系统直接访问 *.jsp返回登录界面
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURI();
        Object employee = req.getSession().getAttribute("employee");
        if (employee == null) {
            //不能包含登录页
            if (url != null && !url.equals("") && url.indexOf(".") != -1
                    && url.indexOf("/login.do") == -1 && url.indexOf("/logout.do") == -1 ){ //|| url.endsWith(".jsp")) {
                res.sendRedirect(req.getContextPath() + "/logout.do");
                return;
            }
        }
        chain.doFilter(request, response);
        return;
    }

    @Override
    public void destroy() {

    }

}
