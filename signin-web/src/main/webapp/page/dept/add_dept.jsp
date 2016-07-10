<%--
  Created by IntelliJ IDEA.
  User: cailonggang
  Date: 2016/5/24
  Time: 8:59
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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
      $("#formId").validate({ //ajax强求后台检查部门名称是否重复
        rules: {
          deptName:{
            required: true,
            remote: {
              type: "post",
              dataType: "json",
              url: "<%=path%>/dept/checkDept.do",
              data: {
                deptName: function () {
                  return $("input[name='deptName']").val();
                }
              }
            }

          }
        },
        messages: {
          deptName:{
            required:"部门名字不能为空！",
            remote: "部门名字已存在",
            rangelength: "输入长度在1和 11 之间的汉字或英文"
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
    <li><a href="#">添加部门</a></li>
  </ul>
</div>

<div class="formbody">
  <div class="formtitle"><span>基本信息</span></div>
  <form id="formId" action="<%=path%>/dept/insertDept.do" method="post">
    <ul id="forminfo">
      <li><label>部门名称:&nbsp;&nbsp;&nbsp;</label><input name="deptName" type="text" class="dfinput" rangelength="1,11" required="true"></li></br>
      <li><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <input  type="submit" class="btn" value="确认添加"/>
      </li>
    </ul>
  </form>
</div>
</body>
</html>
