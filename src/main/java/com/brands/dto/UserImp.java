package com.brands.dto;

import com.brands.dao.*;
import com.brands.utils.ValidateUser;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserImp implements UserDto {
    Session session = MySessionFactory.getMySession();

    public Double getUserCredit(int userId) {
        return ((Users) session.get(Users.class, userId)).getCreditLimit();
    }

    @Override // ok
    public boolean addCredit(String code, int user_id) {

        Users user = (Users) session.get(Users.class, user_id);
        String hql = "select value from com.brands.dao.CreditAdding c where c.code=?";
        Query query = session.createQuery(hql).setParameter(0, code);
        Double value = (Double) query.uniqueResult();
        if (value != null) {
            CreditAdding credit = (CreditAdding) session.get(CreditAdding.class, code);
            session.beginTransaction();
            session.delete(credit);
            if (user.getCreditLimit() != null) {
                Double valueTwo = user.getCreditLimit() + value;
                user.setCreditLimit(valueTwo);
            } else {
                user.setCreditLimit(value);
            }
            session.update(user);
            session.getTransaction().commit();
            session.clear();
            return true;
        } else {
            return false;
        }
    }
    public Set<Orders> getCart(int user_id) {
        String hql = "select orderses from com.brands.dao.Users c where c.userId=? ";
        Query query = session.createQuery(hql).setParameter(0, user_id);
        List<Orders> carts = (List<Orders>) query.list();
        Set<Orders> currentCart = new HashSet<>();
        for (Orders cart : carts) {
            if (cart.getBought() == 1) {
                currentCart.add(cart);
            }
        }
        if (currentCart.size() != 0) {
            return currentCart;
        } else {
            System.out.println("no Current cart but will make one");
            return makeCart(user_id);
        }
    }


    private Set<Orders> makeCart(int user_id) {
        String hql = "select orderses from com.brands.dao.Users c where c.userId=?";
        Query query = session.createQuery(hql).setParameter(0, user_id);
        Users user = (Users) session.get(Users.class, user_id);
        Set<Orders> userCart = user.getOrderses();
        Set<OrderDetails> orderDetailsList = new HashSet<>();
        Orders currentCart = new Orders(user, 0.0, user.getAddress(), new Date(), 1, orderDetailsList);
        userCart.add(currentCart);
        session.getTransaction().begin();
        session.persist(currentCart);
        session.update(user);
        session.getTransaction().commit();
        Set<Orders> actualCart = new HashSet<>();
        actualCart.add(currentCart);
        return actualCart;
    }

    @Override
    public boolean updateCreditWhenBuying(int user_id,String address) {
        Users user = (Users) session.get(Users.class, user_id);
        Set<Orders> currentOrder = getCart(user_id);
        Set<OrderDetails> items;
        Set<OrderDetails> allItems = new HashSet<>();
        for (Orders order : currentOrder) {
            items = order.getOrderDetailses();
            allItems.addAll(items);
        }
            Double totalPrice = 0.0;
            for (OrderDetails thing : allItems) {
                totalPrice += thing.getAmount();
            }
            Double userCredit = user.getCreditLimit();
            Double amount = userCredit - totalPrice;
            if (amount < 0) {
                System.out.println("credit not enough");
                return false;
            } else {
                System.out.println("credit enough" + "total is" + amount);

                for (Orders thing2 : currentOrder) {
                    if (thing2.getOrderNum() != 0) {
                        thing2.setBought(2); // order is bought
                        thing2.setCustomerAddress(address);
                        thing2.setOrderDate(new Date());
                    }
                }
                user.setCreditLimit(amount);
                session.beginTransaction();
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
        session.clear();
        if (!ValidateUser.isExist(user)) {
            session.clear();
            return false;
        } else {
            session.beginTransaction();
            if (user.getUserId() > 0) {
                session.update(user);
                session.getTransaction().commit();
            }
            session.clear();

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
        System.out.println("doneUUUSERS");
        return query.list();
    }

    @Override
    public List<Users> getUsersByRole(String role) {
        String hql = "from com.brands.dao.Users u where u.userRole=?";

        Query query = session.createQuery(hql);
        query.setParameter(0, role);
        System.out.println("doneUUUSERS");
        return query.list();
    }

    @Override
    public boolean login(Users user) {
        if (!ValidateUser.isExist(user)) {
            return false;
        } else {

            user.setStatus("ONLINE");

            session.beginTransaction();
            session.clear();
            if (user.getUserId() > 0) {
                session.update(user);
                session.getTransaction().commit();
                session.clear();
            }
            return true;
        }
    }

    @Override
    public boolean register(Users user) {
//        if (ValidateUser.isExist(user)) {
//
//            return false;
//        } else {

        session.beginTransaction();
        session.clear();
        session.persist(user);
        session.getTransaction().commit();
        makeCart(getUserIdByMail(user.getEmail()));// nehal
        session.clear();
        System.out.println("register here ");
        return true;
        //}

    }

    @Override
    public boolean logOut(Users user) {
        if (!ValidateUser.isExist(user)) {

            return false;
        } else {

            user.setStatus("OFFLINE");

            session.beginTransaction();
            session.clear();
            if (user.getUserId() > 0) {
                session.update(user);
                session.getTransaction().commit();
                session.clear();
            }
            return true;
        }
    }

    public boolean loginNour(String EMail, String passwrod) {
        String hql = "from com.brands.dao.Users c where c.email=? and c.password=?";
        session.clear();
        Query query = session.createQuery(hql);
        query.setString(0, EMail);
        query.setString(1, passwrod);

        List<Users> value = query.list();
        if (value != null && value.size() > 0) {
            Users user = value.get(0);
            user.setStatus("ONLINE");

            System.out.println(user);
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            session.clear();
            return true;
        }
        return false;

    }

    @Override
    public boolean updateStatus(int id) {
        String hql = "from com.brands.dao.Users c where c.userId=?";
        session.clear();
        Query query = session.createQuery(hql);
        query.setInteger(0, id);
        List<Users> value = query.list();
        if (value != null && value.size() > 0) {
            Users user = value.get(0);
            user.setStatus("OFFLINE");
            System.out.println(user);
            System.out.println(user);
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            session.clear();
            return true;
        }
        return false;
    }

    @Override // ok
    public boolean checkCredit(String code) {
       String hql = "select value from com.brands.dao.CreditAdding c where c.code=?";
        Query query = session.createQuery(hql).setParameter(0, code);
        Double value = (Double) query.uniqueResult();
        if (value != null) {
            CreditAdding credit = (CreditAdding) session.get(CreditAdding.class, code);
            session.beginTransaction();
            session.getTransaction().commit();
            session.clear();
            return true;
        } else {
            return false;
        }
    }
}

