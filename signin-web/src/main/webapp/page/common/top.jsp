<%@ page import="com.bestpay.bpbp.signin.dal.models.Employee" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    Employee employee = (Employee)session.getAttribute("employee");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="<%=path%>/resources/js/jquery.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/signin.js" type="text/javascript"></script>
    <script src="<%=path%>/resources/js/jquery-1.9.1.min.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/jquery.validate.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/messages_cn.js" language="JavaScript"></script>
    <script type="text/javascript">
        $(function () {
            //顶部导航切换
            $(".nav li a").click(function () {
                $(".nav li a.selected").removeClass("selected")
                $(this).addClass("selected");
            })
        })
    </script>


</head>

<body style="background:url(<%=path%>/resources/images/topbg.gif) repeat-x;">

<div class="topleft">
    <a href="main.html" target="_parent"><img src="<%=path%>/resources/images/logo.png" title="系统首页"/></a>
</div>

<div class="topright">
    <ul>
        <li><span><img src="<%=path%>/resources/images/help.png" title="帮助" class="helpimg"/></span><a href="<%=path%>/page/common/index.jsp" target="rightFrame">帮助</a></li>
        <li><a href="<%=path%>/page/user/update_password.jsp" target="rightFrame">修改密码</a></li>
        <li><a href="<%=path %>/logout.do" target="_parent">退出</a></li>
    </ul>
    <div class="user">
        <span><%= employee.getName()%></span>
    </div>
</div>

<div class="systime">
    <span><img src="<%=path%>/resources/images/time.png" alt="时间"/></span>
    <i><strong><span id="time">现在时间：<script language="javascript"> showtime();</script></span></strong></i>
</div>

</body>
</html>
