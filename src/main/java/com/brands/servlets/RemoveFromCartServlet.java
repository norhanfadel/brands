package com.brands.servlets;

import com.brands.dto.OrdersImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class RemoveFromCartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        String productIdString = request.getParameter("productId");
        int productId = Integer.parseInt(productIdString);
        OrdersImp orderImp = new OrdersImp();
        boolean removed = orderImp.removeProductByProductIdFromCart(productId, userId);
        PrintWriter out = response.getWriter();
        if (removed == false) {
            out.write("false");
        } else {
            out.write("true");
        }
    }
}
