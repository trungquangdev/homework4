<%--
  Created by IntelliJ IDEA.
  User: Trung
  Date: 9/30/2026
  Time: 12:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${ctx}/giang-vien/update" method="POST">
    <input type="hidden" name="id" value="${empty gv ? param.id : gv.id}">

    Mã GV: <input type="text" name="maGv"
                  value="<c:out value='${empty gv ? param.maGv : gv.msgv}'/>"><br><br>
    Họ tên: <input type="text" name="hoTen"
                   value="<c:out value='${empty gv ? param.hoTen : gv.ten}'/>"><br><br>
    Tuổi: <input type="number" name="tuoi"
                 value="<c:out value='${empty gv ? param.tuoi : gv.tuoi}'/>"><br><br>
    Quê quán: <input type="text" name="queQuan"
                     value="<c:out value='${empty gv ? param.queQuan : gv.queQuan}'/>"><br><br>
    Giới tính:
    Nam <input type="radio" name="sex" value="true"
${((not empty gv and gv.gioiTinh) or param.sex == 'true') ? 'checked' : ''}>
    Nữ <input type="radio" name="sex" value="false"
${((not empty gv and not gv.gioiTinh) or param.sex == 'false') ? 'checked' : ''}>
    <br><br>

    <button type="submit">Update</button>
    <a href="${ctx}/giang-vien/hien-thi-tat-ca">Quay lại</a>
</form>
</body>
</html>
