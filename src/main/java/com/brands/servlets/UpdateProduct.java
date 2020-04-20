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

import static java.util.Base64.getEncoder;

public class UpdateProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("inside update product servlet");
        String productIDToEdit = request.getParameter("productToEdit");
        int idToEdit = Integer.parseInt(productIDToEdit);
        ProductDto productDto =new ProductImp();
        Products oldPorduct = productDto.getProductById(idToEdit);
       // productDto.updateProduct(oldPorduct);
        String base64Image = getEncoder().encodeToString(oldPorduct.getImage());
        request.setAttribute("productOldName",oldPorduct.getName());
        request.setAttribute("productOldPrice",oldPorduct.getPrice());
        request.setAttribute("productOldQuantity",oldPorduct.getQuantity());
//        request.setAttribute("productOldImageName",oldPorduct.getImageName());
        request.setAttribute("productOldImageName",base64Image);
        request.setAttribute("productOldDescription",oldPorduct.getDescription());
        request.setAttribute("productID",Integer.valueOf(idToEdit));
        System.out.println("done");
        RequestDispatcher rd = request.getRequestDispatcher("updateProduct.jsp");
        rd.include(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
