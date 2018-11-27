var base = null;
var ws=null;
var log=null;
var plane=null;
var planeStatus = null;
var role = null;
var task = null;
var fightArSpd = null;
var fightGrSpd = null;
var fightLat = null;
var fightLon = null;
var fightElv = null;
var fightHDG = null;
var fightHAgl = null;
var fightGrVAgl = null;
window.onload=function(){ 
	base = document.getElementById("base");
	log=document.getElementById("log");
	plane =document.getElementById("plane");
	planeStatus =document.getElementById("fightStatus");
	role =document.getElementById("peoplerole");
	base = document.getElementById("base");
	
	fightArSpd=document.getElementById("fightArSpd");
	fightGrSpd =document.getElementById("fightGrSpd");
	fightLat =document.getElementById("fightLat");
	fightLon =document.getElementById("fightLon");
	fightElv = document.getElementById("fightElv");
	fightHDG=document.getElementById("fightHDG");
	fightHAgl =document.getElementById("fightHAgl");
	fightGrVAgl =document.getElementById("fightGrVAgl");
	
	HomeChatOperateUtil.ready();
}

               
