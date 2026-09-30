<%--
  Created by IntelliJ IDEA.
  User: Trung
  Date: 9/30/2026
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/gv/add" method="POST">
    Mã GV: <input type="text" name="msgv">
    Họ tên: <input type="text" name="ten"><br><br>
    Tuổi: <input type="number" name="tuoi">
    Quê quán: <input type="text" name="queQuan"><br><br>
    Giới Tính:<br> Nam <input type="radio" name="gioiTinh" value="true">  Nữ <input type="radio" name="gioiTinh" value="false"> <br><br>
    <button type="submit">Add</button>
</form>
</body>
</html>
