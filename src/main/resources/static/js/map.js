/**
 * Created by dell-pc on 2016/4/21.
 */
//为地图添加各个控件 city为定位到中心城市  num为放大级别一般为12

function addControlMap(map,city,num){
    map.centerAndZoom(city, num);
    var mapType1 = new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]});
    var mapType2 = new BMap.MapTypeControl({anchor: BMAP_ANCHOR_TOP_LEFT});

    var overView = new BMap.OverviewMapControl();
    var overViewOpen = new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT});

    map.addControl(mapType1);          //2D图，卫星图
    map.addControl(mapType2);          //左上角，默认地图控件
    map.setCurrentCity(city);        //由于有3D图，需要设置城市哦
    map.addControl(overView);          //添加默认缩略地图控件
    map.addControl(overViewOpen);      //右下角，打开
    map.enableScrollWheelZoom();
}

//封装ajax请求返回json
function ajax(url,data,type,onsuccess){
    $.ajax({
        url:url,
        dataType:'json',
        data:data,
        type:type,
        success:onsuccess,
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
};

//封装ajax请求没有返回json数据
function ajaxwithoutjson(url,data,type,onsuccess){
    $.ajax({
        url:url,
        data:data,
        type:type,
        async :false,
        success:onsuccess,
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
};

//显示消息提示框
function showmessageInfo(message,showtype){
    $.messager.show({
        title : '消息提示',
        msg : message,
        timeout : 2000,
        showType : showtype
    });
};

//添加道路弧线
function addCurveLineMap(map,points,strokeColor,strokeWeight,strokeOpacity){
    var curve = new BMapLib.CurveLine(points, {strokeColor:strokeColor, strokeWeight:strokeWeight, strokeOpacity:strokeOpacity}); //创建弧线对象
    map.addOverlay(curve); //添加到地图中
}

//添加让点类聚合
function markerClustererMap(map,markers){
    var markerClusterer = new BMapLib.MarkerClusterer(map, {markers:markers});
    return markerClusterer;
}

//创建折线在map地图上
function addPolylineMap(map,points,color){
    var polyline = new BMap.Polyline(points, {strokeColor:color, strokeWeight:7, strokeOpacity:0.6});   //创建折线
    map.addOverlay(polyline);   //增加折线
    return polyline;
}

//添加道路搜索功能
function addLocalserarch(map){
    var local = new BMap.LocalSearch(map, {
        renderOptions:{map: map}
    });
    return local;
}
/* 添加站点信息s到坐标上去
 * makrer 将标注信息存起来
 * points 将点得信息存起来
 * color 线路的颜色
 */
function addRoadStationsMap(map,data,markers,points,color){
    for(var j=0;j<data.length;j++){
        var point=new BMap.Point(data[j].roadstation.longitude,data[j].roadstation.latitude);
        var marker = new BMap.Marker(point);  // 创建标注
        markers.push(marker);
        points.push(point);
        var content =data[j].roadstation.name+' 备注:'+data[j].roadstation.demo+' 等待时间:'+data[j].roadstation.staytime+'分钟';
        map.addOverlay(marker);               // 将标注添加到地图中
        addClickHandler(map,content,marker);	//为标注添加监听事件
    }

    /*
     *  添加道路弧线
     */
    addCurveLineMap(map,points,color,3,0.5);
}

//添加道路线路到地图上
function getRoadLineListMap(map,data){
    map.clearOverlays();
    var markers=[];  		//用来收集所有的标记
    var color=["red","blue","black","#EEEE00","black"];

    for(var i=0;i<data.rows.length;i++){
        var road=data.rows[i];
        var point = new BMap.Point(road.linestations[0].roadstation.longitude,road.linestations[0].roadstation.latitude);
        //在地图上添加label标签
        var text=road.name+' 开车时间:'+road.startTime+' 结束时间'+road.endTime;
        addlabelonMap(map,point,text,"red");
        var points=[];

        addRoadStationsMap(map,road.linestations,markers,points,color[i]);
        addRoadlineStringMap(map,data.linestring[i],color[i]);   //添加道路线路string到地图上
    }

    //点类的聚合，百度地图接口
    //最简单的用法，生成一个marker数组，然后调用markerClusterer类即可。
    var markerClusterer = markerClustererMap(map,markers);
};

//添加道路线路通过id查询到地图上
function getRoadLineListByIdMap(map,id){
    //ajax访问后台
    var polyline;
    $.ajax({
        url:"roadline_findRoadLineById.action",
        dataType:'json',
        data:{id:id},
        type:"post",
        async : false,
        success:function(data){
            var road=data.rows;
            var point = new BMap.Point(road.linestations[0].roadstation.longitude,road.linestations[0].roadstation.latitude);
            addlabelonMap(map,point,road.name+' 开车时间:'+road.startTime+' 结束时间'+road.endTime,"red");
            var points=[];
            var markers=[];  		//用来收集所有的标记
            addRoadStationsMap(map,road.linestations,markers,points,"blue");
            polyline=addRoadlineStringMap(map,data.linestring,"red");   //添加道路线路string到地图上
            //点类的聚合，百度地图接口
            //最简单的用法，生成一个marker数组，然后调用markerClusterer类即可。
            var markerClusterer = markerClustererMap(map,markers);
        }
    });
    return polyline;
}

//添加道路线路string到地图上
function addRoadlineStringMap(map,roadstring,color){
    var points=[];
    for(var i=0;i<roadstring.length;i++){
        points.push(new BMap.Point(roadstring[i].longitude,roadstring[i].latitude));
    }
    return addPolylineMap(map,points,color);
}

//删除道路线路string到地图上
function deleteRoadlineStringById(id){
    ajaxwithoutjson("roadline_deleteRoadlineString.action",{id:id},"post",function(){
        alert("删除成功");
    });
}

/*为标注添加监听事件 可以点击弹出infowindow
 */
function addClickHandler(map,content,marker){

    var opts = {
        width : 300,     // 信息窗口宽度
        height: 100,     // 信息窗口高度
        title : "站台信息" , // 信息窗口标题
        enableMessage:true//设置允许信息窗发送短息
    };

    marker.addEventListener("click",function(e){
        var p = e.target;
        var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
        var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象
        map.openInfoWindow(infoWindow,point); //开启信息窗口
    });

}


//在地图上添加label标签
function addlabelonMap(map,point,text,color){
    var opts_text = {
        position : point,    // 指定文本标注所在的地理位置
        offset   : new BMap.Size(30, -30)    //设置文本偏移量
    };

    var label = new BMap.Label(text, opts_text);  // 创建文本标注对象
    label.setStyle({
        color : color,
        fontSize : "12px",
        height : "20px",
        lineHeight : "20px",
        fontFamily:"微软雅黑"
    });
    map.addOverlay(label);
}

//清除覆盖物
function remove_overlay(map){
    map.clearOverlays();
}

function deletePoint(map){
    var allOverlay = map.getOverlays();
    for (var i = 0; i < allOverlay.length -1; i++){
        map.removeOverlay(allOverlay[i]);
        return false;
    }
}
