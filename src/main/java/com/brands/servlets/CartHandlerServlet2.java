package com.brands.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import com.brands.dao.OrderDetails;
import com.brands.dao.Users;
import  com.brands.dto.UserImp;

import static java.util.Base64.getEncoder;

public class CartHandlerServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        UserImp userImp = new UserImp();
        PrintWriter out = response.getWriter();
        if (userImp.getCart(userId).getOrderNum() == 0) {
            //out.write("empty cart");
            session.setAttribute("cartItems",null);
            Double total = userImp.getCart(userId).getAmount();
            session.setAttribute("totalAmount",total);
            RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
            dispatcher.forward(request, response);
        } else {
            Set<OrderDetails> items = userImp.getCart(userId).getOrderDetailses();
            for (OrderDetails item : items
            ) {
                String base64Image = getEncoder().encodeToString(item.getProducts().getImage());
                item.getProducts().setImageName(base64Image);
                System.out.println(item.getProducts());
            }
            session.setAttribute("cartItems", items);
            Users user = userImp.getUserById(userId);
            session.setAttribute("userAddress",user.getAddress());
            Double total = userImp.getCart(userId).getAmount();
            session.setAttribute("totalAmount",total);
            out.write("cart and address sent !");
            RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
            dispatcher.forward(request, response);
        }
    }
}
