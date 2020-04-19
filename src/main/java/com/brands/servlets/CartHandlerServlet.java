package com.brands.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import  com.brands.dto.UserImp;

public class CartHandlerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        HttpSession session=request.getSession();
        int userId =(int) session.getAttribute("userId");
        UserImp userImp = new UserImp();
        PrintWriter out = response.getWriter();
        if (userImp.addCredit(code,userId)){
            out.write("Valid Code !");
        }else {
            out.write("Not a Valid Code !");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        int userId =(int) session.getAttribute("userId");
        UserImp userImp = new UserImp();
        Double credit = userImp.getUserCredit(userId);
        PrintWriter out = response.getWriter();
        out.write("Your Credit : "+credit);
    }
}
