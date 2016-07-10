<%--
  Created by IntelliJ IDEA.
  User: cailonggang
  Date: 2016/5/24
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
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
      $("#formId").validate({
        rules: {
          deptName:{
            required: true,
            remote: {
              type: "post",
              dataType: "json",
              url: "<%=path%>/dept/checkDept.do",
              data: {
                deptName:function () {
                  return $("input[name='deptName']").val();
                }
              }
            }
          }
        },
        messages: {
          deptName:{
            required:"部门名称不能为空！",
            remote: "部门名称重名或未修改请修改"
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
    <li><a href="#">部门更改</a></li>
  </ul>
</div>

<div class="formbody">

  <div class="formtitle"><span>部门更改</span></div>
  <form action="<%=path%>/dept/updateDept.do" id="formId" method="post">
    <ul id="forminfo">
      <li><label></label><input name="deptId" type="hidden" class="dfinput" value="${dept.deptId}" /></li>
      <li><label>部门名称:&nbsp;&nbsp;&nbsp;</label><input name="deptName" type="text" class="dfinput" value="${dept.deptName}" /></li></br>
      <li><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   </label><input value="确认修改" type="submit" class="btn" /></li>
      </li>
    </ul>
  </form>
</div>
</body>
</html>