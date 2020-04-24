<%--
  Created by IntelliJ IDEA.
  User: Mohemed
  Date: 2020-04-20
  Time: 3:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%><html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Product Details | E-Shopper</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
<header id="header"><!--header-->
    <div class="header_top"><!--header_top-->
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="contactinfo">
                        <ul class="nav nav-pills">
                            <li><a ><i class="fa fa-phone"></i> (20)+1061977417</a></li>
                            <li><a href="#"><i class="fa fa-envelope"></i> eshopper000@gmail.com</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="social-icons pull-right">
                        <ul class="nav navbar-nav">
                            <li><a href="https://www.facebook.com/Eshopper-105586241114204/"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="https://twitter.com/explore"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="https://www.linkedin.com/feed/"><i class="fa fa-linkedin"></i></a></li>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/header_top-->

    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="index.jsp"><img src="images/home/logo.png" alt="" /></a>
                    </div>

                </div>
                <div class="col-sm-8">
                    <div class="shop-menu pull-right">
                        <ul class="nav navbar-nav">
                            <c:if test="${!empty sessionScope.nameprofile}">
                                <li><a href="profile"><i class="fa fa-user"></i>Welcome ${sessionScope.nameprofile}
                                </a></li>
                            </c:if>
                            <li><a href="${pageContext.servletContext.contextPath }/CartHandlerServlet2"><i class="fa fa-shopping-cart"></i> Cart</a></li>
                            <c:if test="${empty sessionScope.userId}" var="userId">
                                <li><a href="login.jsp"><i class="fa fa-lock"></i> Login</a></li>
                            </c:if>
                            <c:if test="${!empty sessionScope.userId}" var="userId">
                                <li><a href="logOut"><i class="fa fa-user"></i>Log out </a></li>

                            </c:if>

                        </ul>


                    </div>
                </div>
            </div>
        </div>
    </div><!--/header-middle-->

    <div class="header-bottom"><!--header-bottom-->
        <div class="container">
            <div class="row">
                <div class="col-sm-9">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div class="mainmenu pull-left">
                        <ul class="nav navbar-nav collapse navbar-collapse">
                            <li><a href="${pageContext.servletContext.contextPath}/HomeServlet" >Home</a>
                            </li>
                            <!--                                    check that role equal Admin start-->
                            <c:if test="${sessionScope.role.equals('ADMIN')}">

                            <li ><a href="ManageUsersServlet">Manage Users</a>
                            </li>
                            <li><a href="ManageProduct">Manage Products</a>
                                </c:if>
                                <!--                                    check that role equal Admin end-->
                            </li>
                            <!--                                    <li><a href="404.html">404</a></li>-->
                            <li><a href="contact-us.html">Contact</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3">

                </div>
            </div>
        </div>
    </div><!--/header-bottom-->
</header><!--/header-->

<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="left-sidebar">
                    <h2>Category</h2>
                    <div class="panel-group category-products" id="accordian"><!--category-productsr-->

                        <div class="panel panel-default">
                            <div class="panel-heading" id="menLink">
                                <h4 class="panel-title"><a href="${pageContext.servletContext.contextPath }/MenProductServlet" onclick="showMenProduct()" style="width: 100%">MEN</a>
                                </h4>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading" id="womenLink">
                                <h4 class="panel-title"><a href="${pageContext.servletContext.contextPath }/WomenProductServlet" onclick="showWomenProduct()"
                                                           style="width: 100%">WOMEN</a></h4>
                            </div>
                        </div>

                        <div class="panel panel-default">
                            <div class="panel-heading" id="kidsLink">
                                <h4 class="panel-title"><a href="${pageContext.servletContext.contextPath }/KidsProductServlet" onclick="showKidsProduct()" style="width: 100%">Kids</a>
                                </h4>
                            </div>
                        </div>


                    </div>
                </div>
            </div>

            <c:if test="${! empty requestScope.productDetails}">
            <div class="col-sm-9 padding-right">
                <div class="product-details"><!--product-details-->
                    <div class="col-sm-5">
                        <div class="view-product">
                            <img src="data:image/jpg;base64,${requestScope.productDetails.imageName}" alt="" />
                        </div>
                    </div>
                    <div class="col-sm-7">
                        <div class="product-information"><!--/product-information-->
                            <img src="images/product-details/new.jpg" class="newarrival" alt="" />
                            <h2> <c:out value="${requestScope.productDetails.name}"/></h2>
                            <p>Web ID: 1089772</p>
                            <img src="images/product-details/rating.png" alt="" />
                            <span>
                                        <span>US $<c:out value="${requestScope.productDetails.price}"/></span>
                                        <label>Quantity:</label>
                                        <input type="text" id="productQuantity" value="<c:out value="${requestScope.productDetails.quantity}"/>" />
                                        <button type="button" class="btn btn-fefault cart" onclick="updateQuantity(${requestScope.productDetails.productId},${sessionScope.userId})">
                                            <i class="fa fa-shopping-cart"></i>
                                            Add to cart
                                        </button>
                                    </span>
                            <p><b>Created in:</b> <c:out value="${requestScope.productDetails.createDate}"/> <b id="errorMsg" style="color: red"></b></p>
                            <p><b>Description:</b> <c:out value="${requestScope.productDetails.description}"/></p>
                            <p><b>Category:</b> <c:out value="${requestScope.productDetails.category.name}"/></p>

                        </div><!--/product-information-->
                    </div>
                </div><!--/product-details-->
                </c:if>


            </div>
        </div>
    </div>

</section>

<footer id="footer"><!--Footer-->
    <div class="footer-bottom">
        <div class="container">
            <div class="row">
                <p class="pull-left">Copyright © 2020 JETS ITI Inc. All rights reserved.</p>
                <p class="pull-right">Designed by <span><a target="_blank" href="http://www.iti.gov.eg/Admission/PTPprogram/intake37/WMADtrack">JETS</a></span></p>
            </div>
        </div>
    </div>

</footer><!--/Footer-->



<script src="js/jquery.js"></script>
<script src="js/price-range.js"></script>
<script src="js/jquery.scrollUp.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.prettyPhoto.js"></script>
<script src="js/main.js"></script>
<script>
    function makeOBJ() {
        if (window.XMLHttpRequest)
            req = new XMLHttpRequest();
        else if (window.ActiveXObject) {
            req = new ActiveXObject(Microsoft.XMLHTTP);
        }
    }
    function updateQuantity(productId,userId) {
        var quantity = parseInt(document.getElementById("productQuantity").value);
        if (quantity<1){
            document.getElementById("productQuantity").value = 1 ;
            document.getElementById("errorMsg").innerHTML = "can't be less than 1 !";
        }else{
            debugger
            document.getElementById("errorMsg").innerHTML = "";
            makeOBJ();
            req.onreadystatechange = handleStateChange;
            url = "AddToCartServlet" + "?quantity=" + quantity+"&productId="+productId+"&userId="+userId;
            req.open("GET", url, true);
            console.log("updateQuantity : " + quantity + " " + productId + " " + userId);
            req.send(null);
        }

    }
    function handleStateChange() {
        console.log("in handling method");
    }
</script>
</body>
</html>