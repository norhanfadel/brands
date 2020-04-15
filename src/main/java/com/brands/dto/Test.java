package com.brands.dto;

import com.brands.dao.Products;
import com.brands.dao.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Test {

    static Session session = MySessionFactory.getMySession();
    public static void main(String[] args) {

        Users user = new Users();
//        session.beginTransaction();
//       session.persist(cart);
//        session.persist(user);
//        session.getTransaction().commit();

        UserImp userImp = new UserImp();
        Users userX = userImp.getUserById(userImp.getUserIdByMail(user.getEmail()));
        userX.setAddress("cairo");
        boolean isItTrue = userImp.updateUser(userX);
        System.out.println(isItTrue);

        // user=(Users) session.get(Users.class,7);
//        if(!userImp.login(user)){
//            System.out.println("false");
//        }
//        CreditAdding creditAdding = new CreditAdding("500x", 10000);
//        session.beginTransaction();
//        session.persist(creditAdding);
//        session.getTransaction().commit();
//        Users user1 = (Users) session.get(Users.class, 3);
//        userImp.addCredit(creditAdding.getCode(), user1);
//        // userImp.register(user,cart);
        System.out.println("done");
//
//        Products products = new Products(2, user1.getCart(), user1, 2, 500);
//        session.beginTransaction();
//        session.persist(products);
//        session.getTransaction().commit();
//        userImp.updateCreditWhenBuying(user1);
//        System.out.println("done");


    }
}
