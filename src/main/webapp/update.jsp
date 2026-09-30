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

<c:if test="${not empty error}">
    <p style="color:#ff0000;">${error}</p>
</c:if>

<%--
  Lần đầu mở (từ nút Update): có attribute "gv" -> điền từ gv.
  Bấm Update bị lỗi validate: không có "gv" -> điền lại từ param.
  Tên field phải trùng tên thuộc tính trong GiangVien (BeanUtils.populate).
--%>
<form action="/gv/update" method="POST">
    <input type="hidden" name="id" value="${empty gv ? param.id : gv.id}">

    Mã GV: <input type="text" name="msgv"
                  value="<c:out value='${empty gv ? param.msgv : gv.msgv}'/>"><br><br>
    Họ tên: <input type="text" name="ten"
                   value="<c:out value='${empty gv ? param.ten : gv.ten}'/>"><br><br>
    Tuổi: <input type="number" name="tuoi"
                 value="<c:out value='${empty gv ? param.tuoi : gv.tuoi}'/>"><br><br>
    Quê quán: <input type="text" name="queQuan"
                     value="<c:out value='${empty gv ? param.queQuan : gv.queQuan}'/>"><br><br>
    Giới tính:
    Nam <input type="radio" name="gioiTinh" value="true"
        ${((not empty gv and gv.gioiTinh) or param.gioiTinh == 'true') ? 'checked' : ''}>
    Nữ <input type="radio" name="gioiTinh" value="false"
        ${((not empty gv and not gv.gioiTinh) or param.gioiTinh == 'false') ? 'checked' : ''}>
    <br><br>

    <button type="submit">Update</button>
    <a href="${ctx}/gv/show">Quay lại</a>
</form>

</body>
</html>
