<%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 4/19/2020
  Time: 4:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cnour" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Contact | E-Shopper</title>
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
</head> <body onload="clearrr()">
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
                            <cnour:if test="${sessionScope.role==null}">
                                <% RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                                    rd.forward(request,response);%>
                            </cnour:if>
                            <cnour:if test="${!empty sessionScope.nameprofile}">
                                <li><a href="profile"><i class="fa fa-user"></i>Welcome ${sessionScope.nameprofile}
                                </a></li>
                            </cnour:if>
                            <li><a href="${pageContext.servletContext.contextPath }/CartHandlerServlet2"
                            ><i class="fa fa-shopping-cart"></i> Cart</a></li>



                            <cnour:if test="${!empty sessionScope.userId}"   >
                                <li><a  href="logOut" ><i class="fa fa-user"></i>Log out  </a></li>

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
                            <li><a href="HomeServlet">Home</a></li>

                            <cnour:if test="${requestScope.role.equals('ADMIN')}"  >

                            <li ><a href="manageUser.jsp">Manage Users</a>
                            </li>
                            <li><a href="manageProduct.jsp">Manage Products</a>
                                </cnour:if>

                            <li><a href="contact-us.jsp">Contact</a></li>

                        </ul>
                    </div>
                </div>
                <div class="col-sm-3">

                </div>
            </div>
        </div>
    </div>
</header>
<div id="contact-page" class="container">
    <div class="bg">
        <div class="row">
            <div class="col-sm-12">
                <h2 class="title text-center">Contact <strong>Us</strong></h2>
                <div id="GMaps" class="contact-map">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d13811.220605482977!2d31.0207521!3d30.0711192!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x88f29d027c44f959!2z2YXYudmH2K8g2KrZg9mG2YjZhNmI2KzZitinINin2YTZhdi52YTZiNmF2KfYqg!5e0!3m2!1sar!2seg!4v1586475379572!5m2!1sar!2seg" width="1100" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>

                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-8">
                <div >
                    <h2 class="title text-center">Get In Touch</h2>
<%--                    <div class="status alert alert-success" style="display: none"></div>--%>
                    <form method="post" action="Contact" >
                        <cnour:if test="${!empty requestScope.nameprofile}">

                            <input type="text" name="name"  value="${requestScope.nameprofile}" class="form-control" required="required" placeholder="Name">

                        </cnour:if>

                        <cnour:if test="${!empty requestScope.emailprofile}">
                            <input type="email" name="email"  id="email" value="${requestScope.emailprofile}"  disabled="disabled" class="form-control" required="required" placeholder="Email">  </cnour:if>


                        <input type="text" name="subjectMsg" class="form-control"  id="subjectMsg"required="required" placeholder="Subject">

                        <textarea name="Msg" required="required" id="Msg"  rows="14" placeholder="Your Message Here"></textarea>

                        <button type="submit" value = "Submit" class="btn btn-default">Send</button>
                        <cnour:if test="${!empty requestScope.true3}"  var="res2">
                            <i class="fa-li fa fa-check-square"></i>  <label id="labelID1" style="color:green;font-size: 20px">Message Send  </label> </cnour:if>
                    </form>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="contact-info">
                    <h2 class="title text-center">Contact Info</h2>
                    <address>
                        <p>JETS ITI.</p>
                        <p>smart village</p>

                        <p>Mobile: (20)+1061977417</p>

                        <p>Email: eshopper000@gmail.com</p>
                    </address>
                    <div class="social-networks">
                        <h2 class="title text-center">Social Networking</h2>
                        <ul>
                            <li>
                                <a href="https://www.facebook.com/Eshopper-105586241114204/"><i class="fa fa-facebook"></i></a>
                            </li>
                            <li>
                                <a href="https://twitter.com/explore"><i class="fa fa-twitter"></i></a>
                            </li>

                            <li>
                                <a href="https://youtube.com/"><i class="fa fa-youtube"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div><!--/#contact-page-->
                                </section>
                            <footer id="footer"     >
                                <div class="footer-bottom" >
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
                                <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?    v=3.exp&sensor=false&libraries=places,drawing,geometry"></script>
<script>
                            function clearrr(){
                            console.clear();
                            }
</script>
<script src="js/contact.js"></script>
<script src="js/price-range.js"></script>
<script src="js/jquery.scrollUp.min.js"></script>
<script src="js/jquery.prettyPhoto.js"></script>
<script src="js/main.js"></script>
</body>
</html>