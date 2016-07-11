<%--
  Created by IntelliJ IDEA.
  User: cailonggang
  Date: 2016/5/24
  Time: 9:10
  To change this template use File | Settings | File Templates.
--%>
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
  <link href="<%=path%>/resources/css/pagination.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="<%=path%>/resources/js/jquery.js"></script>
  <script type="text/javascript" src="<%=path%>/resources/js/jquery-1.11.3.js"></script>
  <script type="text/javascript" src="<%=path%>/resources/js/jquery.idTabs.min.js"></script>
  <script type="text/javascript" src="<%=path%>/resources/js/select-ui.min.js"></script>
  <script type="text/javascript" src="<%=path%>/resources/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript" src="<%=path%>/resources/js/jquery.pagination.js"></script>
  <script type="text/javascript">

    $(function(){
      userList();
    });
    function handlePaginationClick(new_page_index, pagination_container) {
      $("#addDept").html("");
      $(".pagin").html("");
      var page = new_page_index + 1;
      $.ajax({
        type: "post",
        dataType: "json",
        url: "<%=path%>/dept/list.do",
        data: {"pageNo":page},
        success: function (msg) {
          $("#addDept").html("");
          if (msg.type == "error") {
            alert(msg.info);
          } else {
            $.each(msg.result.lists, function (index, n) {
              $("#addDept").append("<tr id="+ n.deptId+">" +
              "<td>"+ n.deptId +    "</td> " +
              "<td>"+ n.deptName +  "</td>" +
              "<td  onclick='del(\""+n.deptId+"\")'>"+"<font style='cursor:pointer'>删除</font></td><td ><form action='<%=path%>/dept/editDept.do' method='"+"post"+"' >" +
              "<input type='hidden' name='deptId' value='"+ n.deptId+" '><input type='submit' style='cursor:pointer' value='修改 '></form></td></tr>");

            });
            $(".pagin").append(
                    "<div class='message'>共<i class='blue'>&nbsp;" + msg.result.total + "&nbsp;</i>条记录，当前显示第&nbsp;<i class='blue'>" + msg.result.pageNo + "&nbsp;</i>页</div>" +
                    "<input id='total' type='hidden' value='" + msg.result.total + "'/>"
            );
          }
        }
      });
    }
    function del(deptId) {
      if (confirm("确定要删除操作吗？")) {//如果选择是，返回true ，那么就把页面转向指定链接
        $.ajax({
          type: "post",
          dataType: "json",
          url: "<%=path%>/dept/deleteDept.do",
          data: {"deptId":deptId},
          success: function (msg) {
            $("#addDept").html("");
            userList();
            if (msg.type == "error") {
              alert(msg.info);
            }

          }
        });
       }else{
       return false;
       }

    };
    function userList(){
      $.ajax({
        type: "post",
        dataType: "json",
        url: "<%=path%>/dept/list.do",
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
          $("#addDept").html("");
          if (msg.type == "error") {
            alert(msg.info);
          } else {
            $.each(msg.result.lists, function (index, n) {
              $("#addDept").append("<tr id="+ n.deptId+">" +
              "<td>"+ n.deptId +    "</td> " +
              "<td>"+ n.deptName +  "</td>" +
              "<td  onclick='del(\""+n.deptId+"\")'>"+"<font style='cursor:pointer'>删除</font></td><td ><form action='<%=path%>/dept/editDept.do' method='"+"post"+"' >" +
              "<input type='hidden' name='deptId' value='"+ n.deptId+" '><input type='submit' style='cursor:pointer' value='修改 '></form></td></tr>");

            });
            $(".pagin").append(
                    "<div class='message'>共<i class='blue'>&nbsp;" + msg.result.total + "&nbsp;</i>条记录，当前显示第&nbsp;<i class='blue'>" + msg.result.pageNo + "&nbsp;</i>页</div>" +
                    "<input id='total' type='hidden' value='" + msg.result.total + "'/>"
            );
          }
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
    <li><a href="#">部门查询</a></li>
  </ul>
</div>
<div class="formbody">
  <div id="bussessin" class="usual">
    <div id="tab2" class="tabson">
      <table class="tablelist">
        <thead id="headBussessin">
        <tr>
          <th>部门ID</th>
          <th>部门</th>
          <th>操作</th>
          <th>修改</th>
        </tr>
        </thead>
        <tbody id="addDept">
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

