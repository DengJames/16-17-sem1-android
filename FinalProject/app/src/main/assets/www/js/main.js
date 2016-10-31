// var url = data.url + "&output=embed";
// window.location.replace(url);

// document.getElementById("runJS").addEventListener("click", myJSFunction, true);
// function myJSFunction() { 
// 	var myUserName = document.getElementById('userName').value;
// 	window.open("hello.html");
// 	// document.getElementById("outputText").innerHTML = "Hello " + myUserName + ", Thanks for using our app!";
// }

function gotoActivity (activity) {
    nativeMethod.toActivity(activity);
  }

  function sendInfoToJava(){
    var nameVal = document.getElementById("userName").value;
    window.AndroidWebView.showInfoFromJs(nameVal);
  }  

  // function showInfoFromJava(msg){  
  //   alert("From client:"+msg);
  // }  