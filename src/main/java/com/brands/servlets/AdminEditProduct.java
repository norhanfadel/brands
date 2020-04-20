package com.brands.servlets;

import com.brands.dao.Category;
import com.brands.dao.Products;
import com.brands.dto.ProductDto;
import com.brands.dto.ProductImp;
import org.apache.commons.io.IOUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@MultipartConfig
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
        int quantity = Integer.parseInt(request.getParameter("quantity"));


        System.out.println(description);
        /////////////////////////////////////
        InputStream inputStream = null;
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        }
        byte[] bytes = IOUtils.toByteArray(inputStream);

        ////////////////////////////////////
        Products oldProduct = productDto.getProductById(idToEdit);
        oldProduct.setName(name);
        oldProduct.setCreateDate(oldProduct.getCreateDate());
        oldProduct.setPrice(Double.valueOf(price));
        oldProduct.setCategory(oldProduct.getCategory());
        oldProduct.setDescription(description);
        oldProduct.setQuantity(quantity);
        oldProduct.setImage(bytes);
        productDto.updateProduct(oldProduct);
        response.sendRedirect("AdminEditProduct?key=value");
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
