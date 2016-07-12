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
            selectdept();
//            sub();
            addOpionChange();

        });

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
//"<option  onchange='chaose(\""+teamObj.deptName+ "\")'>"+teamObj.deptName+"</option>"
                    }
                }
            });
        }

//        function chaose(){
//            $("#deptName").val( $("#selectdept :selected").val());
//        }
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
                        required: "中心名称不能为空！",
                        remote: "中心名称已存在"
                    }
                },
                submitHandler: function (form) {   //表单提交句柄,为一回调函数，带一个参数：form
                    form.submit();   //提交表单
                }
            });
        }
        function addOpionChange(){
               $("#selectdept").change(function(){
                   var deptName=$("#selectdept  option:selected").val();
                   var deptId= $("#selectdept  option:selected").prev().val();
                   if(deptId !="undefined"){
                  $("#deptId").attr("value",deptId);
                       $("#deptName").attr("value",deptName);
               }
               });
        }
//        function sub() {
//            $("#formId").submit(function (e) {

                <%--//验证中心号重复--%>
                <%--var select = $("#selectdept  option:selected").val();--%>
                <%--if (select === "请选择部门") {--%>
                <%--var deptId =$("#deptId").attr("value").val();--%>
                <%--var centerName=$("#centerName").attr("value").val();--%>
                <%--var centerId=$("#centerId").attr("value").val();--%>

                <%--$.ajax({--%>
                <%--type: "post",--%>
                <%--url: "<%=path%>/center/validateCenterIsExist.do",--%>
                <%--dataType: "json",--%>
                <%--date: {--%>
                <%--"deptId":deptId,--%>
                <%--"centerName":centerName,--%>
                <%--"centerId":centerId--%>
                <%--},--%>
                <%--success: function (msg) {--%>
                <%--if (msg.info == "验证失败") {--%>
                <%--alert(msg.info);--%>
                <%--return;--%>
                <%--}--%>
                <%--if(msg.result=="CENTRNOTLLNOLL"){--%>
                <%--alert("中心号已存在");--%>
                <%--return;--%>
                <%--}--%>
                <%--}--%>

                <%--});--%>
                <%--}--%>
                <%--else{--%>
                <%--var deptId= $("#selectdept  option:selected").next().val();--%>
                <%--var centerName=$("#centerName").attr("value").val();--%>
                <%--var centerId=$("#centerId").attr("value").val();--%>
                <%--$.ajax({--%>
                <%--type: "post",--%>
                <%--url: "<%=path%>/center/validateCenterIsExist.do",--%>
                <%--dataType: "json",--%>
                <%--date: {--%>
                <%--"deptId":deptId,--%>
                <%--"centerName":centerName,--%>
                <%--"centerId":centerId--%>
                <%--},--%>
                <%--success: function (msg) {--%>
                <%--if (msg.info == "验证失败") {--%>
                <%--alert(msg.info);--%>
                <%--return;--%>
                <%--}--%>
                <%--if(msg.result=="CENTRNOTLLNOLL"){--%>
                <%--alert("中心号已存在");--%>
                <%--return;--%>
                <%--}--%>
                <%--}--%>

                <%--});--%>
                <%--}--%>
                //
//                var select=$("#selectdept  option:selected").val();
//                if(select=="请选择部门"){
//
//                }else{
//                    var deptId= $("#selectdept  option:selected").prev().val();
//                    $("#deptId").attr("value",deptId);
//                    var deptName=$("#selectdept  option:selected").val();
//                    $("#deptName").attr("value",deptName);
//                }
//
//            });
//        }
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
    <form action="<%=path%>/center/updateCenter.do" id="formId" method="post">
        <ul class="forminfo">
            <br>
            <li><label>请选择部门</label>
                <div class="vocation">
                    <select id="selectdept" class="select2">
                        <option>请选择部门</option>
                    </select>
                </div>
            </li>
            <br>
            <br>
            <br>
            <br>
            <li><label>所选择的部门</label><input name="deptName" type="text" id="deptName" class="dfinput" value="${requestScope.center.deptName}" disabled="disabled"/></li>
            <li><input name="deptId" id="deptId" type="hidden" class="dfinput" value="${requestScope.center.deptId}"/></li>
            <br>
            <li><input name="centerId" id="centerId" type="hidden" class="dfinput" value="${requestScope.center.centerId}"/></li>
            <li><label>中心名称:  </label><input name="centerName" type="text" class="dfinput" value="${requestScope.center.centerName}" /></li>
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
