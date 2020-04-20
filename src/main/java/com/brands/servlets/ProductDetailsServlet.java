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
import java.util.List;

public class ProductDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String pid=request.getParameter("pid");
      int p_id=Integer.parseInt(pid);
        ProductDto productDto =new ProductImp();
       Products products= productDto.getProductById(p_id);
        request.setAttribute("productDetails",products);

        RequestDispatcher rd = request.getRequestDispatcher("showProductDetails.jsp");
        rd.include(request,response);
    }
}
