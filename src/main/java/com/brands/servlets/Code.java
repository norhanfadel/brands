package com.brands.servlets;

import com.brands.dao.Users;
import com.brands.dto.MySessionFactory;
import com.brands.dto.UserImp;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Code  extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String code = request.getParameter("welcomeCode");
        UserImp userImp= new UserImp();

        if(userImp.checkCredit(code)) {

            out.println("voucher code valid");
        }else {

            out.println("voucher code not valid try it again from your profile ! ");
        }
    }
}

