
function showHomeProduct() {
    $("#home").css("display","block");
    $("#men").css("display","none");
    $("#women").css("display","none");
    $("#kids").css("display","none");

}
function showMenProduct() {
    debugger
    $("#home").css("display","none");
    $("#men").css("display","block");
    $("#women").css("display","none");
    $("#kids").css("display","none");
    $("#searchResult").css("display","none");
    $("#menLink").css("background-color","#fdb45e");
    $("#womenLink").css("background-color","white");
    $("#kidsLink").css("background-color","white");

}
function showWomenProduct() {
    $("#home").css("display","none");
    $("#men").css("display","none");
    $("#women").css("display","block");
    $("#kids").css("display","none");
    $("#searchResult").css("display","none");
    $("#menLink").css("background-color","white");
    $("#womenLink").css("background-color","#fdb45e");
    $("#kidsLink").css("background-color","white");

}
function showKidsProduct() {
    $("#home").css("display","none");
    $("#men").css("display","none");
    $("#women").css("display","none");
    $("#kids").css("display","block");
    $("#searchResult").css("display","none");
    $("#menLink").css("background-color","white");
    $("#womenLink").css("background-color","white");
    $("#kidsLink").css("background-color","#fdb45e");

}
function showSearchProduct() {
    debugger
    $("#home").css("display","none");
    $("#men").css("display","none");
    $("#women").css("display","none");
    $("#kids").css("display","none");
    $("#searchResult").css("display","block");
    $("#menLink").css("background-color","white");
    $("#womenLink").css("background-color","white");
    $("#kidsLink").css("background-color","white");

}