package com.brands.dto;

import com.brands.dao.OrderDetails;
import com.brands.dao.Orders;
import com.brands.dao.Products;
import com.brands.dao.Users;

public interface OrderDetailsDto {
    public OrderDetails addOrderDetailForProduct(int product_id,Orders orders);


}
