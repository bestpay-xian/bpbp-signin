<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="<%=path%>resources/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="<%=path%>resources/js/jquery.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $(".click").click(function () {
                $(".tip").fadeIn(200);
            });

            $(".tiptop a").click(function () {
                $(".tip").fadeOut(200);
            });

            $(".sure").click(function () {
                $(".tip").fadeOut(100);
            });

            $(".cancel").click(function () {
                $(".tip").fadeOut(100);
            });

        });
    </script>


</head>


<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">数据表</a></li>
        <li><a href="#">基本内容</a></li>
    </ul>
</div>

<div class="rightinfo">

    <table class="tablelist">
        <thead>
        <tr>
            <th><input name="" type="checkbox" value="" checked="checked"/></th>
            <th>编号<i class="sort"><img src="<%=path%>resources/images/px.gif"/></i></th>
            <th>标题</th>
            <th>用户</th>
            <th>籍贯</th>
            <th>发布时间</th>
            <th>是否审核</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input name="" type="checkbox" value=""/></td>
            <td>20130908</td>
            <td>王金平幕僚：马英九声明字字见血 人活着没意思</td>
            <td>admin</td>
            <td>江苏南京</td>
            <td>2013-09-09 15:05</td>
            <td>已审核</td>
            <td><a href="#" class="tablelink">查看</a> <a href="#" class="tablelink"> 删除</a></td>
        </tr>

        <tr>
            <td><input name="" type="checkbox" value=""/></td>
            <td>20130907</td>
            <td>温州19名小学生中毒流鼻血续：周边部分企业关停</td>
            <td>uimaker</td>
            <td>山东济南</td>
            <td>2013-09-08 14:02</td>
            <td>未审核</td>
            <td><a href="#" class="tablelink">查看</a> <a href="#" class="tablelink">删除</a></td>
        </tr>

        <tr>
            <td><input name="" type="checkbox" value=""/></td>
            <td>20130906</td>
            <td>社科院:电子商务促进了农村经济结构和社会转型</td>
            <td>user</td>
            <td>江苏无锡</td>
            <td>2013-09-07 13:16</td>
            <td>未审核</td>
            <td><a href="#" class="tablelink">查看</a> <a href="#" class="tablelink">删除</a></td>
        </tr>

        </tbody>
    </table>

</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

</body>

</html>
