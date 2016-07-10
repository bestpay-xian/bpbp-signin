<%--
  Created by IntelliJ IDEA.
  User: yyli
  Date: 2016/5/17
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" href="favicon.ico" type="image/x-icon" />
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>用户列表</title>
  <script type="text/javascript" src="${path}/resources/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript">
    $(function(){
      $("#refesh").click(function(){
        $.ajax({
          type: "post",
          url: "${base}/user/refeshList.do",
          dataType: "json",
          success: function(msg){
            $("table tr:gt(2)").remove();
            $.each(msg, function(i, n){
              $("table").append("<tr><td>"+n.uid+"</td>"+
              "<td>"+n.uname+"</td><td>"+n.upass+"</td>"+
              "<td><a href='delete.do?uid="+n.uid+"'>X</a>&nbsp;&nbsp;&nbsp;&nbsp;"+
              "<a href='findById.do?uid="+n.uid+"'>U</a></td></tr>");
            });
          }
        });
      });
    })
  </script>
</head>
<body>
<table border="1">
  <tr>
    <td colspan="2"><a href='<c:url value="/user/add.do"/>'>增加用户</a></td>
    <td colspan="2"><a id="refesh" style="cursor: pointer;">刷新列表</a></td>
  </tr>
  <tr>
    <td>员工id</td>
    <td>员工姓名</td>
    <td>员工密码</td>
    <td>操作</td>
  </tr>
  <c:forEach items="${list}" var="user">
    <tr>
      <td>${user.uid}</td>
      <td>${user.uname}</td>
      <td>${user.upass}</td>
      <td><a href="<c:url value="delete.do?uid=${user.uid}"/>">D</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="<c:url value="findById.do?uid=${user.uid}"/>">U</a></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
