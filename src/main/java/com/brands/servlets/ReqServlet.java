package com.brands.servlets;

import com.brands.dao.Category;
import com.brands.dto.CategoryDto;
import com.brands.dto.CategoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ReqServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String s = request.getParameter("category");
        CategoryDto categoryDto=new CategoryImpl();
        Category categoryByName = categoryDto.getCategoryByName(s);

        System.out.println("ch"+s);
        if (categoryByName==null) {

            out.println("Invalid category");
        } else {

            //out.println("not valid");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
