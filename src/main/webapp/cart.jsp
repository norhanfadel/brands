<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Cart | E-Shopper</title>
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
    <script src="js/checkCode.js"></script>
    <script>

    </script>
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body onload="loadCart()">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cnour" uri="http://java.sun.com/jsp/jstl/core" %>

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
                                <li><a href="profile"><i class="fa fa-user"></i>Welcome ${sessionScope.nameprofile}
                                </a></li>
                            </cnour:if>
                            <li><a href="${pageContext.servletContext.contextPath }/CartHandlerServlet2"><i
                                    class="fa fa-shopping-cart"></i> Cart</a></li>
                            <cnour:if test="${empty sessionScope.userId}" var="userId">
                                <li><a href="login.jsp"><i class="fa fa-lock"></i> Login</a></li>
                            </cnour:if>
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
                            <li><a href="${pageContext.servletContext.contextPath}/HomeServlet" class="active">Home</a>
                            </li>
                            <!--                                    check that role equal Admin start-->
                            <cnour:if test="${sessionScope.role.equals('ADMIN')}">

                            <li><a href="ManageUsersServlet">Manage Users</a>
                            </li>
                            <li><a href="ManageProduct">Manage Products</a>
                                </cnour:if>
                                <!--                                    check that role equal Admin end-->
                            </li>
                            <!--                                    <li><a href="404.html">404</a></li>-->
                            <li><a href="contact-us.jsp">Contact</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div>
                        <form action="SearchServlet">
                            <div class="search_box " style="display: inline">

                                <input type="text" placeholder="Search" name="search"/>
                            </div>
                            <input type="submit" value="search" id="searchButton"
                                   style="  height: 35px;border-radius: 5px; background: #fdb45e;">
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div><!--/header-bottom-->
</header><!--/header-->

<section id="cart_items">
    <div class="container">
        <div class="breadcrumbs">
            <ol class="breadcrumb">
                <li><a href="indexLogin.jsp">Home</a></li>
                <li class="active">Shopping Cart</li>
            </ol>
        </div>
        <div class="table-responsive cart_info">
            <table class="table table-condensed" id="CartTable">
                <thead>
                <tr class="cart_menu">
                    <td class="image">Item</td>
                    <td class="description"></td>
                    <td class="price">Price</td>
                    <td class="quantity">Quantity</td>
                    <td class="total">Total</td>
                    <td></td>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${sessionScope.cartItems}" var="item">
                    <tr>
                        <td class="cart_product">
                            <a href=""><img src="data:image/jpg;base64,${item.products.imageName}
" alt=""></a> <!-- ask for getting the img source-->
                        </td>
                        <td class="cart_description">
                            <h3>${item.products.name}</h3>
                            <h4>${item.products.description}</h4>
                        </td>
                        <td class="cart_price">
                            <p id="${item.products.productId}p">${item.products.price} LE</p>
                        </td>
                        <td class="cart_quantity">
                            <div class="cart_quantity_button">
                                <a id="${item.products.productId}i" name="plusX" class="cart_quantity_up"
                                   onclick="increase(this)"> + </a> <!-- should make fn to update quantity AJAX-->
                                <input id="${item.products.productId}" class="cart_quantity_input" type="text"
                                       name="quantity" value="${item.quanity}" disabled="true"
                                       size="2" onchange="quantityGetting(this)">
                                <a id="${item.products.productId}d" class="cart_quantity_down" onclick="decrease(this)">
                                    - </a>
                                <input type="hidden" id="${sessionScope.userId}" class="userIdHidden">
                                <span id="errorMsg${item.products.productId}" style="font-size:10px; color:red;"></span>
                            </div>
                        </td>
                        <td class="cart_total">
                            <p class="cart_total_price" id="${item.products.productId}a">${item.amount} LE </p>
                        </td>
                        <td class="cart_delete">
                            <a class="cart_quantity_delete"  id="${item.products.productId}r" onclick="removeItem(this)"><i class="fa fa-times" ></i></a>
                            <!-- should make fn to remove item AJAX  -->
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section> <!--/#cart_items-->

