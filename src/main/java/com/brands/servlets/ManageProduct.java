package com.brands.servlets;

import com.brands.dao.Products;
import com.brands.dto.ProductDto;
import com.brands.dto.ProductImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ManageProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDto productDto =new ProductImp();
        List<Products> allProducts = productDto.getAllProducts();
        request.setAttribute("allProducts",allProducts);
        RequestDispatcher rd = request.getRequestDispatcher("manageProduct.jsp");
        rd.include(request,response);
    }
}
