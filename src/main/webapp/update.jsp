<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập nhật giảng viên</title>
</head>
<body>

<h3>Cập nhật giảng viên</h3>
<%--
  Tên field phải trùng tên thuộc tính trong GiangVien (BeanUtils.populate).
--%>
<form action="/gv/update" method="POST">
    <input type="hidden" name="id" value="${gv.id}">

    Mã GV: <input type="text" name="msgv"
                  value="<c:out value='${gv.msgv}'/>"><br><br>
    Họ tên: <input type="text" name="ten"
                   value="<c:out value='${gv.ten}'/>"><br><br>
    Tuổi: <input type="number" name="tuoi"
                 value="<c:out value='${gv.tuoi}'/>"><br><br>
    Quê quán: <input type="text" name="queQuan"
                     value="<c:out value='${gv.queQuan}'/>"><br><br>
    Giới tính:
    Nam <input type="radio" name="gioiTinh" value="true"
        ${gv.gioiTinh ? 'checked' : ''}>
    Nữ <input type="radio" name="gioiTinh" value="false"
        ${gv.gioiTinh ? 'checked' : ''}>
    <br><br>

    <button type="submit">Update</button>
    <a href="/gv/show">Quay lại</a>
</form>

</body>
</html>
