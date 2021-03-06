<%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 4/15/2020
  Time: 9:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="cnour" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login | E-Shopper</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">

    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body onload="checkCookie()" errorPage="404.jsp">
<header id="header"><!--header-->
    <div class="header_top"><!--header_top-->
        <div class="container">
            <div class="row">
                <div class="col-sm-6 ">
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

                </div>
                <div class="col-sm-8">
                    <div class="shop-menu pull-right">

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
                            <li><a href="${pageContext.servletContext.contextPath }/HomeServlet">Home</a></li>


                        </ul>
                    </div>
                </div>
                <div class="col-sm-3">

                </div>
            </div>
        </div>
    </div>
</header>
<%
    String paramName = "notvalid";
    String paramValue = request.getParameter(paramName);
%>

<section><!--form-->
    <div class="container">
        <div class="row">
            <div class="col-sm-4 col-sm-offset-1">
                <div class="login-form"><!--login form-->
                    <h2>Login to your account</h2>
                    <form method="post" action="Login">


                        <input type="email" placeholder="Email Address" name="email" id="email"/>
                        <br>
                        <input type="password" placeholder="password" name="password" id="password"/>
                        <br>
                        <%--                                <%if (paramValue!=null) {--%>


                        <%--                                %>--%>
                        <cnour:if test="${!empty requestScope.user}" var="res">
                            <label id="labelID" style="color:red;font-size: 20px">Sorry UserName or Password
                                Invalid! </label> </cnour:if>
                        <%--                    <%    } %>--%><!--/login form-->
                        <span>

                    <input type="checkbox" class="checkbox" id="remember">
								Keep me signed in
							</span>
                        <button type="submit" value="Submit" onclick="checkCookie1()" id="SubmitButton">Login</button>
                        <br>

                    </form>

                </div>

            </div>
            <div class="col-sm-1">
                <h2 class="or">OR</h2>
            </div>
            <div class="col-sm-4">
                <div class="signup-form"><!--sign up form-->
                    <h2>New User Signup!</h2>
                    <form action="Registration" method="post">
                        <input type="text" placeholder="Name" name="name" id="name" required/>
                        <input type="email" placeholder="Email Address" name="emailRegistration" id="emailRegistration"
                               required/>
                        <input type="password" placeholder="Password" name="passwordRegistration"
                               id="passwordRegistration" required/>
                        <input type="text" placeholder="Address" name="address" id="address" required>
                        <input type="text" placeholder="Phone" onblur="submitForm1()" name="phone" id="phone" required>
                        <label id="labelID" style="color: red"></label>
                        <input type="number" placeholder="Welcome Code" name="welcomeCode" id="welcomeCode"
                               onblur="submitForm2()">
                            <label id="labelwelcome" style="color: orange"></label>

                        <br>
                        <button type="submit" value="Submit" class="btn btn-default">Signup</button>

                        <i class="fa-li fa fa-check-square"></i> <label id="labelID1"
                                                                        style="color:red;font-size: 20px">${requestScope.falseCode} </label>
                        <i class="fa-li fa fa-check-square"></i> <label id="labelID1"
                                                                        style="color:green;font-size: 20px">${requestScope.trueCode} </label>

                        <cnour:if test="${!empty requestScope.true1}" var="res2">


                            <i class="fa-li fa fa-check-square"></i> <label id="labelID1"
                                                                            style="color:green;font-size: 20px">Regestration
                            Done Lets Login </label> </cnour:if>
                    </form>

                    <cnour:if test="${!empty requestScope.register}" var="res1">
                        <label id="labelID1" style="color:red;font-size: 20px">This Account is aleadry
                            Exist! </label> </cnour:if>


                    <script>
                        function submitForm1() {
                            if (window.XMLHttpRequest)
                                req = new XMLHttpRequest();
                            else if
                            (window.ActiveXObject)
                                req = new ActiveXObject(Microsoft.XMLHTTP);
                            yourvalue = document.getElementById("phone").value;
                            url = "Phone";
                            req.open("POST", url, true);
                            req.setRequestHeader("content-type", "application/x-www-form-urlencoded");
                            req.onreadystatechange = handleStateChange1;
                            req.send("uPhone=" + yourvalue);
                        }

                        function handleStateChange1() {
                            console.log("from statechage");
                            if (req.status == 200) {

                                xmlvalue = req.responseText;
                                console.log("from statechage2" + xmlvalue);
                                document.getElementById("labelID").innerHTML = xmlvalue;
                            }
                        }

                        function submitForm2() {
                            if (window.XMLHttpRequest)
                                req = new XMLHttpRequest();
                            else if
                            (window.ActiveXObject)
                                req = new ActiveXObject(Microsoft.XMLHTTP);
                            yourvaluee = document.getElementById("welcomeCode").value;
                            url = "Code";
                            req.open("POST", url, true);
                            req.setRequestHeader("content-type", "application/x-www-form-urlencoded");
                            req.onreadystatechange = handleStateChange2;
                            req.send("welcomeCode=" + yourvaluee);
                        }

                        function handleStateChange2() {
                            console.log("from statechage");
                            if (req.status == 200) {

                                xmlvalue = req.responseText;
                                console.log("from statechage2" + xmlvalue);
                                document.getElementById("labelwelcome").innerHTML = xmlvalue;



                            }
                        }

                    </script>
                    <br>
                    <br>
                </div><!--/sign up form-->
            </div>
        </div>
    </div>

</section><!--/form-->


<footer id="footer" style="position: absolute;bottom: 0px ;width: 100%;">


    <div class="footer-bottom" style="margin-top: 0px ">
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
<script src="js/price-range.js"></script>
<script src="js/jquery.scrollUp.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.prettyPhoto.js"></script>
<script src="js/main.js"></script>
<script src="js/cookies.js"></script>
<script>
</script>
</body>
</html>