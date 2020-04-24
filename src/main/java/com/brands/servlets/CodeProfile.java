package com.brands.servlets;

import com.brands.dto.UserImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CodeProfile  extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String code = request.getParameter("codeedit");
        UserImp userImp= new UserImp();

        if(userImp.checkCredit(code)) {

            out.println("voucher code valid");
        }else {

            out.println("voucher code not valid");
        }
    }
}

