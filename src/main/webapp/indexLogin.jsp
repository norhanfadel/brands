<%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 4/17/2020
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="cnour" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home | E-Shopper</title>
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
</head>
<body>



<header id="header"><!--header-->
    <div class="header_top"><!--header_top-->
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="contactinfo">
                        <ul class="nav nav-pills">
                            <li><a><i class="fa fa-phone"></i> (20)+1061977417</a></li>
                            <li><a href="#"><i class="fa fa-envelope"></i> eshopper000@gmail.com</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="social-icons pull-right">
                        <ul class="nav navbar-nav">
                            <li><a href="https://www.facebook.com/Eshopper-105586241114204/"><i
                                    class="fa fa-facebook"></i></a></li>
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
                        <a href="index.jsp"><img src="images/home/logo.png" alt=""/></a>
                    </div>
                    <div class="btn-group pull-right">

                    </div>
                </div>
                <div class="col-sm-8">
                    <div class="shop-menu pull-right">
                        <ul class="nav navbar-nav">
                            <cnour:if test="${!empty sessionScope.nameprofile}">
                                <li><a href="profile"><i class="fa fa-user"></i>Welcome ${requestScope.username}
                                </a></li>
                            </cnour:if>
                            <li><a href="cart.html"><i class="fa fa-shopping-cart"></i> Cart</a></li>


                            <cnour:if test="${!empty sessionScope.userId}" var="userId">
                                <li><a href="logOut"><i class="fa fa-user"></i>Log out </a></li>

                            </cnour:if>

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
                        <button type="button" class="navbar-toggle" data-toggle="collapse"
                                data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>

                    <div class="mainmenu pull-left">
                        <ul class="nav navbar-nav collapse navbar-collapse">
                            <li><a href="${pageContext.servletContext.contextPath}/HomeServlet?login=true" class="active">Home</a>
                            </li>
                            <!--                                    check that role equal Admin start-->
                                <cnour:if test="${requestScope.role.equals('ADMIN')}"  >

                                      <li ><a href="manageUser.jsp">Manage Users</a>
                                </li>
                                <li><a href="manageProduct.jsp">Manage Products</a>
                                    </cnour:if>
                                <!--                                    check that role equal Admin end-->
                            </li>
                            <!--                                    <li><a href="404.html">404</a></li>-->
                            <li><a href="contact-us.html">Contact</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="search_box pull-right">
                        <input type="text" placeholder="Search"/>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/header-bottom-->
</header><!--/header-->

<section id="slider"><!--slider-->
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div id="slider-carousel" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
                        <li data-target="#slider-carousel" data-slide-to="1"></li>
                        <li data-target="#slider-carousel" data-slide-to="2"></li>
                    </ol>

                    <div class="carousel-inner">
                        <div class="item active">
                            <div class="col-sm-6">
                                <h1><span>E</span>-SHOPPER</h1>
                                <h2> E-Commerce </h2>
                                <!--                                        <p> </p>-->
                                <button type="button" class="btn btn-default get">Get it now</button>
                            </div>
                            <div class="col-sm-6">
                                <img src="images/home/kids.png" class="girl img-responsive" alt=""/>
                                <!--                                        <img src="images/home/pricing.png"  class="pricing" alt="" />-->
                            </div>
                        </div>
                        <div class="item">
                            <div class="col-sm-6">
                                <h1><span>E</span>-SHOPPER</h1>
                                <h2>100% Responsive Design</h2>
                                <!--                                        <p>Lorem ipsu </p>-->
                                <button type="button" class="btn btn-default get">Get it now</button>
                            </div>
                            <div class="col-sm-6">
                                <img src="images/home/girl2.jpg" class="girl img-responsive" alt=""/>
                                <!--                                        <img src="images/home/pricing.png"  class="pricing" alt="" />-->
                            </div>
                        </div>

                        <div class="item">
                            <div class="col-sm-6">
                                <h1><span>E</span>-SHOPPER</h1>
                                <h2> Ecommerce </h2>
                                <!--                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>-->
                                <button type="button" class="btn btn-default get">Get it now</button>
                            </div>
                            <div class="col-sm-6">
                                <img src="images/home/men.png" class="girl img-responsive" alt=""/>
                                <!--                                        <img src="images/home/pricing.png" class="pricing" alt="" />-->
                            </div>
                        </div>

                    </div>

                    <a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
                        <i class="fa fa-angle-left"></i>
                    </a>
                    <a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
                        <i class="fa fa-angle-right"></i>
                    </a>
                </div>

            </div>
        </div>
    </div>
