var angle = 0;//定义全局变量飞机角度，待验证
var wgs84_to_gcj02 = new WGS84_to_GCJ02();
var WebTypeUtil =
    {
        WEBUSERLOGIN: "web@login",
        BROWSEUSERLOGIN: "browse@login",
        MESSAGETYPESTATUS: "status"
    }

var WebSocketUtil = {
    webSocket: null,
    timeOuter: null,
    isActive: true,
    connect: function () {
        //部署的时候该ip改成固定ip
        //WebSocketUtil.webSocket = new WebSocket("ws:///218.65.240.246:7020");
        WebSocketUtil.webSocket = new WebSocket("ws:///127.0.0.1:17020");
        WebSocketUtil.webSocket.onopen = WebSocketUtil.onOpen;
        WebSocketUtil.webSocket.onmessage = WebSocketUtil.onMessage;
        WebSocketUtil.webSocket.onclose = WebSocketUtil.onClose;
        WebSocketUtil.webSocket.onerror = WebSocketUtil.onError;
    },
    initTimeOuter: function () {

    },
    onOpen: function (event) {
        if (role.value == "1") {
            var content = plane.value + WebTypeUtil.BROWSEUSERLOGIN;

        } else if (role.value == "2") {
            var content = plane.value + WebTypeUtil.WEBUSERLOGIN;
        }

        WebSocketUtil.webSocket.send(content);
    },
    onMessage: function (event) {
        var message = event.data;
        var messageType = message.split(":");
        switch (messageType[0]) {
            case WebTypeUtil.MESSAGETYPESTATUS:
                //处理接收到的经纬度消息
                PlaneHandleServiceUtil.handleStatus(messageType[1], messageType[2], messageType[3], messageType[4], messageType[5],
                    messageType[6], messageType[7], messageType[8], messageType[9], messageType[10], messageType[11]);
                break;
        }

    },
    onClose: function (event) {
        //alert("收到消息"+event.data);
    },
    onError: function (event) {
        alert("未连接到服务器！");
    },
    sendMessage: function (content) {
        WebSocketUtil.webSocket.send(content);
        WebSocketUtil.print("[send] '" + content + "'\n");

    },
    disConnection: function () {

    },
    print: function (text) {
        log.innerHTML = (new Date).getTime() + ": " + text + log.innerHTML;
    }
}

var PlaneHandleServiceUtil = {
    handleStatus: function (message, status, GPS_HDG, uavDeciveId, AR_SPD, GR_SPD, lon, lat, GPS_ELV, HORI_AGL, VERT_AGL) {
        //显示无人机的状态信息
        planeStatus.innerHTML = status;
        fightArSpd.innerHTML = AR_SPD + "";
        fightGrSpd.innerHTML = GR_SPD + "";
        fightLat.innerHTML = lat + "";
        fightLon.innerHTML = lon + "";
        fightElv.innerHTML = GPS_ELV + "";
        fightHDG.innerHTML = GPS_HDG + "";
        fightHAgl.innerHTML = HORI_AGL + "";
        fightGrVAgl.innerHTML = VERT_AGL + "";
        var mes = message.split(",");
        var data = new Array();
        var value = mes[0] * 1;
        var value2 = mes[1] * 1;
        data[0] = value;
        data[1] = value2;
    
        
        var oldPosition = planeMarker.getPosition();  //旧点
	    var realdata = wgs84_to_gcj02.transform(data[0],data[1]);
	    var linedata = [oldPosition, realdata];
	    var polyline = new AMap.Polyline({
            map: map,
            path: linedata, //设置线覆盖物路径
            strokeColor: '#436EEE', //线颜色
            strokeOpacity: 1, //线透明度
            strokeWeight: 6, //线宽
            strokeStyle: "solid", //线样式
            strokeDasharray: [10, 5] //补充线样式
        });
	    map.setCenter(realdata); 		   
	    planeMarker.setPosition(realdata);
	    planeMarker.setAngle(GPS_HDG); 
    }


}
var HomeChatOperateUtil = {
    ready: function () {
        WebSocketUtil.connect();
    }
}
//新加入，待验证
/*
function getPlaneAngle(preLongitude,preLatitude,currentLongitude,currentLatitude){
    $.ajax({
        type: "post",
        url: "${s.base}/getPlaneAngle.action",
        data:{
            "preLongitude":preLongitude,
            "preLatitude":preLatitude,
            "currentLongitude":currentLongitude,
			"currentLatitude":currentLatitude
		},
        success: function (result) {
            if (result.errcode == 0 && result.message == "SUCCESS") {
                angle = result.data;
            } else {
                alert(result.message);
            }
        }
    });
}*/
