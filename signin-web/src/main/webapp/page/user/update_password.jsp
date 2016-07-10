<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="<%=path%>/resources/js/jquery-1.9.1.min.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/jquery.validate.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/messages_cn.js" language="JavaScript"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/MD5.js"></script>

    <script type="text/javascript">
        function passwordFocus() {
            var password=document.getElementById("user_password");
            password.innerHTML = "<font color='red'>*密码由大小字母、数字组成，长度为6－20字符</font>";
        }
        function passwordBlur(){
            var password = document.getElementById("uppassword");
            var passwordId = document.getElementById("user_password");
            passwordId.innerHTML = "";
            var reg = /^[a-zA-Z0-9]{6,20}$/;
            if(password.value==""){
                passwordId.innerHTML = "<font color='red'>*密码必填项,请输入密码</font>";
                return false;
            }
            if(reg.test(password.value)==false){
                passwordId.innerHTML = "<font color='red'>*密码格式不正确，请重新输入</font>";
                return false;
            }
            return true;
        }

        function repasswordFocus(){
            var repassword = document.getElementById("user_repassword");
            repassword.innerHTML = "<font color='red'>*请再次输入密码</font>"
        }
        function repasswordBlur(){
            var repassword = document.getElementById("repassword");
            var password = document.getElementById("uppassword");
            var repasswordId = document.getElementById("user_repassword");
            repasswordId.innerHTML = "";
            if(repassword.value==""){
                repasswordId.innerHTML = "<font color='red'>*请再次输入您的密码</font>";
                return false;
            }
            if(repassword.value!=password.value){
                repasswordId.innerHTML = "<font color='red'>*两次输入密码不一致，请重新输入</font>";
                return false;
            }
            return true;
        }
        $(function(){

            $("#save").click(function(){
                $("#password").val(MD5($("#repassword").val()));
                var uppassword = $("#uppassword").val();
                var repassword = $("#repassword").val();
                if(uppassword == "" || repassword == "" || uppassword != repassword){
                    confirm("修改失败，请检查密码是否有误！");
                    return false;
                }
            });

        });
    </script>
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">修改密码</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>

    <form action="<%=path%>/employee/updatePassword.do" method="post" id="formId">
        <ul class="forminfo">
            <input name="password" type="hidden" id="password" />
            <li><label>新密码：</label><input id="uppassword" onfocus="passwordFocus();" onblur="passwordBlur();" type="password" class="dfinput" /><i id="user_password"></i></li>
            <li><label>新密码确认:</label><input id="repassword" onfocus="repasswordFocus();" onblur="repasswordBlur();" type="password" class="dfinput" /><i id="user_repassword"></i></li>
            <li><input id="save" type="submit" class="btn" value="确认保存"/></li>
        </ul>
    </form>
</div>
</body>
</html>
