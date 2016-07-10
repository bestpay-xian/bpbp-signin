<%@ page import="com.bestpay.bpbp.signin.dal.models.Employee" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
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
    <script src="<%=path%>/resources/js/jquery.js" type="text/javascript"></script>
    <script src="<%=path%>/resources/js/signin.js" type="text/javascript"></script>
</head>


<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
    </ul>
</div>

<div class="mainindex">


    <div class="welinfo">
        <h5 class="indexTitle" align="center">欢迎使用签到系统</h5>
        <div class="xline"></div>
        <div class="welinfo">
            <span><img src="<%=path%>/resources/images/dp.png" alt="提醒" /></span>
            <b>签到系统使用指南</b>
        </div>
        <ul class="infolist">
            <li><span>◆ 本系统为签到管理系统</span></li>
            <li><span>◆ 普通用户可进行签到、签退操作</span></li>
            <li><span>◆ 可查询本人综合评定信息</span></li>
            <li><span>◆ 点击用户管理，可对自己的密码进行修改</span></li>
            <li><span>◆ 点击个人考勤，可进行当天签到，第一次点击为签到，第二次点击为签退。</span></li>
            <li><span>◆ 在个人签到页面，点击查询个人考评，可进行个人的考评记录查询。</span></li>
        </ul>

    </div>
</div>

</body>

</html>
