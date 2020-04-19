package com.brands.dto;

import com.brands.dao.Category;
import com.brands.dao.Products;
import com.brands.dao.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Test {


    static Session session = MySessionFactory.getMySession();

    public static void main(String[] args) {

//        Users user = new Users();
//        session.beginTransaction();
//       session.persist(cart);
//        session.persist(user);
//        session.getTransaction().commit();

//        UserImp userImp = new UserImp();
//        Users userX = userImp.getUserById(userImp.getUserIdByMail(user.getEmail()));
//        userX.setAddress("cairo");
//        boolean isItTrue = userImp.updateUser(userX);
//        System.out.println(isItTrue);

        // user=(Users) session.get(Users.class,7);
//        if(!userImp.login(user)){
//            System.out.println("false");
//        }
//        CreditAdding creditAdding = new CreditAdding("500x", 10000);
//        session.beginTransaction();
//        session.persist(creditAdding);
//        session.getTransaction().commit();
//        Users user1 = (Users) session.get(Users.class, 3);
//        userImp.addCredit(creditAdding.getCode(), user1);
//        // userImp.register(user,cart);
//        System.out.println("done");
//
//        Products products = new Products(2, user1.getCart(), user1, 2, 500);
//        session.beginTransaction();
//        session.persist(products);
//        session.getTransaction().commit();
//        userImp.updateCreditWhenBuying(user1);
//        System.out.println("done");
        badriTest();
        //categ();
        System.out.println("done");

    }

    public static void badriTest() {

        ProductDto productDto = new ProductImp();

        Products p = (Products) session.load(Products.class, 7);
        p.setName("hana");
        System.out.println(p);
        productDto.updateProduct(p);
        System.out.println("updated");
//        CategoryDto categoryDto=new CategoryImpl();
//        System.out.println("11");
//        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
//
//        Products products=new Products(categoryDto.getCategoryById(1),date,"name",300);
//
//        System.out.println("12");
//productDto.addProduct(products);


//        System.out.println("inside");
//        Category category = new Category("t-shirt");
//        session.beginTransaction();
//        session.persist(category);
//        session.getTransaction().commit();
//        ProductDto productDto = new ProductImp();

        ////////
        System.out.println("inside");
//        Category category = new Category("t-shirt");
//        session.beginTransaction();
//        session.persist(category);
//        session.getTransaction().commit();
//        ProductDto productDto = new ProductImp();
//        List<Products> allProducts = productDto.getAllProducts();
//        System.out.println("after");
//        System.out.println(allProducts.size());
//        for(Products product : allProducts){
//            System.out.println("inside loop ");
//            System.out.println(product.getName() + " badri");
//        }

//        Products product = productDto.addProduct(new Products(category, new Date(), "jeans", 12));
        // Products product = productDto.addProduct(new Products(category, new Date(), "jeans", 12));

        // System.out.println(product.getName());

        //        System.out.println(product.getName());
        CategoryDto categoryDto = new CategoryImpl();
        Category categoryById = categoryDto.getCategoryById(3);
        System.out.println(categoryById.getName());
        Products oldProduct = productDto.getProductById(6);
        System.out.println(oldProduct.getName());
        oldProduct.setDescription("hesham desc");
        oldProduct.setName("hesham");
        oldProduct.setCreateDate(new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime());
        oldProduct.setCategory(categoryDto.getCategoryById(3));
        productDto.updateProduct(oldProduct);

    }

    public static void categ() {
        CategoryDto categoryDto = new CategoryImpl();
        Category categoryById = categoryDto.getCategoryById(1);
        Category men = categoryDto.getCategoryByName("men");
        System.out.println("name" + categoryById.getName());
        System.out.println("id" + men.getCategoryId());

    }


    public static void testAddProduct() {

        Connection conn = null;
        PreparedStatement pstmt = null;
        FileInputStream fis = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost/brands";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, "root", "");
            File image = new File("d:\\kids3.jpg");
            fis = new FileInputStream(image);
            pstmt = conn.prepareStatement("insert into products (Create_Date,Image,Name,Price,category_id,description)" + "values(?,?,?,?,?,?)");
            pstmt.setString(1, "2017-06-15");
            pstmt.setBinaryStream(2, (InputStream) fis, (int) (image.length()));
            pstmt.setString(3, "skirt");
            pstmt.setString(4, "400");
            pstmt.setInt(5, 1);
            pstmt.setString(6, "hikhji");
            fis = new FileInputStream(image);
            int count = pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
