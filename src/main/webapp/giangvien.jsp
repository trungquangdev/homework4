<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

</h1>
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
</body>
</html>