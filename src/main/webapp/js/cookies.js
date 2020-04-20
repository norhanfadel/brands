

function setCookie(cname,cvalue,exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  var expires = "expires=" + d.toGMTString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";

}

function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i < ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function checkCookie() {
  var user=getCookie("username");
  var password=getCookie("password");

  if (user != ""&&password!="") {
  
 	     document.getElementById("email").value =user ;

    document.getElementById("password").value =password ;
  }
}
function checkCookie1() {

	 if (document.getElementById('remember').checked) {
     user = document.getElementById("email").value;
       password = document.getElementById("password").value;

     if ( user != "" && user != null&&password!=""&&password!=null) {
       setCookie("username", user, 30);
       setCookie("password",password,30);
     }}
	 else{
		//  setCookie("username", "", 30);
		deleteCookie("username","",30);
       deleteCookie("password","",30);
	 }
	
	
  }

function deleteCookie(cname,cvalue,exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays*-24*60*60*1000));
  var expires = "expires=" + d.toGMTString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
 
}