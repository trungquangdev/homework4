<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<br/>

<form action="search" method="GET">
    Tên: <input type="text" name="ten"><br><br>
    Tuổi:<br>
    Min: <input type="number" name="minAge"><br><br>
    Max: <input type="number" name="maxAge"><br><br>
    Giới Tính:<br> Nam <input type="radio" name="sex" value="true">  Nữ <input type="radio" name="sex" value="false"> <br><br>
    <button type="submit">Search</button>
</form>

<br><hr><br>


<form action="add" method="POST">
    Mã GV: <input type="text" name="maGv">
    Họ tên: <input type="text" name="hoTen"><br><br>
    Tuổi: <input type="number" name="tuoi">
    Quê quán: <input type="text" name="queQuan"><br><br>
    Giới Tính:<br> Nam <input type="radio" name="sex" value="true">  Nữ <input type="radio" name="sex" value="false"> <br><br>
    <button type="submit">Add</button>
</form>
<br><br>
<table border="1" cellspacing="1" cellpadding="10">
    <thead>
    <tr>
        <th>Mã GV</th>
        <th>Họ Tên</th>
        <th>Tuổi</th>
        <th>Quê quán</th>
        <th>Giới tính</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${dsGv}" var="g">
            <tr>
                <td>${g.msgv}</td>
                <td>${g.ten}</td>
                <td>${g.tuoi}</td>
                <td>${g.queQuan}</td>
                <td>${g.gioiTinh}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>