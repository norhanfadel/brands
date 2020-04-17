package com.brands.dto;

import com.brands.dao.OrderDetails;
import com.brands.dao.Orders;
import com.brands.dao.Products;
import com.brands.dao.Users;
import org.hibernate.Session;

public class OrderDetailsImpl implements OrderDetailsDto {

    Session session = MySessionFactory.getMySession();

    @Override
    public OrderDetails addOrderDetailForProduct(int product_id, Orders orders) {

        session.beginTransaction();
        Products products= (Products) session.load(Products.class,product_id);
        OrderDetails orderDetails=new OrderDetails(orders,products,4,4,4);


        return null;
    }
}
