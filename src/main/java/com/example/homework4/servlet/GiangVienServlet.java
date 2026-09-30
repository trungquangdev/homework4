package com.example.homework4.servlet;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import com.example.homework4.entity.GiangVien;
import com.example.homework4.repository.GiangVienRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet(name = "giangVienServlet", urlPatterns = {
        "/gv/show",
        "/gv/detail",
        "/gv/add",
        "/gv/update",
        "/gv/view-update",
        "/gv/delete",
        "/gv/search"
}) //nhieu duong dan cho 1 servlet

public class GiangVienServlet extends HttpServlet {
    private GiangVienRepository gvR = new GiangVienRepository();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uri = request.getRequestURI();
        if (uri.contains("/gv/show")) {
            this.showGv(request, response);
        } else if (uri.contains("/gv/detail")) {
            this.detailGv(request, response);
        } else if (uri.contains("/gv/view-update")) {
            this.viewUpdateGv(request, response);
        } else if (uri.contains("/gv/delete")) {
            this.deleteGv(request, response);
        } else if (uri.contains("/gv/search")) {
            this.searchGv(request, response);
        } else {
            response.sendError(404);
        }
    }

    // Hiển thị tất cả: load data lên table
    private void showGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("dsGv", gvR.getAll());
        request.getRequestDispatcher("/giangvien.jsp").forward(request, response);
    }

    // Detail: fill thông tin lên form ở giữa, KHÔNG mở trang mới
    private void detailGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GiangVien gv = timTheoId(request);
        if (gv == null) {
            response.sendRedirect(request.getContextPath() + "/gv/show");
            return;
        }
        request.setAttribute("gv", gv);   // giangvien.jsp dùng "gv" để điền vào form
        this.showGv(request, response);   // vẫn hiện lại table ở dưới
    }

    // View-update: mở TRANG MỚI (update.jsp) để sửa
    private void viewUpdateGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GiangVien gv = timTheoId(request);
        if (gv == null) {
            response.sendRedirect(request.getContextPath() + "/gv/show");
            return;
        }
        request.setAttribute("gv", gv);
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    // Remove: xoá giảng viên được chọn
    private void deleteGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GiangVien gv = timTheoId(request);
        if (gv != null) {
            gvR.delete(gv);
        }
        response.sendRedirect(request.getContextPath() + "/gv/show");
    }

    // Tìm kiếm: theo tên, tuổi min, tuổi max (ô nào trống thì bỏ qua)
    private void searchGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ten = request.getParameter("ten");
        Long min = parseLongHoacNull(request.getParameter("minAge"));
        Long max = parseLongHoacNull(request.getParameter("maxAge"));
        request.setAttribute("dsGv", gvR.search(ten, min, max));
        request.getRequestDispatcher("/giangvien.jsp").forward(request, response);
    }

    // ===================== POST =====================
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // phải đặt TRƯỚC khi đọc parameter, để tiếng Việt không bị lỗi
        String uri = req.getRequestURI();
        if (uri.contains("/gv/add")) {
            this.addGv(req, resp);
        } else if (uri.contains("/gv/update")) {
            this.updateGv(req, resp);
        } else {
            resp.sendError(404);
        }
    }

    // Add: validate -> thêm -> hiện ở dưới table
    private void addGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String loi = kiemTra(request);
        if (loi != null) {
            request.setAttribute("error", loi);
            this.showGv(request, response); // giữ nguyên dữ liệu đã nhập (JSP đọc lại từ param)
            return;
        }
        GiangVien gv = new GiangVien();
        BeanUtils.populate(gv, request.getParameterMap());
        gvR.add(gv);
        response.sendRedirect(request.getContextPath() + "/gv/show"); // redirect: F5 không bị thêm lặp
    }

    // Update: validate -> sửa
    private void updateGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String loi = kiemTra(request);
        if (loi != null) {
            request.setAttribute("error", loi);
            request.getRequestDispatcher("/update.jsp").forward(request, response);
            return;
        }
        GiangVien gv = new GiangVien();
        BeanUtils.populate(gv, request.getParameterMap()); // có cả "id" từ ô hidden
        gvR.update(gv);
        response.sendRedirect(request.getContextPath() + "/gv/show");
    }

    // ===================== Các hàm hỗ trợ =====================

    // Kiểm tra trống + tuổi phải là số dương. Hợp lệ -> null, sai -> trả về câu báo lỗi
    private String kiemTra(HttpServletRequest request) {
        if (laTrong(request.getParameter("msgv"))
                || laTrong(request.getParameter("ten"))
                || laTrong(request.getParameter("tuoi"))
                || laTrong(request.getParameter("queQuan"))
                || laTrong(request.getParameter("gioiTinh"))) {
            return "Vui lòng nhập đầy đủ tất cả các trường (không được để trống).";
        }
        try {
            if (Long.parseLong(request.getParameter("tuoi").trim()) <= 0) {
                return "Tuổi phải lớn hơn 0.";
            }
        } catch (NumberFormatException e) {
            return "Tuổi phải là số.";
        }
        return null;
    }

    // Lấy giảng viên theo parameter "id"; id sai hoặc không tồn tại -> null
    private GiangVien timTheoId(HttpServletRequest request) {
        try {
            return gvR.findById(Long.valueOf(request.getParameter("id")));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean laTrong(String s) {
        return s == null || s.trim().isEmpty();
    }

    private Long parseLongHoacNull(String s) {
        if (laTrong(s)) {
            return null;
        }
        try {
            return Long.valueOf(s.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
