package com.brands.dto;

import com.brands.dao.Products;
import com.brands.dao.Users;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class ProductImp implements ProductDto {
    Session session = MySessionFactory.getMySession();

    @Override
    public List<Products> searchProductByName(String name) {
        return null;
    }

    @Override
    public List<Products> searchProductByPrice(Double price) {
        return null;
    }

    @Override
    public List<Products> getAllProducts() {
        return null;
    }

    @Override
    public List<Products> getAllProductsByCategoryId(int category_id) {
        return null;
    }

    @Override
    public boolean addProduct(Products product) {
        return false;
    }

    @Override
    public Users getUserById(int user_id) {
        return null;
    }

    @Override
    public Products getProductById(int product_id) {
        String hql = " from com.brands.dao.Products p where p.productId=?";
        Query query = session.createQuery(hql).setParameter(0, product_id);
        return (Products) query.uniqueResult();
    }
}
