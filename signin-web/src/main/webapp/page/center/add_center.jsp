<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
  <link href="<%=path%>/resources/css/select.css" rel="stylesheet" type="text/css"/>
  <script src="<%=path%>/resources/js/jquery-1.9.1.min.js" language="JavaScript"></script>
  <script src="<%=path%>/resources/js/jquery.validate.js" language="JavaScript"></script>
  <script src="<%=path%>/resources/js/messages_cn.js" language="JavaScript"></script>
  <script src="<%=path%>/resources/js/select-ui.min.js"></script>
  <script type="text/javascript">

    $(function () {

      $(".select1").uedSelect({
        width: 345
      });
      $(".select2").uedSelect({
        width: 200
      });
      $(".select3").uedSelect({
        width: 100
      });

      validate1();
      selectCenter();


    });
    function selectCenter(){
      //$("#selectCentr").
      $.ajax({
        type:"post",
        url:"<%=path%>/dept/list.do",
        dataType:"json",
        success:function(msg){
          if(msg.type=="error"){
            alert(msg.info);
          }else{
            $.each(msg.result.lists, function (index, jsonObj) {
              $("#selectDept").append("<option value='"+jsonObj.deptId+"'>"+jsonObj.deptName+"</option>");
            });

          }
        }
      });
    }
    function validate1() {
      $("#formId").validate({
        rules: {
          centerName: {
            required: true,
            remote: {
              type: "post",
              dataType: "json",
              url: "<%=path%>/center/validateCenterIsExist.do",
              data: {
                name: function () {
                  return $("input[name='centerName']").val();
                }
              }
            }

          }
        },
        messages: {


          centerName: {
            required: "中心名不能为空！",
            remote: "中心名已存在"
          }
        },
        submitHandler: function (form) {   //表单提交句柄,为一回调函数，带一个参数：form
          form.submit();   //提交表单
        }
      });
    }
  </script>
</head>

<body>

<div class="place">
  <span>位置：</span>
  <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">添加团队</a></li>
  </ul>
</div>

<div class="formbody">
  <div class="formtitle"><span>基本信息</span></div>
  <form id="formId" action="<%=path%>/center/addCenter.do" method="post">


    <ul id="forminfo">
      <li><label>部门</label>
        <select id="selectDept"  class="select2" name="deptId">
        </select>
      </li>
      <li><label>中心</label>
        <input name="centerName" type="text" class="dfinput" ></li></br>
      <li><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <input  type="submit" class="btn" value="确认添加"/></li>
    </ul>
    <c:if test="${requestScope.msg!=null &&requestScope.msg!=''}">

      ${requestScope.msg}

    </c:if>
  </form>
</div>
</body>
</html>