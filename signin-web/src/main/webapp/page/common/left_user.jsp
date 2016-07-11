﻿<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="<%=path%>/resources/js/jquery.js" language="JavaScript"></script>

    <script type="text/javascript">
        $(function () {
            //导航切换
            $(".menuson .header").click(function () {
                var $parent = $(this).parent();
                $(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();

                $parent.addClass("active");
                if (!!$(this).next('.sub-menus').size()) {
                    if ($parent.hasClass("open")) {
                        $parent.removeClass("open").find('.sub-menus').hide();
                    } else {
                        $parent.addClass("open").find('.sub-menus').show();
                    }
                }
            });

            // 三级菜单点击
            $('.sub-menus li').click(function (e) {
                $(".sub-menus li.active").removeClass("active")
                $(this).addClass("active");
            });

            $('.title').click(function () {
                var $ul = $(this).next('ul');
                $('dd').find('.menuson').slideUp();
                if ($ul.is(':visible')) {
                    $(this).next('.menuson').slideUp();
                } else {
                    $(this).next('.menuson').slideDown();
                }
            });
        })
    </script>
</head>

<body style="background:#fff3e1;">
<div class="lefttop"><span></span>考勤系统</div>

<dl class="leftmenu">
    <dd>
        <div class="title">
            <span><img src="<%=path%>/resources/images/leftico01.png"/></span>
            <a href="<%=path%>/signin.do" target="rightFrame">员工签到</a>
        </div>

        <div class="title">
            <span><img src="<%=path%>/resources/images/leftico01.png"/></span>签到管理
        </div>
        <ul class="menuson">
            <li>
                <div class="header">
                    <cite></cite>
                    <a href="<%=path%>/record/recordFromList.do" target="rightFrame">签到查询</a>
                </div>
            </li>
        </ul>

        <div class="title">
            <span><img src="<%=path%>/resources/images/leftico01.png"/></span>
            <a href="<%=path%>/comm/fromList.do" target="rightFrame">通讯录</a>
        </div>
    </dd>
</dl>
</body>
</html>
