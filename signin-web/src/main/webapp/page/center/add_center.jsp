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

      selectCenter();
      addOpionChange();
      sub();
    });
    function selectCenter(){
      $.ajax({
        type:"post",
        url:"<%=path%>/dept/list.do",
        dataType:"json",
        success:function(msg){
          if(msg.type=="error"){
            alert(msg.info);
          }else{
            $.each(msg.result.lists, function (index, jsonObj) {
              $("#selectDept").append("<input type='hidden' value='"+jsonObj.deptId+"'>"+ "<option>"+jsonObj.deptName+"</option>");
            });

          }
        }
      });
    }
    function addOpionChange(){
      $("#selectDept").change(function(){
        var deptName=$("#selectDept  option:selected").val();
        var deptId = $("#selectDept  option:selected").prev().val();
        if(deptName!="-请选择部门-") {
          $("#deptId").attr("value", deptId);
        }
      });
    }
    function sub() {
      $("#formId").submit(function (e) {
        var Dept = $("#selectDept  option:selected").val();
        var deptId = $("#selectDept  option:selected").prev().val();
        if (Dept == "-请选择部门-") {
          alert("请选择部门")
          return false;
        }
        var centerName=$.trim($("#centerName").val());
        if(centerName == null || centerName == "" || centerName == undefined) {
          alert("请填写中心名称")
          return false;
        }
        if(centerName.length>20){
          alert("中心名称太长,应保持在10个字符内")
          return false;
        }
        var flag=true;
        $.ajax({
          type:"post",
          url:"<%=path%>/center/validateCenterIsExist.do",
          dataType:"json",
          async: false,
          data:{
            centerName:centerName,
            deptId:deptId
          },
          success:function(msg) {
            if(msg=="NOTEXSTER"){
             flag=false;
              alert("小组已存在");
            }
          }
        });
        return flag;
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
    <ul class="forminfo">
      <li><label>部门</label>
        <div class="vocation">
        <select id="selectDept"  class="select2" >
          <option>-请选择部门-</option>
        </select>
        </div>
      </li>
      <input name="deptId" id="deptId" type="hidden" class="dfinput"  value=""/></li></br>
      <li><label>中心</label>
        <input name="centerName" id="centerName"type="text" class="dfinput" ></li></br>
      <li><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <input  type="submit" class="btn" value="确认添加"/></li>
    </ul>
    <c:if test="${requestScope.msg!=null &&requestScope.msg!=''}">

      ${requestScope.msg}

    </c:if>
  </form>
</div>
</body>
</html>