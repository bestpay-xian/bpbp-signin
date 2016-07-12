<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>甜橙金融考勤系统</title>
    <link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="<%=path%>/resources/js/jquery-1.9.1.min.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/jquery.validate.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/messages_cn.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/cloud.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/MD5.js"></script>
    <script>
        $(function(){
            $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            $(window).resize(function () {
                $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            });
            $("#loading").click(function(){
                $("#password").val(MD5($(".loginpwd").val()));
            });

        });
        //页面进行各种验证
        $("#formId").validate({
            rules: {
                username: {
                    required: true,

                    password: {
                        required: true
                    }
                },
                messages: {
                    username: {
                        required: "必填"
                    },
                    password: {
                        required: "必填"
                    },
                    submitHandler: function (form) {   //表单提交句柄,为一回调函数，带一个参数：form
                        form.submit();   //提交表单
                    }
                }
            }
        });
    </script>
</head>

<body style="background-color:#df7611; background-image:url(<%=path%>/resources/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>

<div class="logintop">
    <span>甜橙金融考勤系统</span>
    <ul>
        <li><a href="#">回首页</a></li>
    </ul>
</div>

<div class="loginbody">

    <span class="systemlogo"></span>
    <form id="formId" method="post" action="<%=path%>/login.do">
        <input name="password" type="hidden" id="password" />
        <div class="loginbox">

            <ul>
                <li><input name="username" value="请输入邮箱" onclick="JavaScript:this.value=''" required="true" type="text" class="loginuser" /></li>
                <li><input type="password" required="true" class="loginpwd" /></li>
                <li style="color: red">${message}</li>
                <li>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input id="loading" type="submit" class="loginbtn" value="登录" />
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input id="" type="reset" class="loginbtn" value="重置" />
                </li>
            </ul>
        </div>
    </form>
</div>


<div class="loginbm">版权所有 2016 甜橙金融签到系统</div>


</body>

</html>
