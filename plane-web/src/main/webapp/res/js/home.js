var base = null;
var ws=null;
var log=null;
var plane=null;
var planeStatus = null;
var role = null;
var task = null;
window.onload=function(){ 
	base = document.getElementById("base");
	log=document.getElementById("log");
	plane =document.getElementById("plane");
	planeStatus =document.getElementById("fightStatus");
	role =document.getElementById("peoplerole");
	HomeChatOperateUtil.ready();
}

               
