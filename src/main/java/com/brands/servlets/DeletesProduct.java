package com.brands.servlets;

import com.brands.dto.ProductDto;
import com.brands.dto.ProductImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletesProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productID = request.getParameter("productID");
        Integer id = Integer.valueOf(productID);
        ProductDto productDto = new ProductImp();
        boolean b = productDto.deleteProduct(id);
        System.out.println(b);
        response.sendRedirect("DeletesProduct?key=value");
//        RequestDispatcher rd = request.getRequestDispatcher("ManageProduct");
//        rd.include(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        if(key.equals("value")){
            RequestDispatcher rd = request.getRequestDispatcher("ManageProduct");
            rd.include(request,response);
        }
    }
}
