package com.brands.servlets;

import com.brands.dao.Users;
import com.brands.dto.MySessionFactory;
import com.brands.dto.UserImp;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Registration extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("post");
        String name = request.getParameter("name");
        String email = request.getParameter("emailRegistration");
        String phone = request.getParameter("phone");

        String password = request.getParameter("passwordRegistration");
        String address = request.getParameter("address");

        System.out.println("address-----" + address + name + email + phone);
        String code = request.getParameter("welcomeCode");
        System.out.println("code-----" + code);
        Double welcomeCode = Double.parseDouble(code);
        System.out.println("password is=" + password);
        UserImp userImp= new UserImp();
        Users user = new Users();
        user.setStatus("OFFLINE");
        user.setUserRole("USER");
        user.setPhone(phone);
        user.setUserName(name);
        user.setAddress(address);
        user.setEmail(email);
        user.setPassword(password);

        user.setPhone(phone);

        List<Users> usersList = userImp.getAllUsers();
        if(name != null && email != null && password !=null && phone !=null && address != null && code !=null) {
            for (Users userss : usersList) {

                if (userss.getEmail().equals(email)) {
                    String paramName1 = "This Account is aleadry Exist!";
                    System.out.println("from else");
                    request.setAttribute("register", paramName1);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                    dispatcher.forward(request, response);

                }
            }

            if (userImp.register(user)) {
              Session session= MySessionFactory.getMySession();
              Users user11=(Users) session.get(Users.class,userImp.getUserIdByMail(email));

                String falseCode = "";
                String trueCode="";
              if(userImp.addCredit(code,user11.getUserId())) {
                //  trueCode = " voucher Amount added ,see your balance in profile ";

            }else {
                 //  falseCode = " voucher code not valid , try it again from your profile ! ";

              }
                System.out.println("valid");
                String paramName2 = "true1";
                request.setAttribute("trueCode", trueCode);
                request.setAttribute("falseCode", falseCode);
                request.setAttribute("true1", paramName2);

                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);

            }
        }
//        } else {
//            String paramName1 = "This Account is aleadry Exist!";
//            System.out.println("from else");
//            request.setAttribute("register", paramName1);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//            dispatcher.forward(request, response);
//
//
    }
}



