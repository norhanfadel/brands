package com.brands.dto;

import com.brands.dao.*;
import com.brands.utils.ValidateUser;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class OrdersImp implements OrdersDto {
    Session session = MySessionFactory.getMySession();

    @Override
    public Orders getOrderByID(int order_id) {
        String hql = "from  com.brands.dao.Orders o where o.id = ?";
        Query query = session.createQuery(hql).setParameter(0, order_id);
        Orders order = (Orders) query.list().get(0);
        return order;
    }

    @Override
    public List<Orders> getAllOrdersForUser() {
        String hql = "from  com.brands.dao.Orders";
        Query query = session.createQuery(hql);
        List<Orders> orders = query.list();
        return orders;
    }

    @Override
    public Orders addNewOrder(int user_id, double amount, String customerAddress,
                              Date orderDate, int orderNum) {
        Users user = (Users) session.load(Users.class, user_id);
        Orders order = new Orders(1, user, amount, customerAddress, orderDate, orderNum);
        session.beginTransaction();
        session.persist(order);
        session.getTransaction().commit();
        return order;
    }

    @Override
    public boolean updateOrder(Orders orders) {
        int numOfRiws = -1;
        String hql = "update  com.brands.dao.Orders o set o.amount=?, o.customerAddress=?, " +
                "o.orderDate=?, o.bought=? where o.id=? and o.users.id=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, orders.getAmount());
        query.setParameter(1, orders.getCustomerAddress());
        query.setParameter(2, orders.getOrderDate());
        query.setParameter(3, orders.getBought());
        query.setParameter(4, orders.getId());
        query.setParameter(5, orders.getUsers().getUserId());

        numOfRiws = query.executeUpdate();

        if (numOfRiws == -1) {
            return false;
        } else {
//            session.beginTransaction();
//            session.update(orders);
//            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public boolean deleteOrder(int order_id) {
        int numOfRiws = -1;
        String hql = "delete from  com.brands.dao.Orders o where o.id =? ";
        Query query = session.createQuery(hql).setParameter(0, order_id);
        numOfRiws = query.executeUpdate();
        if (numOfRiws == -1) {
            return false;
        } else
            return true;
    }

    @Override
    public Orders getCart(int user_id) {
        String hql = "select orderses from com.brands.dao.Users c where c.userId=?";
        Query query = session.createQuery(hql).setParameter(0, user_id);
        Orders cart = (Orders) query.uniqueResult();
        Orders currentCart2 = null;
        if (cart != null) {
            return cart;
        } else {
            System.out.println("no Current cart but will make one");
            return makeCart(user_id);
        }
    }

    private Orders makeCart(int user_id) {
        String hql = "select orderses from com.brands.dao.Users c where c.userId=?";
        Query query = session.createQuery(hql).setParameter(0, user_id);
        Users user = (Users) session.get(Users.class, user_id);
        Products products = new Products((Category) session.get(Category.class, 1), new Date(), "test", 0.0);
        Set<OrderDetails> orderDetailsList = new HashSet<>();
        Orders currentCart = new Orders(user, 0.0, user.getAddress(), new Date(), 0, 1, orderDetailsList);
        OrderDetails orderDetails = new OrderDetails(currentCart, products, 0, 0, 0);

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

    private boolean addProductByProductIdToCart(int product_id, int user_id, int quantity) {
        Users user = (Users) session.get(Users.class, user_id);
        if (!ValidateUser.isExist(user)) {
            return false;
        } else if (getProductQuantity(product_id) >= quantity) {
            Orders cart = getCart(user_id);
            Products products = (Products) session.get(Products.class, product_id);
            Double price = products.getPrice();
            OrderDetails orderDetails = new OrderDetails(cart, products, price * quantity, price, quantity);
            Set<OrderDetails> cartProducts = cart.getOrderDetailses();
            cartProducts.add(orderDetails);

            session.beginTransaction();
            session.persist(orderDetails);
            session.getTransaction().commit();

            String hql = "from OrderDetails o where o.orders =? and products = ?";
            Query query = session.createQuery(hql);
            query.setEntity(0, cart);
            query.setEntity(1, products);
            orderDetails = (OrderDetails) query.uniqueResult();
            cart.setOrderNum(orderDetails.getId());
            cart.setAmount(orderDetails.getAmount());
            products.setQuantity(products.getQuantity() - quantity);
            session.beginTransaction();
            session.update(cart);
            session.update(products);
            session.getTransaction().commit();
            return true;
        } else {
            return false;
        }
    }

    @Override // ok
    public boolean removeProductByProductIdFromCart(int product_id, int user_id) {
        Users user = (Users) session.get(Users.class, user_id);
        if (!ValidateUser.isExist(user)) {
            return false;
        } else {
            Orders cart = getCart(user_id);
            Set<OrderDetails> cartProducts = cart.getOrderDetailses();
            Products product = (Products) session.get(Products.class, product_id);
            OrderDetails toBeRemovedItem = null;
            for (OrderDetails item : cartProducts) {
                if (item.getProducts().getProductId() == (product.getProductId())) {
                    toBeRemovedItem = item;
                }
            }
            if (toBeRemovedItem != null) {
                double removedPrice = toBeRemovedItem.getAmount();
                product.setQuantity(product.getQuantity() + toBeRemovedItem.getQuanity());
                session.beginTransaction();
                cartProducts.remove(product);
                session.delete(toBeRemovedItem);
                cart.setOrderDetailses(cartProducts);
                cart.setAmount(cart.getAmount() - removedPrice);
                session.update(cart);
                session.update(product);
                session.getTransaction().commit();

                System.out.println("product removed");
                return true;
            } else {
                System.out.println("product not in cart already");
                return false;
            }

        }
    }

    private boolean isProductRemovedFromCart(Products product, Users user) {
        boolean isRemoved = true;
        Orders cart = getCart(user.getUserId());
        Set<OrderDetails> items = cart.getOrderDetailses();
        for (OrderDetails item : items) {
            if (item.getProducts().equals(product)) {
                isRemoved = false;
            } else {
                continue;
            }
        }
        return isRemoved;
    }

    private int getProductQuantity(int product_id) {
        Integer productQuantity = ((Products) session.get(Products.class, product_id)).getQuantity();
        if (productQuantity != null) {
            return productQuantity;
        } else return 0;
    }

    @Override
    public boolean updateQuantityByProductId(int product_id, int user_id, int quantity) {
        Products product = (Products) session.get(Products.class, product_id);
        Orders cart = getCart(user_id);
        Users user = (Users) session.get(Users.class, user_id);
        if (!ValidateUser.isExist(user)) {
            return false;
        } else {
            if (!isProductRemovedFromCart(product, user)) {
                Set<OrderDetails> items = cart.getOrderDetailses();
                Double total = 0.0;
                int prevQuantity = 0;
                for (OrderDetails item : items) {
                    if (item.getProducts().equals(product) && (getProductQuantity(product_id) >= quantity)) {
                        prevQuantity = item.getQuanity();
                        item.setQuanity(quantity);
                        item.setPrice(item.getProducts().getPrice());
                        item.setAmount(quantity * item.getPrice());
                    }
                    total += item.getAmount();
                }
                cart.setOrderDetailses(items);
                cart.setAmount(total);
                session.beginTransaction();
                session.update(cart);
                session.getTransaction().commit();

                int nowQuantity = product.getQuantity() - (quantity - prevQuantity);
                if (nowQuantity >= 0) {
                    product.setQuantity(nowQuantity);
                    session.beginTransaction();
                    session.update(product);
                    session.getTransaction().commit();
                } else {
                    return false;
                }
            } else {
                addProductByProductIdToCart(product_id, user_id, quantity);
            }
        }
        return true;
    }

}
