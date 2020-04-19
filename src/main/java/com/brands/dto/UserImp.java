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

    public Double getUserCredit(int userId){
        return ((Users)session.get(Users.class,userId)).getCreditLimit();
    }
    @Override // ok
    public boolean addCredit(String code, int user_id) {
        session.clear();
        Users user = (Users) session.get(Users.class,user_id);
        String hql = "select value from com.brands.dao.CreditAdding c where c.code=?";
        Query query = session.createQuery(hql).setParameter(0, code);
        Double value = (Double) query.uniqueResult();
        if (value != null) {
            CreditAdding credit = (CreditAdding) session.get(CreditAdding.class,code);
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
            return true;
        } else {
            return false;
        }
    }

    public Orders getCart(int user_id) {
        String hql = "select orderses from com.brands.dao.Users c where c.userId=?";
        Query query = session.createQuery(hql).setParameter(0, user_id);
        Orders cart = (Orders) query.uniqueResult();
        Orders currentCart2 =null;
        if (cart != null) {
            return cart;
        }
        else {
            System.out.println("no Current cart but will make one");
            return makeCart(user_id);
        }
    }

    private Orders makeCart(int user_id){
        String hql = "select orderses from com.brands.dao.Users c where c.userId=?";
        Query query = session.createQuery(hql).setParameter(0, user_id);
        Users user = (Users) session.get(Users.class,user_id);
        Products products = new Products((Category)session.get(Category.class,1), new Date(), "test", 0.0);
        Set<OrderDetails> orderDetailsList = new HashSet<>();
        Orders currentCart =new Orders(user, 0.0, user.getAddress(), new Date(),0,1,orderDetailsList);
        OrderDetails orderDetails = new OrderDetails(currentCart,products, 0, 0, 0);

        products.setOrderDetailses(orderDetailsList);
        currentCart.setOrderDetailses(orderDetailsList);
        orderDetailsList.add(orderDetails);

        session.beginTransaction();
        session.persist(products);
        session.getTransaction().commit();

        session.beginTransaction();
        session.persist(currentCart); // need to add OrderDetails id to order num here ?
        session.getTransaction().commit();

        currentCart = (Orders) query.uniqueResult();
        orderDetails.setOrders(currentCart);

        session.beginTransaction();
        session.persist(orderDetails);
        session.getTransaction().commit();



        String hql2 = "select id from com.brands.dao.OrderDetails c where c.amount=0 and c.price = 0 and quanity = 0";
        Query query2 = session.createQuery(hql2);
        int orderDetailsID = (int) query2.uniqueResult();

        currentCart.setOrderNum(orderDetailsID);

        session.beginTransaction();
        session.update(currentCart); // need to add OrderDetails id to order num here
        session.getTransaction().commit();

        return (Orders) query.uniqueResult();
    }

    @Override
    public boolean updateCreditWhenBuying(int user_id) {
        Users user = (Users) session.get(Users.class,user_id);
        Orders currentOrder = getCart(user_id);
        Double totalPrice = currentOrder.getAmount();
        Double userCredit = user.getCreditLimit();
        Double amount = userCredit - totalPrice;
        if (amount < 0) {
            System.out.println("credit not enough");
            return false;
        } else {
            System.out.println("credit enough" + "total is" + amount);
            currentOrder.setBought(2); // order is bought
            user.setCreditLimit(amount);
            session.beginTransaction();
            session.update(user);
            session.update(currentOrder);
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
        System.out.println("doneUUUSERS");
        return query.list();
    }

    @Override
    public List<Users> getUsersByRole(String role) {
        String hql = "from com.brands.dao.Users u where u.userRole=?";

        Query query = session.createQuery(hql);
        query.setParameter(0,role);
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
            if (user.getUserId() > 0) {
                session.update(user);
                session.getTransaction().commit();
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
            session.persist(user);
            session.getTransaction().commit();
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
            if (user.getUserId() > 0) {
                session.update(user);
                session.getTransaction().commit();
            }
            return true;
        }
    }
    public boolean loginNour(String EMail, String passwrod) {
        String hql = "from com.brands.dao.Users c where c.email=? and c.password=?";

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
    @Override
    public boolean updateStatus(int id) {
        String hql = "from com.brands.dao.Users c where c.userId=?";

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
            return true;
        }
        return false;
    }



}

