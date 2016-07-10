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
    </div>
  </div>
</div>
</body>
</html>

