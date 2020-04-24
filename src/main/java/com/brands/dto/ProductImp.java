package com.brands.dto;

import com.brands.dao.Category;
import com.brands.dao.Orders;
import com.brands.dao.Products;
import com.brands.dao.Users;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class ProductImp implements ProductDto {
    Session session = MySessionFactory.getMySession();


    @Override
    public Products getProductById(int product_id) {
        String hql = " from com.brands.dao.Products p where p.productId=?";
        Query query = session.createQuery(hql).setParameter(0, product_id);
        return (Products) query.list().get(0);
    }

    @Override
    public List<Products> searchProductByName(String name) {
        boolean numeric = true;
        Double price = 0.0;
        String hql = " from com.brands.dao.Products p where p.name like ? OR p.description like ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, "%"+ name+"%");
        query.setParameter(1, "%"+ name+"%");
        List<Products> products = query.list();
        return products;
    }

    @Override
    public List<Products> searchProductByPrice(Double price) {
        String hql = " from com.brands.dao.Products p where p.price=?";
        Query query = session.createQuery(hql).setParameter(0, price);
        List<Products> products = query.list();
        return products;
    }

    @Override
    public List<Products> getAllProducts() {
        String hql = "from  com.brands.dao.Products";
        Query query = session.createQuery(hql);
        List<Products> products = query.list();
        return products;
    }

    @Override
    public List<Products> getAllProductsByCategoryId(int category_id) {
        String hql = " from com.brands.dao.Products p where p.category.id=?";
        Query query = session.createQuery(hql).setParameter(0, category_id);
        List<Products> products = query.list();
        return products;
    }

    @Override
    public Products addProduct(Products product) {

        session.clear();
        System.out.println("inside");
        Category category1 = (Category) session.load(Category.class, product.getCategory().getCategoryId());
        Products products = new Products(category1,product.getCreateDate(), product.getName(), product.getPrice());
        products.setCategory(category1);
        products.setImageName(product.getImageName());
        products.setImage(product.getImage());
        products.setQuantity(product.getQuantity());
        products.setDescription(product.getDescription());
        session.beginTransaction();
        session.persist(products);
        category1.getProductses().add(products);
        session.update(category1);
        session.update(products);
        session.getTransaction().commit();
        session.clear();
       // session.close();
        session.flush();
        return product;
    }


    @Override
    public void updateProduct(Products product) {
        session.clear();
        System.out.println("in update");
        Transaction transaction = session.beginTransaction();
        Products oldProduct = (Products) session.load(Products.class, product.getProductId());
        oldProduct.setName(product.getName());
        oldProduct.setCreateDate(product.getCreateDate());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setCategory(product.getCategory());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setImage(product.getImage());
//        oldProduct.setImageName(product.getImageName());
        oldProduct.setQuantity(product.getQuantity());
        session.update(oldProduct);
        System.out.println("before commit");

        transaction.commit();

    }

    @Override
    public boolean deleteProduct(int product_id) {
        Transaction transaction = session.beginTransaction();
        session.clear();
        int numOfRiws = -1;
        String hql = "delete from  com.brands.dao.Products p where p.productId =? " ;
        Query query = session.createQuery(hql).setInteger(0, product_id);
        numOfRiws = query.executeUpdate();
        transaction.commit();
        if(numOfRiws == -1){
            session.clear();
            return false;
        }else {
            session.clear();
            return true;
        }
    }

}
