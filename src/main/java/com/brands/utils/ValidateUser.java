package com.brands.utils;

import com.brands.dao.Users;
import com.brands.dto.MySessionFactory;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class ValidateUser {
    public static boolean isExist(Users user) {
        Session session = MySessionFactory.getMySession();
        session.clear();
        System.out.println(user+"u----------ser");
        if (user != null) {
            String hql = "from com.brands.dao.Users c where c.email=?";
            System.out.println(hql+"u*****************ser");
            Query query = session.createQuery(hql).setParameter(0, user.getEmail());
            System.out.println(query+"u*****************ser");
            List<Users> listUser = query.list();
            if (listUser != null) {

                System.out.println(listUser);

                session.clear();

                return true;

            }
        }
        session.clear();
        return false;
    }

}
