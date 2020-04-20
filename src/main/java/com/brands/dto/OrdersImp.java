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
        System.out.println(user_id);
        Query query1 = session.createQuery("from com.brands.dao.Users u where u.userId = ?").setParameter(0, user_id);
        Users user = (Users) query1.uniqueResult();
        Set<Orders> userCart = user.getOrderses();
        Set<OrderDetails> orderDetailsList = new HashSet<>();
        System.out.println(user.getAddress());
        Orders currentCart = new Orders(user, 0.0, user.getAddress(), new Date(), 1, orderDetailsList);
        userCart.add(currentCart);
        session.getTransaction().begin();
        session.update(user);
        session.persist(currentCart);
        session.getTransaction().commit();
        Set<Orders> actualCart = new HashSet<>();
        actualCart.add(currentCart);
        return actualCart;
    }

    private void updateOnFirstCart(int product_id, int user_id, int quantity, Set<Orders> cart, Orders actualCart) {
        Products products = (Products) session.get(Products.class, product_id);
        Double price = products.getPrice();
        OrderDetails orderDetails2 = new OrderDetails(actualCart, products, price * quantity, price, quantity);
        Set<OrderDetails> cartProducts = actualCart.getOrderDetailses();
        cartProducts.add(orderDetails2);
        session.beginTransaction();
        session.persist(orderDetails2);
        session.getTransaction().commit();
        // should change order num here in his first order
        String hql = "from OrderDetails o where o.orders =? and products = ?";
        Query query = session.createQuery(hql);
        query.setEntity(0, actualCart);
        query.setEntity(1, products);
        OrderDetails orderDetails = (OrderDetails) query.uniqueResult();
        actualCart.setOrderNum(orderDetails.getId());
        actualCart.setAmount(orderDetails.getAmount());
        products.setQuantity(products.getQuantity() - quantity);
        session.beginTransaction();
        session.update(actualCart);
        session.update(products);
        session.getTransaction().commit();
    }

    private boolean addProductByProductIdToCart(int product_id, int user_id, int quantity) {
        Users user = (Users) session.get(Users.class, user_id);
        if (!ValidateUser.isExist(user)) {
            return false;
        } else if (getProductQuantity(product_id) >= quantity) {
            Set<Orders> cart = getCart(user_id);
            Orders actualCart = cart.iterator().next();

            if (cart.size() == 1 && actualCart.getOrderNum() == 0) {
                updateOnFirstCart(product_id, user_id, quantity, cart, actualCart);
            } else {
                Set<Orders> cart2 = getCart(user_id);
                Orders actualCart2 =null ;
                for (Orders order:cart2) {
                    if (order.getOrderNum() == 0){
                        actualCart2 = order ;
                    }
                }
                updateOnFirstCart(product_id, user_id, quantity, cart2, actualCart2);
            }

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
            Set<Orders> cart = getCart(user_id);
            Set<OrderDetails> items;
            Set<OrderDetails> cartProducts = new HashSet<>();
            for (Orders order : cart) {
                items = order.getOrderDetailses();
                cartProducts.addAll(items);
            }
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
                int itemId = toBeRemovedItem.getId();
                session.delete(toBeRemovedItem);
                Orders tobeRemovedOrder = null;
                String hql = "from com.brands.dao.Orders o where o.orderNum =?";
                Query query = session.createQuery(hql).setParameter(0, itemId);
                tobeRemovedOrder = (Orders) query.uniqueResult();
                cart.remove(tobeRemovedOrder);
                session.delete(tobeRemovedOrder);
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
        Set<Orders> cart = getCart(user.getUserId());
        Set<OrderDetails> items;
        Set<OrderDetails> cartProducts = new HashSet<>();
        for (Orders order : cart) {
            items = order.getOrderDetailses();
            cartProducts.addAll(items);
        }
        for (OrderDetails item : cartProducts) {
            if (item.getProducts().getProductId() == product.getProductId()) {
                isRemoved = false;
                break;
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
    public boolean updateQuantityByProductId(int product_id, int user_id, int quantity) { //
        Products product = (Products) session.get(Products.class, product_id);
        Set<Orders> cart = getCart(user_id);
        Set<OrderDetails> itemsI;
        Set<OrderDetails> cartProducts = new HashSet<>();
        boolean done =false ;
        for (Orders order : cart) {
            itemsI = order.getOrderDetailses();
            cartProducts.addAll(itemsI);
        }
        Users user = (Users) session.get(Users.class, user_id);
        if (!ValidateUser.isExist(user)) {
            return false;
        } else {
            if (!isProductRemovedFromCart(product, user)) {

                Double total = 0.0;
                int prevQuantity = 0;
                OrderDetails neededItem = null;
                Set<OrderDetails> newOrder = new HashSet<>();
                for (OrderDetails item : cartProducts) {
                    if (item.getProducts().equals(product) && (getProductQuantity(product_id) >= quantity)) {
                        prevQuantity = item.getQuanity();
                        item.setQuanity(quantity);
                        item.setPrice(item.getProducts().getPrice());
                        item.setAmount(quantity * item.getPrice());
                        neededItem = item;
                    }
                }
                newOrder.add(neededItem);

                //search for order tied to this product and make order details updated
                String hql = "from com.brands.dao.Orders o where o.orderNum =?";
                Query query = session.createQuery(hql).setParameter(0, neededItem.getId());
                Orders neededOrder = (Orders) query.uniqueResult();
                neededOrder.setOrderDetailses(newOrder);
                neededOrder.setAmount(neededItem.getAmount());
                session.beginTransaction();
                session.update(neededOrder);
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
                makeCart(user_id);
            }
        }

        return true;
    }

    public boolean addproductsAndMakeOrder(boolean orderDetailsAdded, int user_id) {
        if (orderDetailsAdded) {
            makeCart(user_id);
            return true ;
        }else {
            return false;
        }
    }
}
