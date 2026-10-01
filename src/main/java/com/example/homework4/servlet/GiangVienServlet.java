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

    private void detailGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        GiangVien gv= gvR.findById(id);
        request.setAttribute("gv", gv);   // giangvien.jsp dùng "gv" để điền vào form
        this.showGv(request, response);   // vẫn hiện lại table ở dưới
    }

    // mở TRANG MỚI (update.jsp)
    private void viewUpdateGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id=Long.valueOf(request.getParameter("id"));
        GiangVien gv=gvR.findById(id);
        request.setAttribute("gv", gv);
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }


    private void deleteGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id=Long.valueOf(request.getParameter("id"));
        GiangVien gv= gvR.findById(id);
        gvR.delete(gv);
        response.sendRedirect("/gv/show");
    }

    private void searchGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ten = request.getParameter("ten");

        request.setAttribute("dsGv", gvR.search(ten, null, null));
        request.getRequestDispatcher("/giangvien.jsp").forward(request, response);
    }


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

    private void addGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        GiangVien gv=new GiangVien();
        BeanUtils.populate(gv, request.getParameterMap());
        gvR.add(gv);
        response.sendRedirect(request.getContextPath() + "/gv/show"); // redirect: F5 không bị thêm lặp
    }

    private void updateGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        GiangVien gv = new GiangVien();
        BeanUtils.populate(gv, request.getParameterMap()); // có cả "id" từ ô hidden
        gvR.update(gv);
        response.sendRedirect(request.getContextPath() + "/gv/show");
    }

}
