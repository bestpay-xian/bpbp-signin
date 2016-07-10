<%@ page import="com.bestpay.bpbp.signin.dal.models.Employee" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    Employee employee = (Employee) session.getAttribute("employee");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>通讯录查询</title>
    <link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/resources/css/select.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/resources/css/pagination.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/select-ui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery.pagination.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            communicationList();
        });

        function handlePaginationClick(new_page_index, pagination_container) {
            var username = $("#username").val();
            $("#userList").html("");
            $(".pagin").html("");
            var page = new_page_index + 1;
            $.ajax({
                type: "post",
                url: "<%=path%>/selectAll.do",
                dataType: "json",
                data: {"pageNo": page, "name": username},
                success: function (msg) {
                    $.each(msg.result.lists, function (index, n) {
                        if (msg.type == "error") {
                            alert(msg.info);
                        }
                        else {
                            $("#userList").append(
                                    "<tr>" +
                                    "<td>" + n.pk_employee + "</td>" +
                                    "<td>" + n.name + "</td>" +
                                    "<td>" + n.deptname + "</td>" +
                                    "<td>" + n.centername + "</td>" +
                                    "<td>" + n.teamname + "</td>" +
                                    "<td>" + n.phone + "</td>" +
                                    "<td>" + n.qq + "</td>" +
                                    "<td>" + n.email + "</td>" +
                                    "</tr>"
                            );
                        }
                    });
                    $(".pagin").append(
                            "<div class='message'>共<i class='blue'>&nbsp;" + msg.result.total + "&nbsp;</i>条记录，当前显示第&nbsp;<i class='blue'>" + msg.result.pageNo + "&nbsp;</i>页</div>" +
                            "<input id='total' type='hidden' value='" + msg.result.total + "'/>"
                    );
                }
            });
        }

        function communicationList() {
            var username = $("#username").val();
            $("#userList").html("");
            $(".pagin").html("");

            $.ajax({
                type: "post",
                url: "<%=path%>/selectAll.do",
                dataType: "json",
                data: {"name": username},
                success: function (msg) {
                    $("#News-Pagination").pagination(msg.result.total, {
                        items_per_page: 10, // 每页显示多少条记录
                        current_page: msg.result.pageNo-1, // 当前显示第几页数据
                        num_display_entries: 3, // 分页显示的条目数s
                        next_text: "下一页",
                        prev_text: "上一页",
                        num_edge_entries: 2, // 连接分页主体，显示的条目数
                        callback: handlePaginationClick
                    });

                    $.each(msg.result.lists, function (index, n) {
                        if (msg.type == "error") {
                            alert(msg.info);
                        }
                        else {
                            $("#userList").append(
                                    "<tr>" +
                                    "<td>" + n.pk_employee + "</td>" +
                                    "<td>" + n.name + "</td>" +
                                    "<td>" + n.deptname + "</td>" +
                                    "<td>" + n.centername + "</td>" +
                                    "<td>" + n.teamname + "</td>" +
                                    "<td>" + n.phone + "</td>" +
                                    "<td>" + n.qq + "</td>" +
                                    "<td>" + n.email + "</td>" +
                                    "</tr>"
                            );
                        }
                    });
                    $(".pagin").append(
                            "<div class='message'>共<i class='blue'>&nbsp;" + msg.result.total + "&nbsp;</i>条记录，当前显示第&nbsp;<i class='blue'>" + msg.result.pageNo + "&nbsp;</i>页</div>" +
                            "<input id='total' type='hidden' value='" + msg.result.total + "'/>"
                    );
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
        <li><a href="#">通讯录</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div id="tab2" class="tabson">
            <ul class="seachform">
                <li><label>员工姓名：</label><input id="username" name="" type="text" class="scinput"/></li>
                <li><label>&nbsp;</label><input id="selBut" name="" type="button" class="scbtn" value="查询"
                                                onclick="communicationList()"/></li>
            </ul>
            <table class="tablelist">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>员工姓名</th>
                    <th>部门</th>
                    <th>中心</th>
                    <th>小组</th>
                    <th>手机</th>
                    <th>邮箱</th>
                    <th>QQ号</th>
                </tr>
                </thead>
                <tbody id="userList">

                </tbody>
            </table>
            <div class="pagin" style="float: left;"></div>
            <div id="News-Pagination" style="float: right;margin-top: 10px;"></div>
        </div>
    </div>
</div>
</body>
</html>
