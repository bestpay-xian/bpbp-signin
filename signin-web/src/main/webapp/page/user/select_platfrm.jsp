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
    <script type="text/javascript" src="<%=path%>/resources/js/jquery.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/select-ui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">

        $(function(){
            platformList();


        });



          function del( jsObj) {
              if (confirm("确定要提的操作吗？")) {//如果选择是，返回true ，那么就把页面转向指定链接

              } else {
                  return false;
              }
              $.ajax({
                  type: "post",
                  dataType: "json",
                  url: "<%=path%>/platform/deletePlatform.do",
                  data: {"platformId":jsObj},
                  success: function (msg) {
                      platformList();
                      if (msg.type == "error") {
                          alert(msg.info);
                      }

                  }
              });
           };




        function platformList(){
            $.ajax({
                type: "post",
                dataType: "json",
                url: "<%=path%>/platform/selectAllPlatformByName.do",
                success: function (msg) {
                    $("#addBussessin").html("");
                    if (msg.type == "error") {
                        alert(msg.info);
                    } else {
                        $.each(msg.result.lists, function (index, jsonObj) {
                            var tme=jsonObj.platformId;
                            $("#addBussessin").append("<tr id="+tme+"><td>"+tme +"</td><td>" +jsonObj.name + "</td><td  onclick='del(\""+tme+"\")'>"+"<font style='cursor:pointer'>删除</font></td><td ><form action='<%=path%>/platform/selectPlatformById.do' method='"+"post"+"' ><input type='hidden' name='platformId' value='"+tme+" '><input type='submit'  style='cursor:pointer' value='修改 '></form></td></tr>");


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
        <li><a href="#">系统设置</a></li>
    </ul>
</div>

<div class="formbody">
    <div id="bussessin" class="usual">
        <div id="tab2" class="tabson">
            <table class="tablelist">
                <thead id="headBussessin">
                <tr>
                    <th>厂商ID</th>
                    <th>厂商</th>
                    <th>操作</th>
                    <th>修改</th>
                </tr>
                </thead>
                <tbody id="addBussessin">

                </tbody>
            </table>

        </div>
    </div>
</div>
</body>
</html>
