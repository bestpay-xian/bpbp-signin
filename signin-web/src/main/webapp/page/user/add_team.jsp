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

            $("#save").click(function () {
                $("#newPassword").val(MD5($("#oldPassword").val()));
            });
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
            selectCenter();


        });
        function selectCenter(){
            //$("#selectCentr").
            $.ajax({
                type:"post",
                url:"<%=path%>/center/selectCenterList.do",
                dataType:"json",
                success:function(msg){
                    if(msg.type=="error"){
                        alert(msg.info);
                    }else{
                        $("#selectCenter").append("<option>-请选择部门-</option>");
                        $.each(msg.result, function (index, jsonObj) {
                            $("#selectCenter").append("<input type='hidden' name='centerId' value='"+jsonObj.centerId+"'>"+ "<option>"+jsonObj.centerName+"</option>");

                        });

                      // $("#selectCenter  option[0] ").attr("selected",true);
                      //  $("#selectCenter").find("option[index='0']").prop("selected",'selected');
                    }

                    $("#selectCenter  option:first ").addClass("selected").attr("selected","selected");
//                    $("#selectCenter  option:first ").attr("selected","selected");
                }
            });
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


                    teamName: {
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
        <li><a href="#">添加团队</a></li>
    </ul>
</div>

<div class="formbody">
    <div class="formtitle"><span>基本信息</span></div>
    <form id="formId" action="<%=path%>/insertTeamInfo.do" method="post">
        <ul id="forminfo">
            <li><label>中心</label>
                <select id="selectCenter" value="请选择中心"  class="select2">
                </select>
            </li>
            <li><label>团队</label>
                <input name="teamName" type="text" class="dfinput" ></li></br>
            <li><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <input  type="submit" class="btn" value="确认添加"/></li>
        </ul>
        <c:if test="${requestScope.msg!=null &&requestScope.msg!=''}">
            ${requestScope.msg}
        </c:if>
    </form>
</div>
</body>
</html>·