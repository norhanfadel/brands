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

public class KidsProductServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        ProductImp productImp =new ProductImp();


        List<Products> kidsProductsList =productImp.getAllProductsByCategoryId(3);
        List<Products> kidsList=new ArrayList<>();
        for(Products products:kidsProductsList){
            String base64Image = getEncoder().encodeToString(products.getImage());
            products.setImageName(base64Image);
            kidsList.add(products);
        }
        request.setAttribute("kidsList", kidsList);
        if(request.getParameter("login") != null){
            System.out.println("jkhkhkj");
            RequestDispatcher dispatcher = request.getRequestDispatcher("indexLogin.jsp");
            dispatcher.include(request, response);
        }else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.include(request, response);
        }


    }

}