<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2016/5/23
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<html>
<title>无标题文档</title>
<link href="<%=path%>/resources/css/pagination.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/resources/css/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/resources/css/select.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=path%>/resources/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/jquery-1.11.3.js"></script>
<script src="<%=path%>/resources/js/jquery-1.9.1.min.js" language="JavaScript"></script>
<script src="<%=path%>/resources/js/select-ui.min.js"></script>
<script src="<%=path%>/resources/js/jquery.validate.js" language="JavaScript"></script>
<script src="<%=path%>/resources/js/messages_cn.js" language="JavaScript"></script>

<script type="text/javascript" src="<%=path%>/resources/js/jquery.pagination.js"></script>
<script type="text/javascript">
  $(function() {
      selectTeam();
      $("#News-Pagination").pagination(${list.total}, {
          items_per_page: 10, // 每页显示多少条记录
          current_page: ${list.pageNo}-1, // 当前显示第几页数据
          num_display_entries: 3, // 分页显示的条目数
          next_text: "下一页",
          prev_text: "上一页",
          num_edge_entries: 2, // 连接分页主体，显示的条目数
          callback: handlePaginationClick
      });
  })
  function handlePaginationClick(new_page_index, pagination_container) {
      $("#addTeam").html("");
      $(".pagin").html("");
      var page = new_page_index + 1;
      $.ajax({
          type: "post",
          url: "<%=path%>/team/list.do",
          dataType: "json",
          data: {"pageNo": page},
          success: function (msg) {
              $.each(msg.result.lists, function (index, teamObj) {
                  if (msg.type == "error") {
                      alert(msg.info);
                  }
                  else {
                      $("#addTeam").append(
                              "<tr>" +
                              "<td>" + teamObj.teamId + "</td>" +
                              "<td>" + teamObj.centerName + "</td>" +
                              "<td>" + teamObj.teamName + "</td>" +
                              "<td onclick='del(\"" + teamObj.teamId + "\")'>" + "删除" + "</td>" +
                              "<td>" +
                              "<form action='<%=path%>/team/single.do' method='post' >" +
                              "<input type='hidden' name='teamId' value='" + teamObj.teamId + "'>" +
                              "<input type='hidden' name='centerId'  value='" + teamObj.centerId + "'>" +
                              "<input type='submit' value='修改'>" +
                              "</form>" +
                              "</td>" +
                              "</tr>");
                  }
              });
              $(".pagin").append(
                      "<div class='message'>共<i class='blue'>&nbsp;" + msg.result.total + "&nbsp;</i>条记录，当前显示第&nbsp;<i class='blue'>" + msg.result.pageNo + "&nbsp;</i>页</div>" +
                      "<input id='total' type='hidden' value='" + msg.result.total + "'/>"
              );
          }
      });
  }


  function selectTeam(){
      $.ajax({
          type:"post",
          dateType:"json",
          url: "<%=path%>/team/list.do",
          // date:{"pageNo":"pageNo"}
          success: function (msg) {
              if(msg.type=="error"){
                  alert(msg.info);
              }else{
                  $.each(msg.result.lists,function (index, teamObj) {
                      $("#addTeam").append(
                              "<tr>"+
                              "<td>"+teamObj.teamId+"</td>"+
                              "<td>"+teamObj.centerName+"</td>"+
                              "<td>"+teamObj.teamName+"</td>"+
                              "<td onclick='del(\""+teamObj.teamId+ "\")'>"+"删除"+"</td>"+
                              "<td>"+
                              "<form action='<%=path%>/team/single.do' method='post' >"+
                              "<input type='hidden' name='teamId' value='"+teamObj.teamId+"'>"+
                              "<input type='hidden' name='centerId'  value='"+teamObj.centerId+"'>"+
                              "<input type='submit' value='修改'>"+
                              "</form>"+
                              "</td>"+
                              "</tr>");
                  });
                  $(".pagin").append(
                          "<div class='message'>共<i class='blue'>&nbsp;" + msg.result.total + "&nbsp;</i>条记录，当前显示第&nbsp;<i class='blue'>" + msg.result.pageNo + "&nbsp;</i>页</div>" +
                          "<input id='total' type='hidden' value='" + msg.result.total + "'/>"
                  );

              }

          }

      });
  };


  function del(id) {

      alert(id);
      if (confirm("确定要提的操作吗？")) {//如果选择是，返回true ，那么就把页面转向指定链接

      } else {
          return false;
      }
      $.ajax({
          type: "post",
          //dateType:"json",
          data: {"teamId": id},
          url: "<%=path%>/team/delete.do",
          success: function (msg) {
              $("#addTeam").html("");
              $(".pagin").html("");

              selectTeam();
              if (msg.type == "error") {
                  alert(msg.info);
              }

          }
      });

  }

</script>
<head>
    <title></title>
</head>

<body>


<div class="place">
  <span>位置：</span>
  <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">系统设置</a></li>
  </ul>
</div>

<div class="formbody">
  <div id="team" class="usual">
    <div id="tab2" class="tabson">
      <table class="tablelist">
        <thead id="headBussessin">
        <tr>
          <th>团队号</th>
          <th>团队所属中心</th>
          <th>团队名称</th>
          <th>删除</th>
          <th>修改</th>
        </tr>
        </thead>
        <tbody id="addTeam">

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
