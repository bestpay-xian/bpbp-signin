<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>无标题文档</title>
    <link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/resources/css/select.css" rel="stylesheet" type="text/css"/>
    <script src="<%=path%>/resources/js/jquery-1.9.1.min.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/jquery.validate.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/messages_cn.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/select-ui.min.js"></script>
    <script type="text/javascript">

        $(function () {
//           debugger
            $("#formId").validate({
                rules: {
                    name:{
                        required: true,
                        remote: {
                            type: "post",
                            dataType: "json",
                            url: "<%=path%>/platform/validatePlatformNameIsExist.do",
                            data: {
                                 name:function () {
                                    return $("input[name='name']").val();
                                }
                            }
                        }

                    }
                },
                messages: {


                    name:{
                        required:"厂商名字不能为空！",
                        remote: "厂商名字重名或未修改请修改"
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
        <li><a href="#">表单</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>
    <form action="<%=path%>/platform/updatePlatform.do" id="formId" method="post">
        <ul id="forminfo">
                <li><label></label><input name="platformId" type="hidden" class="dfinput" value="${requestScope.record.platformId}"/></li>
                <li><label>厂商名</label><input name="name" type="text" class="dfinput" value="${requestScope.record.name}" /></li>
            <br>
                <li><label>&nbsp;</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input value="提交" type="submit" class="btn" /></li>
            </li>
        </ul>
        <c:if test="${requestScope.msg!=null &&requestScope.msg!=''}">
            ${requestScope.msg}
        </c:if>
    </form>
</div>
</body>
</html>