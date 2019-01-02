/**
 * 搜索功能API
 */
var searchColors = ['#1ef22a', '#260cff', '#eb0aff'];
var searchRouteStatusCode = ['混合光缆', '一干光缆', '二干光缆'];
var searchResultMarker = new Array();

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
