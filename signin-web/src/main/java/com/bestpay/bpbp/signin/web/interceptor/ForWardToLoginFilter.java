package com.bestpay.bpbp.signin.web.interceptor;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LENOVO on 2016/7/18.
 */
public class ForWardToLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse= (HttpServletResponse) response;
        String username=httpServletRequest.getParameter("username");
        String password=httpServletRequest.getParameter("password");

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            httpServletRequest.getRequestDispatcher("/page/login/login.jsp").forward(httpServletRequest, httpServletResponse);
        }else{
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
