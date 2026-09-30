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
        "/gv/view-add",
        "/gv/update",
        "/gv/view-update",
        "/gv/delete",
        "/gv/search"
} ) //nhieu duong dan cho 1 servlet

public class GiangVienServlet extends HttpServlet {
    private GiangVienRepository gvR=new GiangVienRepository();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uri=request.getRequestURI();
        if(uri.contains("/gv/show")){
            this.showGv(request,response);
        }else if(uri.contains("/gv/detail")){
            this.detailGv(request,response);
        }else if(uri.contains("/gv/view-add")){
            this.viewAddGv(request,response);
        }else if (uri.contains("/gv/view-update")){
            this.viewUpdateGv(request,response);
        }else if (uri.contains("/gv/delete")){
            this.deleteGv(request,response);
        }else if(uri.contains("/gv/search")){
            this.searchGv(request,response);
        }else {
            response.sendError(404);
        }

    }

    private void searchGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    }

    private void deleteGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id =request.getParameter("id");
        GiangVien gv = gvR.findById(Long.valueOf(id));
        gvR.delete(gv);
        response.sendRedirect( "/gv/show");
    }

    private void viewUpdateGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");
        GiangVien gv = gvR.findById(Long.valueOf(id));
        request.setAttribute("g",gv);
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }



    private void viewAddGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    }



    private void detailGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");
        GiangVien gv= gvR.findById(Long.valueOf(id));
        request.getRequestDispatcher("/detail.jsp").forward(request,response);
    }

    private void showGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("dsGv", gvR.getAll());
        request.getRequestDispatcher("/giangvien.jsp").forward(request,response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.contains("/gv/add")){
            this.addGv(req,resp);
        }else if(uri.contains("/gv/update")){
            this.updateGv(req,resp);
        }
    }
    private void addGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        GiangVien gv = new GiangVien();
        BeanUtils.populate(gv,request.getParameterMap());
        gvR.add(gv);
        response.sendRedirect("/gv/show");
    }
    private void updateGv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    }
}