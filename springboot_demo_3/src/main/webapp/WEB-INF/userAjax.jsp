<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">

        /*$(function x() {
            //alert("jjk");
            $.get("/findAjax", function (result) {
                createRowMessage(result);
            });

        });*/
        $.ajax({
            type: "GET",
            url: "/findAjax",
            success: function (result) {
                $(result).each((index, item) => {
                    $("#table1").append(doCreateRows(item));
                });

            },
            error: function(result){
                alert("请求失败");

            }

        });
        /*$.get("/findAjax", function (result) {
            createRowMessage(result);
        });*/

        function createRowMessage(result) {

            //console.log("result"+result);

            var table = $("#table1");
            result.forEach((item) => {
                table.append(doCreateRows(item))
            });
        }

        function doCreateRows(item) {

            var id = item.id;
            var name = item.name;
            var age = item.age;
            var sex = item.sex;
            console.log(id);
            return "<tr align='center'>" + "<td>" + id + "</td>" + "<td>" + name + "</td>" +
                "<td>" + age + "</td>" + "<td>" + sex + "</td>" + "</tr>";

        }
    </script>
    <title>您好Springboot</title>
</head>
<body>
<table border="1px" width="65%" align="center" id="table1">
    <tr>
        <td colspan="6" align="center"><h3>学生信息</h3></td>
    </tr>
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
        <th></th>
    </tr>

    <%--<c:forEach items="${userList}" var="u">
        <tr>
            <th>${u.id}</th>
            <th>${u.name}</th>
            <th>${u.age}</th>
            <th>${u.sex}</th>
        </tr>
    </c:forEach>--%>
</table>
</body>
</html>