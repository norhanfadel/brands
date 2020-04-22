var req = null;
var productId;
var productId2;

function makeOBJ() {
    if (window.XMLHttpRequest)
        req = new XMLHttpRequest();
    else if (window.ActiveXObject) {
        req = new ActiveXObject(Microsoft.XMLHTTP);
    }
}

function checkCode() {
    makeOBJ();
    req.onreadystatechange = handleStateChange;
    code = document.getElementById("code").value;
    url = "CartHandlerServlet" + "?code=" + code + "&timeStamp=" + new Date().getTime();
    req.open("GET", url, true);
    console.log("checkCode : " + code);
    req.send(null);
}

function handleStateChange() {
    if (req.readyState === 4 && req.status === 200) {
        xmlvalue = req.responseText;
        console.log("handleStateChange : " + xmlvalue);
        document.getElementById("userCreditValidation").innerHTML = xmlvalue;
        getCredit();
    }
}

function getCredit() {
    makeOBJ();
    req.onreadystatechange = handleStateChange2;
    url = "CartHandlerServlet";
    req.open("POST", url, true);
    console.log("getCredit !");
    req.send(null);
}

function handleStateChange2() {
    if (req.readyState === 4 && req.status === 200) {
        xmlvalue = req.responseText;
        console.log("handleStateChange : " + xmlvalue);
        document.getElementById("userCreditValue").innerHTML = xmlvalue;
    }
}

function getCart() { // make it onload
    makeOBJ();
    url = "CartHandlerServlet2";
    req.open("GET", url, true);
    console.log("getCart!");
    req.send(null);
}

function loadCart() {
    getCart();
    getCredit();
}

var shippingAdddressValid;

function buyCart() {
    if (!isCartEmpty()) {
        makeOBJ();
        req.onreadystatechange = handleStateChange3;
        var shippingAddress = $.trim($("#shippingAddress").val());
        debugger
        if (shippingAddress == "") {
            shippingAdddressValid = true;
        } else {
            shippingAdddressValid = false;
        }
        url = "BuyingServlet?address=" + shippingAddress;
        req.open("GET", url, true);
        console.log("buyCart!");
        req.send(null);
    } else {
        document.getElementById("buyingResult").innerHTML = "Add Products to Your Cart to shop With Us !";
    }
}

function handleStateChange3() {
    debugger
    if (req.readyState === 4 && req.status === 200) {
        buyingResult = req.responseText;
        console.log("handleStateChange : " + buyingResult);
        document.getElementById("buyingResult").innerHTML = buyingResult + "<br> For any issue Or note for us please Contact Us :) ";
        if (buyingResult == "Credit is Enough, so Cart is Bought !" && !shippingAdddressValid) {
            document.getElementById("ShippingNote").setAttribute("innerHTML", "(Your Shipping Address was Empty,<br> Order Sent on Your Default Address)");
            console.log("item bought");
            emptyCart();
            updateTotalPrice();
        } else if (buyingResult == "Credit is Enough, so Cart is Bought !" && shippingAdddressValid) {
            console.log("item bought");
            document.getElementById("ShippingNote").setAttribute("innerHTML", "(Don't Worry, Your default Address Will not Change)");
            emptyCart();
            updateTotalPrice();
        } else {
            console.log("item not bought");
            document.getElementById("ShippingNote").setAttribute("innerHTML", "(Don't Worry, Your default Address Will not Change)");
        }
        getCredit();

    }
}

function increase(id) {
    var linkid = $(id).attr("id");
    var prodId = linkid.substring(0, linkid.length - 1); // product id
    var userId = $(".userIdHidden").attr("id");// user id
    var quantitiy = parseInt($("#" + prodId).val())+1;// total quantity needed
    productId2 = prodId;//needed outside fn
    //sending product id to validate Quantity
    makeOBJ();
    req.onreadystatechange = handleStateChange4;
    url = "CartHandlerServlet3" + "?userId=" + userId + "&prodId=" + prodId + "&quantitiy=" + quantitiy;
    req.open("GET", url, true);
    console.log("increase : " + prodId);
    req.send(null);
}
var amountExceededFor ;

