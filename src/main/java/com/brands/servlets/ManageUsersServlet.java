package com.brands.servlets;

import com.brands.dao.Products;
import com.brands.dao.Users;
import com.brands.dto.ProductDto;
import com.brands.dto.ProductImp;
import com.brands.dto.UserDto;
import com.brands.dto.UserImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ManageUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        UserDto userDto =new UserImp();


        System.out.println("1setadmin");
        String userId = request.getParameter("userIds");
        System.out.println("1setadmin");
        System.out.println("id"+userId);
       int id= Integer.parseInt(userId);
        Users uSers=userDto.getUserById(id);
        System.out.println("1setadmin found");
        uSers.setUserRole("ADMIN");
        userDto.updateUser(uSers);
        System.out.println("1setadmin done");
        out.println(uSers.getUserId());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto =new UserImp();
        List<Users> allUsers = userDto.getUsersByRole("USER");
        request.setAttribute("allUsers",allUsers);
        System.out.println("all users do get");
        System.out.println("all users do get"+allUsers.size());
        RequestDispatcher rd = request.getRequestDispatcher("manageUser.jsp");
        rd.forward(request,response);
    }
}
