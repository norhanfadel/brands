package com.brands.servlets;

import com.brands.dao.Category;
import com.brands.dao.Products;
import com.brands.dto.CategoryDto;
import com.brands.dto.CategoryImpl;
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
import java.util.Base64;
import java.util.Date;
import java.util.List;

@MultipartConfig
public class AdminAddProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("upppp");
        String name =request.getParameter("name");
        String category =request.getParameter("category");

        System.out.println("cat "+category);

         String price =request.getParameter("price");
        String description =request.getParameter("description");
//        System.out.println("des "+description);
//        System.out.println("pp ben "+cost);
       double prices=Double.parseDouble(price);

        InputStream inputStream = null; // input stream of the upload file

        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        byte[] bytes = IOUtils.toByteArray(inputStream);


        CategoryDto categoryDto=new CategoryImpl();
        Category categoryByName = categoryDto.getCategoryByName(category);
        Products products=new Products(categoryByName,new Date(),name,prices);
        products.setDescription(description);
        products.setImage(bytes);
        ProductDto productDto =new ProductImp();
        productDto.addProduct(products);


        //  request.setAttribute("allProducts",allProducts);
        RequestDispatcher rd = request.getRequestDispatcher("manageProduct.jsp");
        rd.forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
