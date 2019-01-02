/**
 * 搜索功能API
 */
var searchColors = ['#1ef22a', '#260cff', '#eb0aff'];
var searchRouteStatusCode = ['混合光缆', '一干光缆', '二干光缆'];
var searchResultMarker = new Array();

var searchWindow = new AMap.InfoWindow({
    isCustom: true,
    offset: new AMap.Pixel(20, -35)
});
//构建自定义信息窗体
function createSearchWindow(title, content) {
    var info = document.createElement("div");
    info.className = "info";

    //可以通过下面的方式修改自定义窗体的宽高
    //info.style.width = "400px";
    // 定义顶部标题
    var top = document.createElement("div");
    var titleD = document.createElement("div");
    var closeX = document.createElement("img");
    top.className = "info-top";
    titleD.innerHTML = title;
    closeX.src = "res/i/close2.gif";
    closeX.onclick = closeInfoWindow;

    top.appendChild(titleD);
    top.appendChild(closeX);
    info.appendChild(top);

    // 定义中部内容
    var middle = document.createElement("div");
    middle.className = "info-middle";
    middle.style.backgroundColor = 'white';
    middle.innerHTML = content;
    info.appendChild(middle);
    return info;
}

//打开信息窗体
function searchMarkerClick(e) {
	searchWindow.setContent(e.target.content);
    searchWindow.open(map, e.target.getPosition());
}

//关闭信息窗体
function closeInfoWindow() {
    map.clearInfoWindow();
}

$("ul#type-selections").on("click", "li", function () {

    switch ($(this).text()) {
        case '光缆': {
            $('#search-option').html('<i class="icon-link"></i> 光缆&nbsp;&nbsp;<i class="icon-angle-down"></i>');
            $("#search-type").val(1);
            $("#search-target").val('');   //清空输入的内容
            $('#search-div-1').css('display', 'none');
            break;
        }
        case '飞行路径': {
            $('#search-option').html('<i class="icon-link"></i> 飞行路径&nbsp;&nbsp;<i class="icon-angle-down"></i>');
            $("#search-type").val(2);
            $("#search-target").val('');   //清空输入的内容
            $('#search-div-1').css('display', 'none');
            break;
        }
        case '信息点': {
        	$('#search-option').html('<i class="icon-map-marker"></i> 信息点&nbsp;&nbsp;<i class="icon-angle-down"></i>');
            $("#search-type").val(3);
            $("#search-target").val('');   //清空输入的内容
            $('#search-div-1').css('display', 'none');       
            break;
        }
        default:
            break;
    }
});


$("#search-target").bind('keyup', function () {
    // type = 1 查询光缆
    //  type = 2 查询飞行路径
    //  type =3 查询信息点
    var reg = /^[\s]*$/;     //判断是否全是空格，减少查询次数
    if (reg.test($("#search-target").val())) {
        return;
    }
    
    $.ajax({
        type: "POST",
        url: "indexSearch.action",
        dataType: 'json',
        data: {
            queryType: $("#search-type").val(),
            queryString: $("#search-target").val()
        },
        success: function (result) {
            //处理返回的数据
            // var text = json_encode(response);
            if (result.errcode == 0 && result.message == "SUCCESS") {
                var userStr = JSON.stringify(result.data);
                var arr = userStr.substring(1, userStr.length - 1).split(',');
                for (var j = 0; j < arr.length; j++) {
                    arr[j] = arr[j].substring(1, arr[j].length - 1);
                }
                var html = "";
                for (var i = 0; i < arr.length; i++) {
                    html += "<li class='taskoff'>" + arr[i] + "</li>"
                }
                $('#search-result').html(html);
                $('#search-div-1').css('display', 'block');
            }
        },
        error: function () {
            //Modal.tipFailure('导入失败');
        }
    });
});

