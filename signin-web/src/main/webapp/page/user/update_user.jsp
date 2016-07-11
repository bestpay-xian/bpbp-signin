<%--
  Created by IntelliJ IDEA.
  User: cailonggang
  Date: 2016/5/23
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.nio.file.Path" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/resources/css/select.css" rel="stylesheet" type="text/css"/>
    <script src="<%=path%>/resources/js/jquery-1.9.1.min.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/jquery.validate.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/messages_cn.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/select-ui.min.js"></script>
    <script >
        $(function () {
            //请求平台厂商
            $.ajax({
                type: "post",
                url: "<%=path%>/platform/selectPlatformList.do",
                success: function (msg) {
                    if(msg.type !='success'){
                         return false;
                    }else{
                        $.each(msg.result,function(i,n){
                            $("#selectId").append("<option value=\""+ n.platformId+"\">" + n.name+"</option>");
                        });
                    }

                    //拼装select
                }
            });
            $(".select1").uedSelect({
                width: 345
            });
            $(".select2").uedSelect({
                width: 200
            });
            $(".select3").uedSelect({
                width: 100
            });

            //页面进行各种验证
            $("#formId").validate({
                rules: {
                    name: {
                        required: true
                    },
                    phone: {
                        required: true,
                        remote: {
                            type: "post",
                            dataType: "json",
                            url: "<%=path%>/employee/checkEmployee.do",
                            data: {
                                phone: function () {
                                    return $("input[name='phone']").val();
                                },
                                employeeId:function(){
                                    return "${employee.employeeId}";
                                }
                            }
                        }

                    },
                    qq: {
                        required: true,
                        remote: {
                            type: "post",
                            dataType: "json",
                            url: "<%=path%>/employee/checkEmployee.do",
                            data: {
                                qq: function () {
                                    return $("input[name='qq']").val();
                                },
                                employeeId:function(){
                                    return "${employee.employeeId}";
                                }
                            }
                        }
                    },
                    email: {
                        required: true,
                        remote: {
                            type: "post",
                            dataType: "json",
                            url: "<%=path%>/employee/checkEmployee.do",
                            data: {
                                email: function () {
                                    return $("input[name='email']").val();
                                },
                                employeeId:function(){
                                    return "${employee.employeeId}";
                                }
                            }
                        }
                    },
                    password: {
                        required: true
                    }
                },
                messages: {
                    name: {
                        required: "员工姓名不能为空",
                        rangelength: "输入长度在 1和 10 之间的汉字或字母"
                    },
                    phone: {
                        required: "员工电话不能为空",
                        remote: "员工电话已存在",
                        rangelength: "输入长度在 10和 11 之间的整数",
                        digits: "需要输入整数"
                    },
                    qq: {
                        required: "员工QQ不能为空",
                        remote: "员工QQ已存在",
                        rangelength: "输入长度在 6和 11 之间的整数",
                        digits: "需要输入整数"
                    },
                    email: {
                        required: "员工邮箱不能为空",
                        remote: "员工邮箱已存在"
                    },
                    password: {
                        required: "员工密码不能为空"
                    }
                },
                submitHandler: function (form) {   //表单提交句柄,为一回调函数，带一个参数：form
                    form.submit();   //提交表单
                }
            });
        });
        <c:if test="${message!=null && message!=''}">
        alert("${message}");
        </c:if>

    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">员工更改</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="<%=path%>/employee/updateEmployee.do" method="post" id="formId">
        <input name="password" type="hidden" id="newPassword" />
        <ul class="forminfo">
            <li><input name="employeeId" type="hidden" class="dfinput"   value="${employee.employeeId}"/></li>
            <li><label>员工姓名:</label><input name="name" type="text"   class="dfinput" rangelength="1,11" required="true" value="${employee.name}"/></li>
            <li><label>员工电话:</label><input name="phone" type="text" class="dfinput" rangelength="9,11" required="true" digits="true" value="${employee.phone}"/></li>
            <li><label>员工QQ:  </label><input name="qq"    type="text" class="dfinput"  rangelength="6,11" required="true" digits="true"value="${employee.qq}"/></li>
            <li><label>员工邮箱:</label><input name="email" type="text" class="dfinput" email="true" required="true"value="${employee.email}"/></li>
            <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               <input id="save" type="submit" class="btn" value="确认修改"/></li>
        </ul>
    </form>

</div>
</body>
</html>