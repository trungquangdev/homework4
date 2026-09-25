package com.example.homework4;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "giangVienServlet", urlPatterns = { "/search","/add"} ) //nhieu duong dan cho 1 servlet

public class GiangVienServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String ten = request.getParameter("ten");
        String minAge = request.getParameter("minAge");
        String maxAge = request.getParameter("maxAge");
        String sex = request.getParameter("sex");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String maGv= req.getParameter("maGv");
        String hoTen = req.getParameter("hoTen");
        String tuoi = req.getParameter("tuoi");
        String queQuan = req.getParameter("queQuan");
        String sex = req.getParameter("sex");
    }
}