package com.brands.dto;

import com.brands.dao.Category;
import org.hibernate.Query;
import org.hibernate.Session;


public class CategoryImpl implements CategoryDto{
    Session session = MySessionFactory.getMySession();
    @Override
    public Category getCategoryByName(String name) {
        String hql="from Category c where c.name=?";
        Query query = session.createQuery(hql).setParameter(0, name);
       Category category= (Category) query.uniqueResult();

      return category;
    }

    @Override
    public Category getCategoryById(int id) {
        String hql="from Category c where c.categoryId=?";
        Query query = session.createQuery(hql).setParameter(0, id);
        Category category= (Category) query.uniqueResult();

        return category;
    }
}
