<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý giảng viên</title>
</head>
<body>

<%-- ============ Form tìm kiếm ============ --%>
<form action="${ctx}/gv/search" method="GET"
      style="border:1px solid #ccc; padding:15px; width:400px;">
    Tên: <input type="text" name="ten" value="<c:out value='${param.ten}'/>"><br><br>
    Tuổi:<br><br>
    Min: <input type="number" name="minAge" value="<c:out value='${param.minAge}'/>"><br><br>
    Max: <input type="number" name="maxAge" value="<c:out value='${param.maxAge}'/>"><br><br>
    <button type="submit">Search</button>
</form>

<br>

<%-- Báo lỗi validate (nếu có) --%>
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<%--
  ============ Form Add (cũng là form "ở giữa" được fill khi bấm Detail) ============
  - Bấm Detail: servlet gửi attribute "gv" -> điền từ gv
  - Add bị lỗi validate: không có "gv" -> điền lại từ param (giữ nguyên dữ liệu vừa nhập)
  Tên field (msgv, ten, tuoi, queQuan, gioiTinh) phải trùng tên thuộc tính trong class GiangVien
  vì servlet dùng BeanUtils.populate.
--%>
<form action="${ctx}/gv/add" method="POST">
    Mã GV: <input type="text" name="msgv"
                  value="<c:out value='${empty gv ? param.msgv : gv.msgv}'/>">
    &nbsp;&nbsp;&nbsp;
    Họ tên: <input type="text" name="ten"
                   value="<c:out value='${empty gv ? param.ten : gv.ten}'/>"><br><br>
    Tuổi: <input type="number" name="tuoi"
                 value="<c:out value='${empty gv ? param.tuoi : gv.tuoi}'/>">
    &nbsp;&nbsp;&nbsp;
    Quê quán: <input type="text" name="queQuan"
                     value="<c:out value='${empty gv ? param.queQuan : gv.queQuan}'/>"><br><br>
    Giới tính:
    Nam <input type="radio" name="gioiTinh" value="true"
${((not empty gv and gv.gioiTinh) or param.gioiTinh == 'true') ? 'checked' : ''}>
    Nữ <input type="radio" name="gioiTinh" value="false"
${((not empty gv and not gv.gioiTinh) or param.gioiTinh == 'false') ? 'checked' : ''}>
    <br><br>
    <button type="submit">Add</button>
</form>

<br>

<%-- ============ Bảng danh sách ============ --%>
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
    <c:forEach items="${dsGv}" var="g">
        <tr>
            <td><c:out value="${g.msgv}"/></td>
            <td><c:out value="${g.ten}"/></td>
            <td>${g.tuoi}</td>
            <td><c:out value="${g.queQuan}"/></td>
            <td>${g.gioiTinh ? 'Nam' : 'Nữ'}</td>
            <td>
                <form action="${ctx}/gv/detail" method="get" style="display:inline;">
                    <input type="hidden" name="id" value="${g.id}">
                    <button type="submit">Detail</button>
                </form>
                <form action="${ctx}/gv/view-update" method="get" style="display:inline;">
                    <input type="hidden" name="id" value="${g.id}">
                    <button type="submit">Update</button>
                </form>
                <form action="${ctx}/gv/delete" method="get" style="display:inline;"
                      onsubmit="return confirm('Bạn có chắc muốn xoá giảng viên này?');">
                    <input type="hidden" name="id" value="${g.id}">
                    <button type="submit">Remove</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
