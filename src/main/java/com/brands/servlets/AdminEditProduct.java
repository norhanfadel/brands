package com.brands.servlets;

import com.brands.dao.Category;
import com.brands.dao.Products;
import com.brands.dto.ProductDto;
import com.brands.dto.ProductImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AdminEditProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDto productDto = new ProductImp();
        String productIDToEdit = request.getParameter("productID");
        int idToEdit = Integer.parseInt(productIDToEdit);
        System.out.println("product ID : "+idToEdit);
        String name = request.getParameter("name");
        System.out.println(name);
        String price = request.getParameter("price");
        System.out.println(price);
        String description = request.getParameter("description");
        System.out.println(description);
        Products oldProduct = productDto.getProductById(idToEdit);
        oldProduct.setName(name);
        oldProduct.setCreateDate(oldProduct.getCreateDate());
        oldProduct.setPrice(Double.valueOf(price));
        oldProduct.setCategory(oldProduct.getCategory());
        oldProduct.setDescription(description);

        productDto.updateProduct(oldProduct);
        RequestDispatcher rd = request.getRequestDispatcher("manageProduct.jsp");
        rd.include(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
