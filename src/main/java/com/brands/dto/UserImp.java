package com.brands.dto;

import com.brands.dao.Orders;
import com.brands.dao.Products;
import com.brands.dao.Users;
import com.brands.utils.ValidateUser;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;
import java.util.Set;

public class UserImp implements UserDto {
    Session session = MySessionFactory.getMySession();

    @Override // ok
    public boolean addCredit(String code, Users user) {

        String hql = "select value from com.brands.dao.CreditAdding c where c.code=?";
        Query query = session.createQuery(hql).setParameter(0, code);

        Double value = (Double) query.uniqueResult();
        if (value != null) {
            if (user.getCreditLimit() != null) {
                Double valueTwo = user.getCreditLimit() + value;
                user.setCreditLimit(valueTwo);
                session.beginTransaction();
                session.update(user);
                session.getTransaction().commit();
                return true;
            } else {
                user.setCreditLimit(value);
                session.beginTransaction();
                session.update(user);
                session.getTransaction().commit();

                return true;
            }

        } else {
            return false;
        }
    }

    public Orders getCart(Users user) {
        String hql = "select orderses from com.brands.dao.Users c where c.userId=?";
        Query query = session.createQuery(hql).setParameter(0, user.getUserId());
        Set<Orders> userOrders = (Set<Orders>) query.uniqueResult();
        if (userOrders != null) {
            Orders currentOrder = null;
            for (Orders order : userOrders) {
                if (order.getBought() == 1) {
                    return order;
                }
            }

        }
        return null;
    }

    @Override
    public boolean updateCreditWhenBuying(Users user) {
        Orders currentOrder = getCart(user);
        Double totalPrice = currentOrder.getAmount();
        Double userCredit = user.getCreditLimit();
        Double amount = userCredit - totalPrice;
        if (amount < 0) {
            System.out.println("credit not enough");
            return false;
        } else {
            System.out.println("credit not enough" + "total is" + amount);
            session.beginTransaction();
            user.setCreditLimit(amount);
            session.update(user);
            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public int getUserIdByMail(String mail) {
        String hql = "select id from com.brands.dao.Users c where c.email=?";
        Query query = session.createQuery(hql).setParameter(0, mail);
        return (int) query.uniqueResult();
    }

    @Override
    public boolean updateUser(Users user) { // mail should be disabled in GUI
        if (!ValidateUser.isExist(user)) {
            return false;
        } else {
            session.beginTransaction();
            if (user.getUserId() > 0) {
                session.update(user);
                session.getTransaction().commit();
            }
            return true;
        }
    }

    @Override
    public Users getUserById(int id) {
        Users user = (Users) session.get(Users.class, id);
        if (ValidateUser.isExist(user)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public List<Users> getAllUsers() {
        String hql = "from com.brands.dao.Users ";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public boolean login(Users user) {
        if (!ValidateUser.isExist(user)) {
            return false;
        } else {

            user.setStatus("ONLINE");

            session.beginTransaction();
            if (user.getUserId() > 0) {
                session.update(user);
                session.getTransaction().commit();
            }
            return true;
        }
    }

    @Override
    public boolean register(Users user) {
        if (!ValidateUser.isExist(user)) {

            return false;
        } else {

            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
            System.out.println("register here ");
            return true;
        }

    }

    @Override
    public boolean logOut(Users user) {
        if (!ValidateUser.isExist(user)) {

            return false;
        } else {

            user.setStatus("OFFLINE");

            session.beginTransaction();
            if (user.getUserId() > 0) {
                session.update(user);
                session.getTransaction().commit();
            }
            return true;
        }
    }
    public boolean loginNour(String EMail, String passwrod) {
        String hql = "from com.brands.dao.Users c where c.EMail=? and c.password=?";

        Query query = session.createQuery(hql);
        query.setString(0, EMail);
        query.setString(1, passwrod);

        List<Users> value =  query.list();
        if (value != null &&  value.size()>0) {
            Users user = value.get(0);
            user.setStatus("ONLINE");

            System.out.println(user);
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            return true;
        }
        return false;

    }

}
