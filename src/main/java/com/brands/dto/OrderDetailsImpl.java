package com.brands.dto;

import com.brands.dao.OrderDetails;
import com.brands.dao.Orders;
import com.brands.dao.Products;
import org.hibernate.Query;
import org.hibernate.Session;

public class OrderDetailsImpl implements OrderDetailsDto {

    Session session = MySessionFactory.getMySession();

    @Override
    public OrderDetails getOrderDetailById(int orderDetail_id) {
        String hql = "from  com.brands.dao.OrderDetails od where od.id = ?";
        Query query = session.createQuery(hql).setParameter(0,orderDetail_id);
        OrderDetails orderDetails = (OrderDetails) query.list().get(0);
        return orderDetails;
    }

    @Override
    public OrderDetails insertOrderDetailForProduct(int product_id, int order_id,
                                                    int quantity, double price, double amount) {
        Products product= (Products) session.load(Products.class,product_id);
        Orders order= (Orders) session.load(Orders.class,order_id);
        OrderDetails orderDetails=new OrderDetails(1,order,product,amount,price,quantity);
        //persist im database
        session.beginTransaction();
        session.persist(orderDetails);
        session.getTransaction().commit();
        return orderDetails;
    }

    @Override
    public boolean updateOrderDetail(OrderDetails orderDetails) {
        int numOfRiws = -1;
        String hql = "update  com.brands.dao.OrderDetails od set od.amount = ?, od.price =? " +
                ",od.quanity = ?, od.orders.id=?, od.products.id = ?  where od.id = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,orderDetails.getAmount());
        query.setParameter(1,orderDetails.getPrice());
        query.setParameter(2,orderDetails.getQuanity());
        query.setParameter(3,orderDetails.getOrders().getId());
        query.setParameter(4,orderDetails.getProducts().getProductId());
        query.setParameter(5,orderDetails.getId());

        numOfRiws = query.executeUpdate();

        if(numOfRiws == -1){
            return false;
        }else{
//            session.beginTransaction();
//            session.update(orderDetails);
//            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public boolean deleteOrderDetail(int  orderDetail_id) {
        int numOfRiws = -1;
        String hql = "delete from  com.brands.dao.OrderDetails od where od.id =? " ;
        Query query = session.createQuery(hql).setParameter(0, orderDetail_id);
        numOfRiws = query.executeUpdate();
        if(numOfRiws == -1){
            return false;
        }else
            return true;
    }
}
