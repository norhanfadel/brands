package com.brands.servlets;

import com.brands.dao.Products;
import com.brands.dao.Users;
import com.brands.dto.ProductImp;
import com.brands.dto.UserImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static java.util.Base64.getEncoder;


public class HomeServlet  extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        System.out.println("in home servlet");
        ProductImp productImp =new ProductImp();
        List<Products> productsList =productImp.getAllProducts();
        List<Products> list=new ArrayList<>();
        for(Products products:productsList){
            String base64Image = getEncoder().encodeToString(products.getImage());
            products.setImageName(base64Image);
            list.add(products);
        }

        List<Products> menProductsList =productImp.getAllProductsByCategoryId(1);
        List<Products> menList=new ArrayList<>();
        for(Products products:menProductsList){
            String base64Image = getEncoder().encodeToString(products.getImage());
            products.setImageName(base64Image);
            menList.add(products);
        }

        List<Products> womenProductsList =productImp.getAllProductsByCategoryId(2);
        List<Products> womenList=new ArrayList<>();
        for(Products products:womenProductsList){
            String base64Image = getEncoder().encodeToString(products.getImage());
            products.setImageName(base64Image);
            womenList.add(products);
        }

        List<Products> kidsProductsList =productImp.getAllProductsByCategoryId(3);
        List<Products> kidsList=new ArrayList<>();
        for(Products products:kidsProductsList){
            String base64Image = getEncoder().encodeToString(products.getImage());
            products.setImageName(base64Image);
            kidsList.add(products);
        }
        request.setAttribute("productsList", list);
        request.setAttribute("menList", menList);
        request.setAttribute("womenList", womenList);
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

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}

