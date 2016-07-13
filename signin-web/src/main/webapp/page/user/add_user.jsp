<%@ page import="java.nio.file.Path" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script type="text/javascript" src="<%=path%>/resources/js/MD5.js"></script>
    <script>
        $(function () {
            //请求平台厂商
            $.ajax({
                type: "post",
                url: "<%=path%>/platform/selectPlatformList.do",
                success: function (msg) {
                    if (msg.type != 'success') {
                        return false;
                    } else {
                        $("#selectId").append("<option>-请选择所属厂商-</option>");
                        $.each(msg.result, function (i, n) {
                            $("#selectId").append("<option value=\"" + n.platformId + "\">" + n.name + "</option>");
                        });
                    }

                    //拼装select
                }
            });
            //请求组别
            $.ajax({
                type: "post",
                url: "<%=path%>/team/list.do",
                success: function (msg) {
                    if (msg.type != 'success') {
                        return false;
                    } else {
                        $("#selectIdInfo").append("<option>-请选择所属团队-</option>");
                        $.each(msg.result.lists , function (i, n) {
                            $("#selectIdInfo").append("<option value=\"" + n.teamId + "\">" + n.teamName + "</option>");
                        });
                    }

                    //拼装select
                }
            });
            $("#save").click(function () {
                $("#newPassword").val(MD5($("#oldPassword").val()));
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
                        required: "*员工姓名不能为空",
                        rangelength: "*输入长度在1和 10 之间的字符串"
                    },
                    phone: {
                        required: "*员工电话不能为空",
                        remote: "*员工电话已存在",
                        rangelength: "*输入长度在 9和 11 之间的整数",
                        digits: "*需要输入整数"
                    },
                    qq: {
                        required: "*员工QQ不能为空",
                        remote: "*员工QQ已存在",
                        rangelength: "*输入长度在 6和 11 之间的整数",
                        digits: "*需要输入整数"
                    },
                    email: {
                        required: "*员工邮箱不能为空",
                        remote: "*员工邮箱已存在"
                    },
                    password: {
                        required: "*员工密码不能为空"
                    }
                },
                submitHandler: function (form) {   //表单提交句柄,为一回调函数，带一个参数：form
                    form.submit();   //提交表单
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
        <li><a href="#">员工添加</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="<%=path%>/employee/insertEmployee.do" method="post" id="formId">
        <input name="password" type="hidden" id="newPassword"/>
        <ul class="forminfo">
            <li><label>员工姓名:</label><input name="name" type="text" class="dfinput" rangelength="1,11" required="true"/>
            </li>
            <li><label>员工电话：</label><input name="phone" type="text" class="dfinput" rangelength="9,11" required="true"
                                           digits="true"/></li>
            <li><label>员工QQ: </label><input name="qq" type="text" class="dfinput" rangelength="6,11" required="true"
                                            digits="true"/></li>
            <li><label>员工邮箱：</label><input name="email" type="text" class="dfinput" email="true" required="true"/></li>
            <li><label>员工密码：</label><input id="oldPassword" type="password" class="dfinput" value="000000"
                                           required="true"/>初始密码已经设置为：000000</li>
            <li><label>所属团队：</label>
                <div class="vocation">
                    <select id="selectIdInfo" class="select2" name="team">
                    </select>
                </div>
            </li>
            <li><label>所属厂商：</label>
                <div class="vocation">
                    <select id="selectId" class="select2" name="platformId">
                    </select>
                </div>
            </li>
            <input type="hidden" name="token" value="${token}"/>
            <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input id="save" type="submit" class="btn" value="确认保存"/></li>
        </ul>
        <c:if test="${requestScope.msg!=null &&requestScope.msg!=''}">
            ${requestScope.msg}
        </c:if>
    </form>

</div>
</body>
</html>