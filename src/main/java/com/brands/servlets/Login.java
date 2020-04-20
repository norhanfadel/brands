package com.brands.servlets;

import com.brands.dao.Products;
import com.brands.dao.Users;
import com.brands.dto.ProductImp;
import com.brands.dto.UserDto;
import com.brands.dto.UserImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static java.util.Base64.getEncoder;

public class Login extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        RequestDispatcher dispatcher =request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request,response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");

        String password = request.getParameter("password");

        System.out.println("password is=" + password);

        Users user = new Users();
        user.setEmail(email);
        user.setPassword(password);

        UserImp userImp = new UserImp();
        if (userImp.loginNour(email,password)) {

            System.out.println("valid");
           Users user2= userImp.getUserById(userImp.getUserIdByMail(email));

     //      response.sendRedirect("indexLogin.jsp?name"+email+"&password"+password);


            ProductImp productImp = new ProductImp();
            List<Products> productsList = productImp.getAllProducts();
            List<Products> list = new ArrayList<>();
            for (Products products : productsList) {
                String base64Image = getEncoder().encodeToString(products.getImage());
                products.setImageName(base64Image);
                list.add(products);
            }
            request.setAttribute("productsList", list);
            String paramName1= user2.getUserName();
            String role=user2.getUserRole();
            int id=user2.getUserId();
            request.setAttribute("username",paramName1);
            request.setAttribute("role",role);
            HttpSession session=request.getSession();
            session.setAttribute("userId",id);//as it will be needed later
            String name = user2.getUserName();
            session.setAttribute("nameprofile",name);
            request.setAttribute("id",id);
            RequestDispatcher dispatcher =request.getRequestDispatcher("indexLogin.jsp");
            dispatcher.forward(request,response);


        } else {
            String paramName="Sorry UserName or Password Error!";

            request.setAttribute("user",paramName);
            RequestDispatcher dispatcher =request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request,response);



        }

    }

}
