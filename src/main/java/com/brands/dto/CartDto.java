/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brands.dto;

import com.brands.dao.Products;
import com.brands.dao.Users;

import java.util.List;

/**
 *
 * @author Shimaa Mohammed
 */
public interface CartDto {
    /*nehal*/
    public Products getProductByProductId(int product_id);
    public boolean addProductByProductIdToCart(int product_id, Users user);
    public boolean removeProductByProductIdFromCart(int product_id, Users user);
    public boolean updateQuantityByProductId(int product_id, Users user,int quantity);
    public List<Products> getAllProductByUserId(Users user); // should update buying date
    public void updateNoOfProductsInCart(int product_id, Users user); // will be rethought when we change DB
    public Double calculateSumOfProducts(Users user);
    /*nehal end*/

     /*badri*/
    
     /*badri end*/
    
    
     /*noura*/
    
     /*noura end*/
    
    
     /*hesham*/
    
     /*end hesham*/
    
    
     /*shimaa*/
    
     /*shimaa end*/
}
