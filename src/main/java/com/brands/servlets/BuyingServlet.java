package com.brands.servlets;

import com.brands.dto.UserImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class BuyingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        String Shippingaddress = request.getParameter("address");
        UserImp userImp = new UserImp();
        if (Shippingaddress.equals("")){
            Shippingaddress = userImp.getUserById(userId).getAddress();
        }
        PrintWriter out = response.getWriter();
        boolean isBought = userImp.updateCreditWhenBuying(userId,Shippingaddress);
        if (isBought) {
            out.write("Credit is Enough, so Cart is Bought !");
        } else {
            out.write("Credit is not Enough, so Cart is not Bought !");
        }

    }
    //RemoveFromCartServlet
    //AddToCartServlet
}
