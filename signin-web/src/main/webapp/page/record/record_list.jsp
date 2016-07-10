<%@ page import="com.bestpay.bpbp.signin.dal.models.Employee" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    String path = request.getContextPath();
    Employee employee = (Employee) session.getAttribute("employee");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>签到查询</title>
    <link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/resources/css/select.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/resources/css/select.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/resources/css/pagination.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/select-ui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/base.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery.pagination.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            recordList();
        });

        function handlePaginationClick(new_page_index, pagination_container) {

            //清空上次数据
            $("#userList").html("");
            $(".pagin").html("");

            var page = new_page_index + 1;
            var startTime = $("input[name='startDate']").val();
            var endTime = $("input[name='endDate']").val();
            if (startTime) {
                startTime = new Date(startTime).Format("yyyy-MM-dd hh:mm:ss");
            } else {
                startTime = "";
            }

            if (endTime) {
                endTime = new Date(endTime).Format("yyyy-MM-dd hh:mm:ss");
            } else {
                endTime = "";
            }

            //校验
            if (startTime > endTime && endTime) {
                alert("开始时间必须小于签退时间");
                return;
            }

            $.ajax({
                type: "post",
                url: "<%=path%>/record/list.do",
                dataType: "json",
                data: {
                    "pageNo": page,
                    "employeeName": $("#username").val(),
                    "startDate": startTime,
                    "endDate": endTime
                },
                success: function (msg) {
                    $.each(msg.result.lists, function (i, n) {

                        var startTime = (n.startTime == null || n.startTime == "" || n.startTime == undefined) ? "" : dataFormat(n.startTime);
                        var endTime = (n.endTime == null || n.endTime == "" || n.endTime == undefined) ? "" : dataFormat(n.endTime);

                        $("#userList").append("<tr><td>" + n.recordId + "</td>" +
                                "<td>" + n.employeeName + "</td>" +
                                "<td>" + startTime + "</td>" +
                                "<td>" + endTime + "</td>" +
                                "<td>" + n.hoursWork + "</td></tr>");
                    });

                    $(".pagin").append(
                            "<div class='message'>共<i class='blue'>&nbsp;" + msg.result.total + "&nbsp;</i>条记录，当前显示第&nbsp;" + msg.result.pageNo + "<i class='blue'>&nbsp;</i>页</div>" +
                            "<input id='total' type='hidden' value='" + msg.result.total + "'/>"
                    );
                }
            });
            return false;
        }

        function recordList() {
            //清空上次数据
            $("#userList").html("");
            $(".pagin").html("");

            var startTime = $("input[name='startDate']").val();
            var endTime = $("input[name='endDate']").val();

            if (startTime) {
                startTime = new Date(startTime).Format("yyyy-MM-dd hh:mm:ss");
            } else {
                startTime = "";
            }

            if (endTime) {
                endTime = new Date(endTime).Format("yyyy-MM-dd hh:mm:ss");
            } else {
                endTime = "";
            }
            //校验
            if (startTime > endTime && endTime) {
                alert("开始时间必须小于签退时间");
                return;
            }

            $.ajax({
                type: "post",
                url: "<%=path%>/record/list.do",
                dataType: "json",
                data: {
                    "employeeName": $("#username").val(),
                    "startDate": startTime,
                    "endDate": endTime
                },
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
                    $.each(msg.result.lists, function (i, n) {
                        var startTime = (n.startTime == null || n.startTime == "" || n.startTime == undefined) ? "" : dataFormat(n.startTime);
                        var endTime = (n.endTime == null || n.endTime == "" || n.endTime == undefined) ? "" : dataFormat(n.endTime);

                        $("#userList").append(
                                "<tr><td>" + n.recordId + "</td>" +
                                "<td>" + n.employeeName + "</td>" +
                                "<td>" + startTime + "</td>" +
                                "<td>" + endTime + "</td>" +
                                "<td>" + n.hoursWork + "</td></tr>");
                    });

                    $(".pagin").append(
                            "<div class='message'>共<i class='blue'>&nbsp;" + msg.result.total + "&nbsp;</i>条记录，当前显示第&nbsp;<i class='blue'>" + msg.result.pageNo + "&nbsp;</i>页</div>" +
                            "<input id='total' type='hidden' value='" + msg.result.total + "'>"
                    );
                }
            });
        }

        //时间格式化
        function dataFormat(dateStr) {
            var d = new Date(dateStr);
            var year = d.getFullYear();
            var month = d.getMonth() + 1;
            var day = d.getDate();
            var hour = d.getHours();
            var min = d.getMinutes();

            var time = year + '-' + (Array(2).join(0) + month).slice(-2) + '-' + (Array(2).join(0) + day).slice(-2) + ' '
                    + (Array(2).join(0) + hour).slice(-2) + ':' + (Array(2).join(0) + min).slice(-2);
            return time;
        }

    </script>

</head>

<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">签到查询</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div id="tab2" class="tabson">
            <ul class="seachform">
                <c:if test="${employee.isAdmin == 2}">
                    <li><label>员工姓名：</label><input id="username" name="" type="text" class="scinput"/></li>
                </c:if>

                <li><label>签到时间：</label><input name="startDate" type="text" class="scinput" onfocus="WdatePicker()"/>
                </li>
                <li><label>签退时间：</label><input name="endDate" type="text" class="scinput" onfocus="WdatePicker()"/></li>
                <li><label>&nbsp;</label><input id="selBut" name="" type="button" class="scbtn" value="查询"
                                                onclick="recordList()"/></li>
            </ul>
            <table class="tablelist">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>员工姓名</th>
                    <th>签到时间</th>
                    <th>签退时间</th>
                    <th>上班时间(h)</th>
                </tr>
                </thead>
                <tbody id="userList">
                </tbody>
            </table>
            <div class="pagin" style="float: left;">
            </div>
            <div id="News-Pagination" style="float: right;margin-top: 10px;"></div>
        </div>
    </div>
</div>
</body>
</html>
