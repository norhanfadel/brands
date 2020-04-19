package com.brands.servlets;

import com.brands.dao.Users;
import com.brands.dto.UserImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class logOut extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        UserImp userImp = new UserImp();

        HttpSession session = request.getSession();

        int paramValue = (int) session.getAttribute("userId");
        System.out.println("paramValue" + paramValue);
        if (paramValue != 0) {
            System.out.println("paramValue---" + paramValue);
            Users user2 = userImp.getUserById(paramValue);
            userImp.updateStatus(user2.getUserId());
            response.sendRedirect("login.jsp");
            System.out.println("user---" + user2);
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}










