package com.brands.dto;

import com.brands.dao.OrderDetails;
import com.brands.dao.Orders;
import com.brands.dao.Products;
import com.brands.dao.Users;

public interface OrderDetailsDto {

     OrderDetails getOrderDetailById(int orderDetail_id);

     OrderDetails insertOrderDetailForProduct(int product_id,int order_id,
            int quantity, double price, double amount);

     boolean updateOrderDetail(OrderDetails orderDetails);

     boolean deleteOrderDetail(int  orderDetail_id);
}
