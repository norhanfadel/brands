var req = null;

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

function buyCart() {

}

function increase(id) {
    var linkid = $(id).attr("id");
    var quantId = linkid.substring(0,linkid.length-1);
    var quantitiy =parseInt($("#"+quantId).val());
    $("#"+quantId).val(quantitiy+1);
    updateQuantity();
}

function decrease(id) {
    //debugger
    var linkid = $(id).attr("id");
    var quantId = linkid.substring(0,linkid.length-1);
    var quantitiy =parseInt($("#"+quantId).val());
    if(quantitiy>0){
        $("#"+quantId).val(quantitiy-1);
        updateQuantity();
    }

}

function  updateQuantity(one) {
    debugger
   // var father = $("#quantityNeeded").children()
    alert(one);
    var prodId = $(one).attr("id");//
    var userId = $(".userIdHidden").attr("id");//
    var quantitiy  = parseInt($("#"+prodId).val());//
    alert(userId+" "+prodId+" "+quantitiy);
    makeOBJ();
    req.onreadystatechange = handleStateChange3;
    url = "CartHandlerServlet3";
    req.open("POST", url, true);
    console.log("updateQuantity !");
    req.send("userId="+userId+"&prodId"+prodId+"&quantitiy"+quantitiy);

}
function handleStateChange3() {
    if (req.readyState === 4 && req.status === 200) {
        var isEnough = req.responseText;
        console.log("handleStateChange : " + isEnough);
        if (isEnough == "false"){
            document.getElementById("errorMsg").innerHTML = "Quantity Exceeded";
        }else {
            document.getElementById("errorMsg").innerHTML = "";
        }
    }
}

