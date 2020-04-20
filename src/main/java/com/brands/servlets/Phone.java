package com.brands.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Phone  extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String phonee = request.getParameter("uPhone");
        System.out.println("phonee is=" + phonee);
        if (phonee.matches("^(01){1}\\d{9}")) {

            System.out.println("true-------");

        } else {
            System.out.println("false-------");
            out.println("NOt valid Phone Number");

        }
    }
}
