<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/resources/css/select.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/resources/css/pagination.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/select-ui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery.pagination.js"></script>
    <script src="<%=path%>/resources/js/jquery.validate.js" language="JavaScript"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/MD5.js"></script>
    <script type="text/javascript">
        $(function () {
            userList();
        });

        function handlePaginationClick(new_page_index, pagination_container) {
            var username = $("#username").val();
            var phone = $("#phone").val();

            $("#allUsers").html("");
            $(".pagin").html("");
            var page = new_page_index + 1;
            $.ajax({
                type: "post",
                url: "<%=path%>/employee/list.do",
                dataType: "json",
                data: {"pageNo": page, "name": username, "phone": phone},
                success: function (msg) {
                    $.each(msg.result.lists, function (index, n) {

                        if (msg.type == "error") {
                            alert(msg.info);
                        } else {
                            $("#allUsers").append(
                                    "<tr><td>" + n.employeeId + "</td>" +
                                    "<td>" + n.name + "</td>" +
                                    "<td>" + n.phone + "</td>" +
                                    "<td>" + n.qq + "</td>" +
                                    "<td>" + n.email + "</td>" +
                                    "<id=" + n.employeeId + "><td  onclick='del(\"" + n.employeeId + "\")'>" + "<font style='cursor:pointer'>删除</font></td>" +
                                    " <td >" + "<form action='<%=path%>/employee/editEmployee.do' method='" + "post" + "' >" +
                                    "<input type='hidden' name='employeeId' value='" + n.employeeId + " '><input type='submit' style='cursor:pointer' value='修改 '>" +
                                    "<id=" + n.employeeId + "><td  onclick='reset(\"" + n.employeeId + "\")'>" + "<font style='cursor:pointer'>重置密码</font></td></form></td>");
                        }
                    });

                    $(".pagin").append(
                            "<div class='message'>共<i class='blue'>&nbsp;" + msg.result.total + "&nbsp;</i>条记录，当前显示第&nbsp;<i class='blue'>" + msg.result.pageNo + "&nbsp;</i>页</div>" +
                            "<input id='total' type='hidden' value='" + msg.result.total + "'/>"
                    );
                }
            });
        }

        function del(employeeId) {
            if (confirm("确定要删除吗？")) {
                //如果选择是，返回true ，那么就把页面转向指定链接
            } else {
                return false;
            }
            $.ajax({
                type: "post",
                dataType: "json",
                url: "<%=path%>/employee/deleteEmployee.do",
                data: {"employeeId": employeeId},
                success: function (msg) {
                    userList();
                    if (msg.type == "error") {
                        alert(msg.info);
                    }
                }
            });
        }
        function reset(employeeId) {
            if (confirm("确定要重置密码吗？")) {
                //如果选择是，返回true ，那么就把页面转向指定链接
            } else {
                return false;
            }
            $.ajax({
                type: "post",
                dataType: "json",
                url: "<%=path%>/employee/resetPassword.do",
                data: {"employeeId": employeeId},
                success: function (msg) {
                    userList();
                    if (msg.type == "error") {
                        alert(msg.info);
                    }
                }
            });
        }
        function userList() {
            //清空上次查询记录
            $("#allUsers").html("");
            $(".pagin").html("");
            var username = $("#username").val();
            var phone = $("#phone").val();

            $.ajax({
                type: "post",
                url: "<%=path%>/employee/list.do",
                dataType: "json",
                data: {"name": username, "phone": phone},
                success: function (msg) {
                    $("#News-Pagination").pagination(msg.result.total, {
                        items_per_page: 10, // 每页显示多少条记录
                        current_page: msg.result.pageNo-1, // 当前显示第几页数据
                        num_display_entries: 3, // 分页显示的条目数
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
                            $("#allUsers").append(
                                    "<tr><td>" + n.employeeId + "</td>" +
                                    "<td>" + n.name + "</td>" +
                                    "<td>" + n.phone + "</td>" +
                                    "<td>" + n.qq + "</td>" +
                                    "<td>" + n.email + "</td>" +
                                    "<id=" + n.employeeId + "><td  onclick='del(\"" + n.employeeId + "\")'>" + "<font style='cursor:pointer'>删除</font></td>" +
                                    " <td >" + "<form action='<%=path%>/employee/editEmployee.do' method='" + "post" + "' >" +
                                    "<input type='hidden' name='employeeId' value='" + n.employeeId + " '><input type='submit' style='cursor:pointer' value='修改 '>" +
                                    "<id=" + n.employeeId + "><td  onclick='reset(\"" + n.employeeId + "\")'>" + "<font style='cursor:pointer'>重置密码</font></td></form></td>");
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
        <li><a href="#">员工查询</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div id="tab2" class="tabson">
            <ul class="seachform">
                <li><label>员工姓名：</label><input id="username" name="" type="text" class="scinput"/></li>
                <li><label>员工电话：</label><input id="phone" name="" type="text" class="scinput"/></li>
                <input type="text" id="total2"/>
                <li><label>&nbsp;</label><input id="selBut" onclick="userList()" type="button" class="scbtn" value="查询"/></li>
            </ul>
            <table class="tablelist">
                <thead>
                <tr>
                    <th>员工编号</th>
                    <th>员工姓名</th>
                    <th>员工电话</th>
                    <th>员工QQ</th>
                    <th>员工邮箱</th>
                    <th>删除</th>
                    <th>详情</th>
                    <th>重置密码</th>
                </tr>
                </thead>
                <tbody id="allUsers">
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
