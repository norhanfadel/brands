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
    console.log("checkCode : " +code);
    req.send(null);
}
function handleStateChange() {
    if (req.readyState === 4 && req.status === 200) {
        xmlvalue = req.responseText;
        console.log("handleStateChange : " +xmlvalue);
        document.getElementById("userCreditValidation").innerHTML = xmlvalue;
        getCredit();
    }
}

function getCredit(){
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
        console.log("handleStateChange : " +xmlvalue);
        document.getElementById("userCreditValue").innerHTML = xmlvalue;
    }
}

function getCart(){ // make it onload
    makeOBJ();
    url = "CartHandlerServlet2";
    req.open("GET", url, true);
    console.log("getCart!");
    req.send(null);
}

function goHome() {
    makeOBJ();
    url = "CartHandlerServlet3?goHome=true";
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