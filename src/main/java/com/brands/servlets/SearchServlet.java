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

public class SearchServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        System.out.println("in home servlet");
        ProductImp productImp = new ProductImp();
        String search = request.getParameter("search");
        System.out.println("in search servlet   " + search);
        if (search != null) {
            List<Products> productsList = productImp.searchProductByName(search);
            List<Products> list = new ArrayList<>();
            for (Products products : productsList) {
                if (products.getImage() != null) {
                    String base64Image = getEncoder().encodeToString(products.getImage());
                    products.setImageName(base64Image);
                }
                list.add(products);
            }
            request.setAttribute("productsList", list);
            RequestDispatcher dispatcher = request.getRequestDispatcher("indexLogin.jsp");
            dispatcher.include(request, response);
        }

    }

}

