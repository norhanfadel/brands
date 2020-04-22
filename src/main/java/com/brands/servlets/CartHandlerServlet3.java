package com.brands.servlets;

import com.brands.dto.OrdersImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CartHandlerServlet3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //("userId="+userId+"&prodId"+prodId+"&quantitiy"+quantitiy)
        int userId = Integer.parseInt(request.getParameter("userId"));
        int prodId = Integer.parseInt(request.getParameter("prodId"));
        int quantitiy = Integer.parseInt(request.getParameter("quantitiy"));
        System.out.println(userId+" "+prodId+""+quantitiy);
        OrdersImp ordersImp = new OrdersImp() ;
        boolean isEnough = ordersImp.updateQuantityByProductId(prodId,userId,quantitiy);
        PrintWriter out = response.getWriter();
        out.write(""+isEnough);
    }
}
