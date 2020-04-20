package com.brands.servlets;

import com.brands.dao.Users;
import com.brands.dto.UserImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Profile extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.println("-----------------here-----------");

        UserImp userImp = new UserImp();

        HttpSession session = request.getSession();

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId" + userId);
        if (userId != 0) {

            Users user2 = userImp.getUserById(userId);
            System.out.println("***********" + user2);
            System.out.println("***********" + user2.getUserName());
            String email = user2.getEmail();
            String name = user2.getUserName();
            String address = user2.getAddress();
            String phone = user2.getPhone();
            Double code = user2.getCreditLimit();
            String password = user2.getPassword();
            request.setAttribute("emailprofile", email);
            request.setAttribute("nameprofile", name);
            request.setAttribute("addressprofile", address);
            request.setAttribute("phoneprofile", phone);
            request.setAttribute("codeprofile", code);
            request.setAttribute("passwordprofile", password);
            request.setAttribute("id1",userId);

            RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
            dispatcher.forward(request, response);

        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserImp userImp = new UserImp();
        HttpSession session = request.getSession();

        int userId = (int) session.getAttribute("userId");
        System.out.println("userId" + userId);
        if (userId != 0) {

            Users user2 = userImp.getUserById(userId);
            String nameEdit = request.getParameter("nameedit");

            String phoneEdit = request.getParameter("phoneedit");

            String passwordEdit = request.getParameter("passwordedit");
            String addressEdit = request.getParameter("Addressedit");
            String codeEdit = request.getParameter("codeedit");
if(codeEdit.equals("")){
    codeEdit="notValid";
}
           userImp.addCredit(codeEdit,userId);
            user2.setPhone(phoneEdit);
            user2.setUserName(nameEdit);
            user2.setAddress(addressEdit);

            user2.setPassword(passwordEdit);


            if (userImp.updateUser(user2)) {
                System.out.println("valid");
                String paramName2 = "true1";
                request.setAttribute("true2", paramName2);
                RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
                dispatcher.forward(request, response);

            }
        }


    }

}









