<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý giảng viên</title>
</head>
<body>
<form action="/gv/search" method="GET">
    Tên: <input type="text" name="ten"><br><br>
    Tuổi:<br><br>
    Min: <input type="number" name="minAge"><br><br>
    Max: <input type="number" name="maxAge"/><br><br>
    <button type="submit">Search</button>
</form>


<br>

<%--
  Tên field (msgv, ten, tuoi, queQuan, gioiTinh) phải trùng tên thuộc tính trong class GiangVien
  vì servlet dùng BeanUtils.populate.
--%>
<form action="/gv/add" method="POST">
    //request.setAttribute("gv", gv);
    Mã GV: <input type="text" name="msgv"/>">
    Họ tên: <input type="text" name="ten"/>"><br><br>
    Tuổi: <input type="number" name="tuoi"/>">
    Quê quán: <input type="text" name="queQuan"/>"><br><br>
    Giới tính:
    Nam <input type="radio" name="gioiTinh">
    Nữ <input type="radio" name="gioiTinh">
    <br><br>
    <button type="submit">Add</button>
</form>
<br>

<table border="1" cellspacing="1" cellpadding="6">
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
<%--    request.setAttribute("dsGv", gvR.getAll()); showGv - servlet--%>
    <c:forEach items="${dsGv}" var="g">
        <tr>
            <td><c:out value="${g.msgv}"/></td>
            <td><c:out value="${g.ten}"/></td>
            <td>${g.tuoi}</td>
            <td><c:out value="${g.queQuan}"/></td>
            <td>${g.gioiTinh ? 'Nam' : 'Nữ'}</td>
            <td>
                <form action="/gv/detail" method="get">
                    <input type="hidden" name="id" value="${g.id}">
                    <button type="submit">Detail</button>
                </form>
                <form action="/gv/view-update" method="get">
                    <input type="hidden" name="id" value="${g.id}">
                    <button type="submit">Update</button>
                </form>
                <form action="/gv/delete" method="get">
                    <input type="hidden" name="id" value="${g.id}">
                    <button type="submit">Remove</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>

<c:if test="${not empty gv}">
    <h3>Chi tiết giảng viên</h3>

    <p>Mã GV: ${gv.msgv}</p>
    <p>Họ tên: ${gv.ten}</p>
    <p>Tuổi: ${gv.tuoi}</p>
    <p>Quê quán: ${gv.queQuan}</p>
    <p>Giới tính: ${gv.gioiTinh ? 'Nam' : 'Nữ'}</p>
</c:if>

<br>
</body>
</html>
