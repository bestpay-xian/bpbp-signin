<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <style>
        .tt{
            border-style:groove;
            border-width:1px;
            Width:200px;
            height:35px;
            border-color:#F5DEB3;
        }
        </style>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>无标题文档</title>
    <link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="<%=path%>/resources/js/jquery-1.9.1.min.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/jquery.validate.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/messages_cn.js" language="JavaScript"></script>
    <%--<script src="<%=path%>/resources/js/select-ui.min.js"></script>--%>
  <script type="text/javascript">

    $(function () {
        selectdept();
        addOpionChange();
        addOpionChange1();
        sub();
    });
    function addOpionChange1() {

        $("#selectCenter").change(function () {
            var centerName = $("#selectCenter  option:selected").val();
            if (centerName!="-请选择中心-") {
                var centerId = $("#selectCenter  option:selected").prev().val();
                $("#centerId").attr("value", centerId);
                $("#centerName").attr("value", centerName);
            }
        });

    }
    function sub() {
        $("#formId").submit(function (e) {

            var centerId=$("#centerId").val();
            var teamName=$("#teamName").val();
            var teamId=$("#teamId").val();
            if(teamName==null || teamName=="" ||teamName==undefined){
                alert("请填写团队名称")
                return false;
            }
            if(teamName.length>20){
                alert("团队名称太长,应保持在10个字符内")
                return false;
            }
            var flag=true;
            $.ajax({
                type:"post",
                url:"<%=path%>/validateTeamIsExist.do",
                dataType:"json",
                async: false,
                data:{
                    centerId:centerId,
                    teamName:teamName,
                    teamId:teamId
                },
                success:function(msg) {
                    if(msg=="HASTEAM"){
                        flag=false;
                        alert("小组已存在");
                    }
                }
            });
            return flag;


        });
    }
    //************获取部门列表
    function selectdept(){
        $.ajax({
            type:"post",
            url:"<%=path%>/dept/list.do",
            dataType:"json",
            success:function(msg){
                if(msg.type=="error"){
                    alert(msg.info);
                }else{
                    $.each(msg.result.lists, function (index, teamObj) {
                        $("#selectdept").append("<input type='hidden' value='"+teamObj.deptId+"'>"+ "<option>"+teamObj.deptName+"</option>");
                    });
                }
            }
        });
    }

    //*****获取中心开始
    function addOpionChange(){
        $("#selectdept").change(function(){
            var deptName= $("#selectdept  option:selected").val();
            var deptId= $("#selectdept  option:selected").prev().val();
            var input1=$("#romeCenter ~ input")
            input1.remove();
            var option1=$("#romeCenter ~ option")
            option1.remove();
            if(deptName!="-请选择部门-"){
                $.ajax({
                    type:"post",
                    url:"<%=path%>/center/selectCenterList.do",
                    dataType:"json",
                    data:{
                        "deptId":deptId
                    },
                    success:function(msg){
                        if(msg.type=="error"){
                            alert(msg.info);
                        }else{
                            $.each(msg.result, function (index, jsonObj) {
                                $("#selectCenter").append("<input type='hidden'  value='"+jsonObj.centerId+"'>"+ "<option>"+jsonObj.centerName+"</option>");
                            });
                            $("#selectCenter").find("option[index='0']").prop("selected",'selected');
                        }

                    }
                });

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
    <ul class="forminfo">
        <li><label>部门</label>

            <div class="vocation">
                <select id="selectdept" class="select2 tt">
                    <option id="romedept">-请选择部门-</option>
                </select>
            </div>
        </li>
        <br>
        <li><label>中心</label>
            <div class="vocation">
                <select id="selectCenter" class="select2 tt">
                    <option id="romeCenter">-请选择中心-</option>
                </select>
            </div>
        </li>
        <input name="centerId"  id="centerId" type="hidden" class="dfinput" value="${requestScope.team.centerId}" ></li></br>
        <li><label>所属中心 </label><input name="centerName" type="text" id="centerName" class="dfinput" value="${requestScope.team.centerName}" disabled="disabled"/></li>
        <br>
       <li><input name="teamId" type="hidden" id="teamId" class="dfinput" value="${requestScope.team.teamId}"/></li>
        <li><label>团队名称 </label><input name="teamName" id="teamName" type="text" class="dfinput" value="${requestScope.team.teamName}" /></li>
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
