package com.brands.servlets;

import com.brands.dao.Users;
import com.brands.dto.UserImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


public class Contact extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        UserImp userImp = new UserImp();

        HttpSession session = request.getSession();

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId" + userId);
        if (userId != 0) {

            Users user2 = userImp.getUserById(userId);
            System.out.println("******From Contact*****");

            String email = user2.getEmail();
            String name = user2.getUserName();

            request.setAttribute("emailprofile", email);
            request.setAttribute("nameprofile", name);
            request.setAttribute("id1",userId);
            RequestDispatcher dispatcher = request.getRequestDispatcher("contact-us.jsp");
            dispatcher.forward(request, response);

        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        System.out.println("From DO post");
        UserImp userImp = new UserImp();
        HttpSession session = request.getSession();

        int userId = (int) session.getAttribute("userId");

        System.out.println(userId+"-----------");
        System.out.println("userId" + userId);
        if (userId != 0) {
            System.out.println("From DO post");
            Users user2 = userImp.getUserById(userId);

            String subject = request.getParameter("subjectMsg");
            String email = user2.getEmail();
            String msg = request.getParameter("Msg");
            System.out.println(msg + subject + "******************");


            FileWriter myWriter = null;
            try {

                myWriter = new FileWriter("C:\\Users\\Dan\\Desktop\\filename.txt", true);
                myWriter.write(System.getProperty("line.separator"));
                myWriter.write("Email:-" + email);
                myWriter.write(System.getProperty("line.separator"));
                myWriter.write("subject Is" + subject);
                myWriter.write(System.getProperty("line.separator"));

                myWriter.write("Msg is" + msg);
                myWriter.close();
                System.out.println("I wrote");
            } catch (Exception ex) {
                ex.printStackTrace();
            }


            System.out.println("valid");
            String paramName2 = "true1";
            request.setAttribute("true3", paramName2);

            RequestDispatcher dispatcher = request.getRequestDispatcher("contact-us.jsp");
            dispatcher.forward(request, response);

        }
    }


}




