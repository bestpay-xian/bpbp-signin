<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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
  <script type="text/javascript">

    $(function () {
        /*selectCenter();*/
        validate1();
        $(".select1").uedSelect({
            width: 345
        });
        $(".select2").uedSelect({
            width: 200
        });
        $(".select3").uedSelect({
            width: 100
        });
//           debugger
      /*  $("#formId").validate({
       rules: {
       name:{
       required: true,
       remote: {
       type: "post",
       dataType: "json",
       url: "<%=path%>/platform/validatePlatformNameIsExist.do",
       data: {
       name: function () {
       return $("input[name='name']").val();
       }
       }
       }

       }
       },
       messages: {


       name:{
       required:"！",
       remote: "厂商名字已存在"
       }
       },
       submitHandler: function (form) {   //表单提交句柄,为一回调函数，带一个参数：form
       form.submit();   //提交表单
       }
       });*/


    });

    function selectCenter(){
        //$("#selectCentr").
        $.ajax({
            type:"post",
            url:"<%=path%>/Center/selectCenterList.do",
            dataType:"json",
            success:function(msg){
                if(msg.type=="error"){
                    alert(msg.info);
                }else{
                    $.each(msg.result, function (index, teamObj) {
                        $("#selectCenter").append("<input type='hidden' name='centerId' value='"+teamObj.centerId+"'>"+ "<option  onchange='chaose(\""+teamObj.centerName+ "\")'>"+teamObj.centerName+"</option>");
                    });

                }
            }
        });
    }

    function chaose(){
      $("#centerName").val( $("#selectCenter :selected").val());
    }
 function validate1() {
     $("#formId").validate({
         rules: {
             teamName: {
                 required: true,
                 remote: {
                     type: "post",
                     dataType: "json",
                     url: "<%=path%>/validateTeamIsExist.do",
                     data: {
                         name: function () {
                             return $("input[name='teamName']").val();
                         }
                     }
                 }

             }
         },
         messages: {


             name: {
                 required: "小组名称不能为空！",
                 remote: "小组名称已存在"
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
    <li><a href="#">表单</a></li>
  </ul>
</div>

<div class="formbody">

  <div class="formtitle"><span>基本信息</span></div>
  <form action="<%=path%>/team/update.do" id="formId" method="post">
    <ul id="forminfo">
      <li><input name="centerId" type="hidden" class="dfinput"   value="${requestScope.team.centerId}"/></li>
     <!-- <li><label>修改所属中心</label>
        <select id="selectCenter"  class="select2" onchange="chaose()" >

        </select>
        -->
        <li><label>所属中心 </label><input name="centerName" type="text" id="centerName" class="dfinput" value="${requestScope.team.centerName}" disabled="disabled"/></li>
        <br>
       <li><input name="teamId" type="hidden" class="dfinput" value="${requestScope.team.teamId}"/></li>
        <li><label>团队名称 </label><input name="teamName" type="text" class="dfinput" value="${requestScope.team.teamName}" /></li>
        <br>
        <li><label>&nbsp;</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input value="提交" type="submit" class="btn" /></li>
      </li>
    </ul>
      <c:if test="${requestScope.msg!=null &&requestScope.msg!=''}">
          ${requestScope.msg}
      </c:if>
  </form>
</div>
</body>
</html>
