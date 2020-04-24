package com.brands.servlets;

import com.brands.dto.OrdersImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddToCartServlet extends HttpServlet {
    private int userId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int userId = Integer.parseInt( request.getParameter("userId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        OrdersImp ordersImp = new OrdersImp();
        System.out.println(productId + "            " + userId );
        boolean isAdded = ordersImp.updateQuantityByProductId(productId, userId, 1);
        out.println(""+isAdded);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int userId = Integer.parseInt( request.getParameter("userId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        OrdersImp ordersImp = new OrdersImp();
        System.out.println(productId + "            " + userId + "             "+quantity);
        boolean isAdded = ordersImp.updateQuantityByProductId(productId, userId, quantity);
        out.println(""+isAdded);
    }
}
