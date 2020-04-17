package com.brands.utils;

import com.brands.dao.*;
import com.brands.dto.MySessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class ValidateProduct {
    public static boolean isExist(Products product) {
//        Session session = MySessionFactory.getMySession();
//        if (product != null) {
//            String hql = "from com.brands.dao.Products c where c.id=?";
//            Query query = session.createQuery(hql).setParameter(0, product.getProductId());
//            Products product2 =(Products) query.uniqueResult();
//            if (product2 != null && product2.getQuantity()>0) {
//                System.out.println(product2);
//                return true;
//            }
//        }
        return false;
    }

}