$('#search-result').on('click', 'li', function () {
    $("#search-target").val($(this).text());
    $('#search-div-1').css('display', 'none');
    //把选择的数据放入到地图中
    //map.remove(searchResultMarker);
    while (searchResultMarker.length > 0) {   //把标记删除
        var markeritem = searchResultMarker.pop();
        map.remove(markeritem);
    }
    
    switch ($("#search-type").val()) {
        case '1': {
        	
            $.ajax({
                type: "POST",
                url: "getRouteByName.action",
                dataType: 'json',
                data: {
                   name:$(this).text()
                },
                success: function (result) {
                
                    if (result.errcode == 0 && result.message == "SUCCESS") {
                        //var center = JSON.parse(JSON.stringify(result.data.routepathdata[0]))
                        map.setCenter(result.data.routepathdata[0]);
                        
                        var polyline = new AMap.Polyline({
                            map: map,
                            path: result.data.routepathdata, //设置线覆盖物路径
                            strokeColor: searchColors[parseInt(data.type)], //线颜色
                            strokeOpacity: 1, //线透明度
                            strokeWeight: 5, //线宽
                            strokeStyle: "solid", //线样式
                            strokeDasharray: [10, 5] //补充线样式
                        });
                        searchResultMarker.push(polyline);

                    }
                },
                error: function () {
                    //Modal.tipFailure('导入失败');
                }
            });
            break;
        }
        case '2': {
            $.ajax({
                type: "POST",
                url: "/getFlyingPathByName.action",
                dataType: 'json',
                data: {name: $(this).text()},
                success: function (result) {
                    if (result.errcode == 0 && result.message == "SUCCESS") {
                        map.setCenter(result.data.pathdata[0]);
                        var polyline = new AMap.Polyline({
                            map: map,
                            path: result.data.pathdata, //设置线覆盖物路径
                            strokeColor: '#ff6700', //线颜色
                            strokeOpacity: 1, //线透明度
                            strokeWeight: 5, //线宽
                            strokeStyle: "solid", //线样式
                            strokeDasharray: [10, 5] //补充线样式
                        });
                        searchResultMarker.push(polyline);
                    }
                },
                error: function () {
                    //Modal.tipFailure('导入失败');
                }
            });
            break;
        }
        case '3': {
        	
        	$.ajax({
                type: "POST",
                url: "/getInfoPointByName.action",
                dataType: 'json',
                data: {name: $(this).text()},
                success: function (result) {
                    if (result.errcode == 0 && result.message == "SUCCESS") {
                        //显示数据
                    	var infoPointdata = result.data;
                    	AMapUI.loadUI(['overlay/SimpleMarker'], function (SimpleMarker) {
                    		
                    		var i=0;
                    		console.log(infoPointdata);
                        	for(i=0;i<infoPointdata.length;i++){
                        		
                        		var infoPointMarker = new SimpleMarker({
                                    //前景文字
                                    map: map,
                                    iconLabel: {
                                        innerHTML: '<i>桩</i>', //设置文字内容
                                        style: {
                                            color: 'white' //设置文字颜色
                                        }
                                    },
                                    iconTheme: 'fresh',
                                    iconStyle: 'orange',
                                    position: infoPointdata[i].lnglat,
                                    animation: 'AMAP_ANIMATION_DROP',
                                });
                        		if(i==0){
                        			map.setCenter(infoPointdata[i].lnglat);
                        		}
                        	
                        		var title = '信息点编号：<span>' + infoPointdata[i].id + '</span>';
                                var content = [];
                                content.push("信息点名称：" + infoPointdata[i].name);
                                content.push("海拔高度：" +infoPointdata[i].altitude);
                                content.push("所属路由Id：" + infoPointdata[i].routeId);
                                content.push("所属路由：" + infoPointdata[i].routeName);                                                
                                infoPointMarker.content = createSearchWindow(title, content.join("<br/>"));
                                infoPointMarker.on('click', searchMarkerClick);
                        		
                        		searchResultMarker.push(infoPointMarker);
                    	    }
                        	                 
                    	});
                    	           	
                    }
                },
                error: function () {
                    //Modal.tipFailure('导入失败');
                }
            });
            break;
        }
        default:
            break;
    }

});

$("#search-target").bind("input propertychange", function (event) {
    var inputVal = $("#search-target").val();
    var str = inputVal.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
    if (str == '' || str == undefined || str == null) {
        $('#search-div1').css('display', 'none');
    }
});
