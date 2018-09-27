var WebTypeUtil=
{
		WEBUSERLOGIN:"web@login",
		BROWSEUSERLOGIN:"browse@login",
		MESSAGETYPESTATUS:"status"
}

var WebSocketUtil = {
	webSocket : null,
	timeOuter : null,
	isActive : true,
	connect : function() {
		
		WebSocketUtil.webSocket = new WebSocket("ws:///172.16.29.58:17020");
		WebSocketUtil.webSocket.onopen = WebSocketUtil.onOpen;
		WebSocketUtil.webSocket.onmessage = WebSocketUtil.onMessage;
		WebSocketUtil.webSocket.onclose = WebSocketUtil.onClose;
		WebSocketUtil.webSocket.onerror = WebSocketUtil.onError;
	},
	initTimeOuter : function() {
		
	},
	onOpen : function(event) {
		if(role.value == "1")
		{
			var content = plane.value+WebTypeUtil.BROWSEUSERLOGIN;
			
		}else if(role.value =="2")
		{
			var content = plane.value+WebTypeUtil.WEBUSERLOGIN;
		}
		
		WebSocketUtil.webSocket.send(content);
	},
	onMessage : function(event) {
		//alert("收到消息"+event.data);
		var message = event.data;
		var messageType = message.split(":");
		switch(messageType[0]){
		case WebTypeUtil.MESSAGETYPESTATUS:
			//处理接收到的经纬度消息
			PlaneHandleServiceUtil.handleStatus(messageType[1],messageType[2]);
			break;
		}

	},
	onClose : function(event) {
		//alert("收到消息"+event.data);
	},
	onError : function(event) {
		alert("出错了");
	},
	sendMessage : function(content)
	{
		WebSocketUtil.webSocket.send(content);
		WebSocketUtil.print("[send] '" + content + "'\n");
		 
	},
	disConnection : function() {
		
	},
	print: function (text) {
         log.innerHTML = (new Date).getTime() + ": " + text + log.innerHTML;
    }
}

var PlaneHandleServiceUtil ={
		handleStatus:function(message,status)
		{
			//显示无人机的状态信息
			planeStatus.innerHTML = status;
			var mes = message.split(",");
			var data = new Array();
	        var value = mes[0] *1
	        var value2=mes[1]*1;
	        data[0] = value;
	        data[1] = value2;
			map.remove(planeMarker);
						
    	   planeMarker = new AMap.Marker({
                //map: map,
                position:  data,
                icon: new AMap.Icon({
                size: new AMap.Size(32, 32), //图标大小
                image: "i/fly-32.png",
                offset: new AMap.Pixel(-16, -16) ,// 相对于基点的偏移位置
                }),
            });
		    map.setCenter(data); 
		    map.add(planeMarker);
			//WebSocketUtil.print("[send] '" + message + "'\n");
		}
	
}
var HomeChatOperateUtil = {
	ready : function(){
		WebSocketUtil.connect();
	}
}