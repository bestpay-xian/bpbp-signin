﻿<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>甜橙金融考勤系统</title>
</head>
<frameset rows="88,*,31" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="top.html" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame"/>
    <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
        <frame src="left_user.html" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame"/>
        <frame src="index.html" name="rightFrame" id="rightFrame" title="rightFrame"/>
    </frameset>
    <frame src="footer.html" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame"
           title="bottomFrame"/>
</frameset>
<noframes>
    <body>
    浏览器不支持frame框架
    </body>
</noframes>
</html>
