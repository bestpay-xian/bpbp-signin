<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<style>
#selectCenter{
border-style:solid;
border-width:5px;
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
//            $(".select1").uedSelect({
//                width: 345
//            });
//            $(".select2").uedSelect({
//                width: 200
//            });
//            $(".select3").uedSelect({
//                width: 100
//            });
            selectdept();
           // validate1();
            addOpionChange();

            sub();
        });

        <%--function selectCenter(){--%>
            <%--//$("#selectCentr").--%>
            <%--$.ajax({--%>
                <%--type:"post",--%>
                <%--url:"<%=path%>/center/selectCenterList.do",--%>
                <%--dataType:"json",--%>
                <%--success:function(msg){--%>
                    <%--if(msg.type=="error"){--%>
                        <%--alert(msg.info);--%>
                    <%--}else{--%>
                        <%--$.each(msg.result, function (index, jsonObj) {--%>
                            <%--$("#selectCenter").append("<input type='hidden' name='centerId' value='"+jsonObj.centerId+"'>"+ "<option>"+jsonObj.centerName+"</option>");--%>

                        <%--});--%>

                      <%--// $("#selectCenter  option[0] ").attr("selected",true);--%>
                      <%--//  $("#selectCenter").find("option[index='0']").prop("selected",'selected');--%>
                    <%--}--%>
                    <%--//*************获取中心列表--%>
                    <%--$("#selectCenter  option:first ").addClass("selected").attr("selected","selected");--%>
<%--//                    $("#selectCenter  option:first ").attr("selected","selected");--%>
                <%--}--%>
            <%--});--%>
        <%--}--%>
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
                if(deptId !="undefined"){
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

                                // $("#selectCenter  option[0] ").attr("selected",true);
                                $("#selectCenter").find("option[index='0']").prop("selected",'selected');
                            }
                            //*************获取中心列表
                           // $("#selectCenter  option:first ").addClass("selected").attr("selected","selected");
//                    $("#selectCenter  option:first ").attr("selected","selected");
                        }
                    });

                }
            });
        }
        <%--function validate1() {--%>
            <%--$("#formId").validate({--%>
                <%--rules: {--%>
                    <%--teamName: {--%>
                        <%--required: true,--%>
                        <%--remote: {--%>
                            <%--type: "post",--%>
                            <%--dataType: "json",--%>
                            <%--url: "<%=path%>/validateTeamIsExist.do",--%>
                            <%--data: {--%>
                                <%--name: function () {--%>
                                    <%--return $("input[name='teamName']").val();--%>
                                <%--}--%>
                            <%--}--%>
                        <%--}--%>

                    <%--}--%>
                <%--},--%>
                <%--messages: {--%>


                    <%--teamName: {--%>
                        <%--required: "小组名称不能为空！",--%>
                        <%--remote: "小组名称已存在"--%>
                    <%--}--%>
                <%--},--%>
                <%--submitHandler: function (form) {   //表单提交句柄,为一回调函数，带一个参数：form--%>
                    <%--form.submit();   //提交表单--%>
                <%--}--%>
            <%--});--%>
        <%--}--%>
                function sub() {
                    $("#formId").submit(function (e) {
                        var center=$("#selectCenter  option:selected").val();
                        var teanName=$("#teamName").val();
                        if(center=="-请选择中心-"){
                            alert("请选择中心")
                            return false;
                        }
                        var CenterId= $("#selectCenter  option:selected").prev().val();
                        $("#centerId").attr("value",CenterId);

                        if(teanName==null || teanName=="" ||teanName==undefined){
                            alert("请填写团队名称")
                            return false;
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
    <form id="formId" action="<%=path%>/insertTeamInfo.do" method="post">
        <ul class="forminfo">
            <li><label>部门</label>
                <div class="vocation">
                    <select id="selectdept" class="select2">
                    </select>
                </div>
            </li>
            <li><label>中心</label>
                <div class="vocation">
                    <select id="selectCenter" class="select2" >
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