function handleStateChange4() {
    var quantitiy = parseInt($("#" + productId2).val());
    if (req.readyState === 4 && req.status === 200) {
        result = req.responseText;
        if (result == "true"){ // if valid in DB it will auto increase in DB
            $("#" + productId2).val(quantitiy + 1);
            updatePrice(productId2);
            updateTotalPrice(productId2, "add");
        }else{
            console.log("handleStateChange4 : " + result);
            document.getElementById("errorMsg"+productId2).innerHTML = " exceeded!";
            amountExceededFor = productId2+"true" ;
        }
    }
}
function decrease(id) {
    if (amountExceededFor == productId2+"true"){
        amountExceededFor = productId2+"false"
        document.getElementById("errorMsg"+productId2).innerHTML ="";
    }
    var linkid = $(id).attr("id");
    var quantId = linkid.substring(0, linkid.length - 1);
    productId2 = quantId;
    var quantitiy = parseInt($("#" + quantId).val());
    if (quantitiy > 1) {
        $("#" + quantId).val(quantitiy - 1);
        quantityGetting(quantId);
        updatePrice(quantId);
        updateTotalPrice(quantId, "sub");
    }
}

function updatePrice(id) {
    productId = id;
    var price = $("#" + id + "p").html();
    var here2 = (price.length) - 3;
    var price2 = price.substring(0, here2);
    var quant = parseInt($("#" + id).val());
    price2 = parseFloat(price2);
    var total = price2 * quant;
    $("#" + id + "a").html(total + " LE");
}

function updateTotalPrice(id, how, amount, removedProductPrice) {
    var price = $("#totalPrice").html();
    var totalPrice = price.substring(0, (price.length) - 3); // total price in string
    totalPrice = parseFloat(totalPrice); // total price in float
    if (id == undefined && how == undefined && amount == undefined && removedProductPrice == undefined) {
        totalPrice = 0 ;
    } else if (amount == undefined && removedProductPrice == undefined) {
        var productPrice = $("#" + id + "p").html();
        productPrice = productPrice.substring(0, (productPrice.length) - 3);// product price
        productPrice = parseFloat(productPrice); // product price in float
        if (how == "sub") {
            totalPrice -= productPrice;
        } else {
            totalPrice += productPrice;
        }
    } else {
        for (var i = 0; i < amount; i++) {
            totalPrice -= removedProductPrice;
        }
    }
    $("#totalPrice").html(totalPrice + " LE");
}

function emptyCart() {
    document.getElementsByTagName("tbody").item(0).innerHTML = "";
}

function removeItem(x) {
    var removedIdWithR = $(x).attr("id");
    var removedId = removedIdWithR.substring(0, removedIdWithR.length - 1);
    var amount = document.getElementById(removedId).value;
    var productPrice = document.getElementById(removedId + "p").innerHTML;
    productPrice = productPrice.substring(0, (productPrice.length) - 3);// product price
    productPrice = parseFloat(productPrice); // product price in float
    removeFromCart(removedId);
    var table = document.getElementById("CartTable");
    table.deleteRow(x.parentNode.parentNode.rowIndex);
    updateTotalPrice(removedId, "sub", amount, productPrice);
}

function removeFromCart(id) {
    makeOBJ();
    url = "RemoveFromCartServlet?productId=" + id;
    req.open("GET", url, true);
    console.log("removeFromCart!" + id);
    req.send(null);
}

function isCartEmpty() {
    var totalPrice =  $("#totalPrice").html();
    totalPrice = totalPrice.substring(0, (totalPrice.length) - 3); // total price in string
    totalPrice = parseFloat(totalPrice); // total price in float
    if (totalPrice == 0 || totalPrice == 0.0){
        return true;
    }
    else {
        return false ;
    }
}

function quantityGetting(id) {
    var prodId = id;//
    var userId = $(".userIdHidden").attr("id");//
    var quantitiy = parseInt($("#" + prodId).val());//
    productId = id;
    makeOBJ();
    url = "CartHandlerServlet3" + "?userId=" + userId + "&prodId=" + prodId + "&quantitiy=" + quantitiy;
    req.open("GET", url, true);
    console.log("updateQuantity !");
    req.send(null);
}

