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
        String name =request.getParameter("name");
        String category =request.getParameter("category");
        String photo = request.getParameter("photo");
        String quantity = request.getParameter("quantity");
        System.out.println("category : "+category);

         String price =request.getParameter("price");
        String description =request.getParameter("description");
//        System.out.println("des "+description);
//        System.out.println("pp ben "+cost);
       double prices=Double.parseDouble(price);

        InputStream inputStream = null; // input stream of the upload file

        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        String fileName = extractFileName(filePart);
        System.out.println("image name >> "+fileName);
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
//        products.setImageName(extractFileName(filePart));
        products.setQuantity(Integer.valueOf(quantity));
        ProductDto productDto =new ProductImp();
        productDto.addProduct(products);

        response.sendRedirect("AdminAddProduct?key=value");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        if (key.equals("value")) {
            RequestDispatcher rd = request.getRequestDispatcher("ManageProduct");
            rd.include(request, response);
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
