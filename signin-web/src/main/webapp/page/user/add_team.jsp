<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<style>
.tt{
border-style:groove;
border-width:1px;
    Width:200px;
    height:35px;
    border-color:#F5DEB3;
}
</style>
<head>
    <link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="<%=path%>/resources/js/jquery-1.9.1.min.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/jquery.validate.js" language="JavaScript"></script>
    <script src="<%=path%>/resources/js/messages_cn.js" language="JavaScript"></script>
    <script type="text/javascript">
        $(function () {
            $("#save").click(function () {
                $("#newPassword").val(MD5($("#oldPassword").val()));
            });
            selectdept();
            addOpionChange();
            sub();
        });

        //************获取部门列表
        function selectdept(){
            //$("#selectCentr").
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
                var deptId= $("#selectdept  option:selected").prev().val();
                var input1=$("#romeCenter ~ input")
                input1.remove();
                var option1=$("#romeCenter ~ option")
                option1.remove();
                var deptName= $("#selectdept  option:selected").val();
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
                function sub() {
                    $("#formId").submit(function (e) {
                        var center=$("#selectCenter  option:selected").val();
                        var teamName=$("#teamName").val();
                        if(center=="-请选择中心-"){
                            alert("请选择中心")
                            return false;
                        }
                        var CenterId= $("#selectCenter  option:selected").prev().val();
                        $("#centerId").attr("value",CenterId);

                        if(teamName==null || teamName=="" ||teamName==undefined){
                            alert("请填写团队名称")
                            return false;
                        }

                        var flag=true;
                        $.ajax({
                            type:"post",
                            url:"<%=path%>/validateTeamIsExist.do",
                            dataType:"json",
                            async: false,
                            data:{
                                CenterId:CenterId,
                                teamName:teamName
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
    <form id="formId" action="<%=path%>/insertTeamInfo.do" method="post">
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
                    <select id="selectCenter" class="select2 tt" >
                        <option id="romeCenter">-请选择中心-</option>
                    </select>
                </div>
            </li>
            <input name="centerId"  id="centerId" type="hidden" class="dfinput" value="" ></li></br>
            <li><label>团队</label>
                <input name="teamName" type="text" id="teamName" class="dfinput" ></li></br>
            <li><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <input  type="submit" class="btn" value="确认添加"/></li>
        </ul>
        <c:if test="${requestScope.msg!=null &&requestScope.msg!=''}">
            ${requestScope.msg}
        </c:if>
    </form>
</div>
</body>
</html>·