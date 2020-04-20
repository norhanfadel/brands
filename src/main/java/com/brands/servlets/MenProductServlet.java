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

public class MenProductServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        ProductImp productImp =new ProductImp();
        List<Products> menProductsList =productImp.getAllProductsByCategoryId(1);
        List<Products> menList=new ArrayList<>();
        for(Products products:menProductsList){
            if(products.getImage() != null) {
                String base64Image = getEncoder().encodeToString(products.getImage());
                products.setImageName(base64Image);
            }
            menList.add(products);
        }
        request.setAttribute("productsList", menList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("indexLogin.jsp");
            dispatcher.forward(request, response);


    }

}