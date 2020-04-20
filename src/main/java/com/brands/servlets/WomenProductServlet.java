package com.brands.servlets;

import com.brands.dao.Products;
import com.brands.dto.ProductImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static java.util.Base64.getEncoder;

public class WomenProductServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductImp productImp =new ProductImp();

        List<Products> womenProductsList =productImp.getAllProductsByCategoryId(2);
        List<Products> womenList=new ArrayList<>();
        for(Products products:womenProductsList){
            if(products.getImage() != null) {
                String base64Image = getEncoder().encodeToString(products.getImage());
                products.setImageName(base64Image);
            }
            womenList.add(products);
        }
        request.setAttribute("productsList", womenList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("indexLogin.jsp");
            dispatcher.include(request, response);


    }

}