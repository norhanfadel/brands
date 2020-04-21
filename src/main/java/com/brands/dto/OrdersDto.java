/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brands.dto;

import com.brands.dao.Orders;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Shimaa Mohammed
 */
public interface OrdersDto {
    /*nehal*/
//    public Products getProductByProductId(int product_id);
//    public boolean addProductByProductIdToOrders(OrderDetails orderDetails, Users user);
//    public boolean removeProductByProductIdFromOrders(int product_id, Users user);
//    public boolean updateQuantityByProductId(int product_id, Users user,int quantity);
//    public List<Products> getAllProductByUserId(Users user); // should update buying date
//    public void updateNoOfProductsInOrders(int product_id, Users user); // will be rethought when we change DB
//    public Double calculateSumOfProducts(Users user);
    public boolean  updateQuantityByProductId(int product_id, int user_id, int quantity);
    public Set<Orders> getCart(int user_id);
    public boolean removeProductByProductIdFromCart(int product_id, int user_id);
    /*nehal end*/

     /*badri*/
    Orders getOrderByID(int order_id);
    List<Orders> getAllOrdersForUser();
    Orders addNewOrder(int user_id, double amount, String customerAddress,
                       Date orderDate, int orderNum);
    boolean updateOrder(Orders orders);
    boolean deleteOrder(int order_id);
     /*badri end*/


     /*noura*/

     /*noura end*/


     /*hesham*/

     /*end hesham*/


     /*shimaa*/

     /*shimaa end*/
}