<section id="do_action">
    <div class="container">
        <div class="heading">
            <h3>What would you like to do next?</h3>
            <p>Enter Your voucher Code to Earn Credit ! </p>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <div class="chose_area">
                    <ul class="user_option">
                        <li class="single_field">
                            <label>Use Coupon Code</label>
                            <input type="text" id="code" onblur="checkCode()">
                            <label id="userCreditValidation"></label> <!-- should view if valid code or not-->
                        </li>
                        <li class="single_field">
                            <label id="userCreditValue"></label>
                            <!-- should view user Credit before and after update -->
                        </li>
                    </ul>
                    <ul class="user_info">
                        <li class="single_field">
                            <label>Shipping address:</label>
                            <label style="font-size:14px; color:forestgreen;" id="ShippingNote">(Don't Worry, Your default Address Will not Change)</label>
                            <textarea id="shippingAddress">${sessionScope.userAddress}</textarea>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="total_area">
                    <ul>
                        <li>Shipping Cost <span>Free !</span></li>
                        <li>Total <span id="totalPrice">${sessionScope.totalAmount} LE</span></li>
                    </ul>
                    <a class="btn btn-default check_out" onclick="buyCart()" >Check Out</a>
                    <label id="buyingResult"></label>
                </div>
            </div>
        </div>
    </div>
</section><!--/#do_action-->

<footer id="footer"><!--Footer-->
    <!--            <div class="footer-top">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-2">
                                <div class="companyinfo">
                                    <h2><span>e</span>-shopper</h2>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,sed do eiusmod tempor</p>
                                </div>
                            </div>
                            <div class="col-sm-7">
                                <div class="col-sm-3">
                                    <div class="video-gallery text-center">
                                        <a href="#">
                                            <div class="iframe-img">
                                                <img src="images/home/iframe1.png" alt="" />
                                            </div>
                                            <div class="overlay-icon">
                                                <i class="fa fa-play-circle-o"></i>
                                            </div>
                                        </a>
                                        <p>Circle of Hands</p>
                                        <h2>24 DEC 2014</h2>
                                    </div>
                                </div>

                                <div class="col-sm-3">
                                    <div class="video-gallery text-center">
                                        <a href="#">
                                            <div class="iframe-img">
                                                <img src="images/home/iframe2.png" alt="" />
                                            </div>
                                            <div class="overlay-icon">
                                                <i class="fa fa-play-circle-o"></i>
                                            </div>
                                        </a>
                                        <p>Circle of Hands</p>
                                        <h2>24 DEC 2014</h2>
                                    </div>
                                </div>

                                <div class="col-sm-3">
                                    <div class="video-gallery text-center">
                                        <a href="#">
                                            <div class="iframe-img">
                                                <img src="images/home/iframe3.png" alt="" />
                                            </div>
                                            <div class="overlay-icon">
                                                <i class="fa fa-play-circle-o"></i>
                                            </div>
                                        </a>
                                        <p>Circle of Hands</p>
                                        <h2>24 DEC 2014</h2>
                                    </div>
                                </div>

                                <div class="col-sm-3">
                                    <div class="video-gallery text-center">
                                        <a href="#">
                                            <div class="iframe-img">
                                                <img src="images/home/iframe4.png" alt="" />
                                            </div>
                                            <div class="overlay-icon">
                                                <i class="fa fa-play-circle-o"></i>
                                            </div>
                                        </a>
                                        <p>Circle of Hands</p>
                                        <h2>24 DEC 2014</h2>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="address">
                                    <img src="images/home/map.png" alt="" />
                                    <p>505 S Atlantic Ave Virginia Beach, VA(Virginia)</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="footer-widget">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-2">
                                <div class="single-widget">
                                    <h2>Service</h2>
                                    <ul class="nav nav-pills nav-stacked">
                                        <li><a href="">Online Help</a></li>
                                        <li><a href="">Contact Us</a></li>
                                        <li><a href="">Order Status</a></li>
                                        <li><a href="">Change Location</a></li>
                                        <li><a href="">FAQ’s</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <div class="single-widget">
                                    <h2>Quock Shop</h2>
                                    <ul class="nav nav-pills nav-stacked">
                                        <li><a href="">T-Shirt</a></li>
                                        <li><a href="">Mens</a></li>
                                        <li><a href="">Womens</a></li>
                                        <li><a href="">Gift Cards</a></li>
                                        <li><a href="">Shoes</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <div class="single-widget">
                                    <h2>Policies</h2>
                                    <ul class="nav nav-pills nav-stacked">
                                        <li><a href="">Terms of Use</a></li>
                                        <li><a href="">Privecy Policy</a></li>
                                        <li><a href="">Refund Policy</a></li>
                                        <li><a href="">Billing System</a></li>
                                        <li><a href="">Ticket System</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <div class="single-widget">
                                    <h2>About Shopper</h2>
                                    <ul class="nav nav-pills nav-stacked">
                                        <li><a href="">Company Information</a></li>
                                        <li><a href="">Careers</a></li>
                                        <li><a href="">Store Location</a></li>
                                        <li><a href="">Affillate Program</a></li>
                                        <li><a href="">Copyright</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-sm-3 col-sm-offset-1">
                                <div class="single-widget">
                                    <h2>About Shopper</h2>
                                    <form action="#" class="searchform">
                                        <input type="text" placeholder="Your email address" />
                                        <button type="submit" class="btn btn-default"><i class="fa fa-arrow-circle-o-right"></i></button>
                                        <p>Get the most recent updates from <br />our site and be updated your self...</p>
                                    </form>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>-->

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


<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.scrollUp.min.js"></script>
<script src="js/jquery.prettyPhoto.js"></script>
<%--<script src="js/main.js"></script>--%>
</body>
</html>