</section><!--/slider-->

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
            <div class="col-sm-9 padding-right">
                <c:if test="${requestScope.productsList !=null}">
                    <div class="features_items" id="home"><!--features_items-->

                        <h2 class="title text-center">Features Items</h2>
                        <c:forEach var="productsList" items="${requestScope.productsList}">
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <img src="data:image/jpg;base64,${productsList.imageName}" height="170px"
                                                 alt=""/>
                                            <h2><c:out value="${productsList.price}"/></h2>
                                            <p><c:out value="${productsList.description}"/></p>
                                            <a href="#" class="btn btn-default add-to-cart"><i
                                                    class="fa fa-shopping-cart"></i>Add to cart</a>
                                        </div>
                                        <div class="product-overlay">
                                            <div class="overlay-content">
                                                <h2><c:out value="${productsList.price}"/></h2>
                                                <p><c:out value="${productsList.description}"/></p>
                                                <a href="#" class="btn btn-default add-to-cart"><i
                                                        class="fa fa-shopping-cart"></i>Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </c:if>
                <!--                men category product start               -->
                <c:if test="${requestScope.menList !=null}">
                    <div class="features_items" id="men" ><!--features_items-->
                        <h2 class="title text-center">Features Items</h2>

                        <c:forEach var="menList" items="${requestScope.menList}">
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <img src="data:image/jpg;base64,${menList.imageName}" height="170px"
                                                 alt=""/>
                                            <h2><c:out value="${menList.price}"/></h2>
                                            <p><c:out value="${menList.description}"/></p>
                                            <a href="#" class="btn btn-default add-to-cart"><i
                                                    class="fa fa-shopping-cart"></i>Add to cart</a>
                                        </div>
                                        <div class="product-overlay">
                                            <div class="overlay-content">
                                                <h2><c:out value="${menList.price}"/></h2>
                                                <p><c:out value="${menList.description}"/></p>
                                                <a href="#" class="btn btn-default add-to-cart"><i
                                                        class="fa fa-shopping-cart"></i>Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>

                <!--                men category product end

                   <!--               womenList result start               -->
                <c:if test="${requestScope.womenList !=null}">
                    <div class="features_items" id="searchResult" ><!--features_items-->
                        <h2 class="title text-center">Features Items</h2>

                        <c:forEach var="womenList" items="${requestScope.womenList}">
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <img src="data:image/jpg;base64,${womenList.imageName}" height="170px"
                                                 alt=""/>
                                            <h2><c:out value="${womenList.price}"/></h2>
                                            <p><c:out value="${womenList.description}"/></p>
                                            <a href="#" class="btn btn-default add-to-cart"><i
                                                    class="fa fa-shopping-cart"></i>Add to cart</a>
                                        </div>
                                        <div class="product-overlay">
                                            <div class="overlay-content">
                                                <h2><c:out value="${womenList.price}"/></h2>
                                                <p><c:out value="${womenList.description}"/></p>
                                                <a href="#" class="btn btn-default add-to-cart"><i
                                                        class="fa fa-shopping-cart"></i>Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </c:if>
                <!--                women category product end               -->

                <!--                kids category product start               -->

                <c:if test="${requestScope.kidsList !=null}">
                    <div class="features_items" id="kids" ><!--features_items-->
                        <h2 class="title text-center">Features Items</h2>
                        <c:forEach var="kidsList" items="${requestScope.kidsList}">
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <img src="data:image/jpg;base64,${kidsList.imageName}" height="170px"
                                                 alt=""/>
                                            <h2><c:out value="${kidsList.price}"/></h2>
                                            <p><c:out value="${kidsList.description}"/></p>
                                            <a href="#" class="btn btn-default add-to-cart"><i
                                                    class="fa fa-shopping-cart"></i>Add to cart</a>
                                        </div>
                                        <div class="product-overlay">
                                            <div class="overlay-content">
                                                <h2><c:out value="${kidsList.price}"/></h2>
                                                <p><c:out value="${kidsList.description}"/></p>
                                                <a href="#" class="btn btn-default add-to-cart"><i
                                                        class="fa fa-shopping-cart"></i>Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </c:if>
                <!--                kids category product end               -->
                <!--                search Result start               -->
                <c:if test="${requestScope.list !=null}">
                    <div class="features_items" id="searchResult" style="display: block"><!--features_items-->
                        <h2 class="title text-center">Features Items</h2>
                        <c:forEach var="searchList" items="${requestScope.list}">
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <img src="data:image/jpg;base64,${searchList.imageName}" height="170px"
                                                 alt=""/>
                                            <h2><c:out value="${searchList.price}"/></h2>
                                            <p><c:out value="${searchList.description}"/></p>
                                            <a href="#" class="btn btn-default add-to-cart"><i
                                                    class="fa fa-shopping-cart"></i>Add to cart</a>
                                        </div>
                                        <div class="product-overlay">
                                            <div class="overlay-content">
                                                <h2><c:out value="${searchList.price}"/></h2>
                                                <p><c:out value="${searchList.description}"/></p>
                                                <a href="#" class="btn btn-default add-to-cart"><i
                                                        class="fa fa-shopping-cart"></i>Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <!--               search reasult end               -->

            </div>
        </div>
    </div>
</section>

<footer id="footer">
    <div class="footer-bottom">
        <div class="container">
            <div class="row">
                <p class="pull-left">Copyright © 2020 JETS ITI Inc. All rights reserved.</p>
                <p class="pull-right">Designed by <span><a target="_blank"
                                                           href="http://www.iti.gov.eg/Admission/PTPprogram/intake37/WMADtrack">JETS</a></span>
                </p>
            </div>
        </div>
    </div>

</footer><!--/Footer-->


<script src="js/home.js"></script>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.scrollUp.min.js"></script>
<script src="js/price-range.js"></script>
<script src="js/jquery.prettyPhoto.js"></script>
<script src="js/main.js"></script>
<script>

    $(document).ready(function () {
        $("#womenLink").css("background-color", "white");
        $("#womenLink").css("background-color", "white");
        $("#kidsLink").css("background-color", "white");
    });
</script>
    </body>
    < /html>
