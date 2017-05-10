/**
 * Created by 79875 on 2017/3/10.
 */


/**
 * echarts 模块化编程思想
 * @type {Object}
 */
var echarts = new Object({
    m1: function () {
        //function 1
    },
    m2: function () {
        //function 2
    }
});

/**
 * Echarts标准饼状图绘制
 * @param myChart
 * @param echartsdata
 * @param titleText
 */
function echartsPieBarInit(myChart,echartsdata,titleText) {
    option = {
        title : {
            text: titleText,
            subtext: 'Echarts',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:echartsdata.legend
            //data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel','bar'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'left',
                            max: 1548
                        }
                    }
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'数据',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:echartsdata.piedata
                // data:[
                //     {value:335, name:'直接访问'},
                //     {value:310, name:'邮件营销'},
                //     {value:234, name:'联盟广告'},
                //     {value:135, name:'视频广告'},
                //     {value:1548, name:'搜索引擎'}
                // ]
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

/**
 * 绘制大型数据折线图 蓝色 StormWordCount系统吞吐量
 * @param myChart
 * @param echartsdata
 */
function echartsBlueBrokenLineInit(myChart,echartsdata,titleText){
    console.info(echartsdata)
    option = {
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        title: {
            left: 'center',
            text: titleText,
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {},
                magicType: {show: true, type: ['line', 'bar']},
                dataView : {show: true, readOnly: false}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: echartsdata.timeinfo
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%']
        },
        dataZoom: [ {
            type: 'slider',
            show: true,
            xAxisIndex: [0],
            start: 1,
            end: 35,
            handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
            handleSize: '80%',
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        },
            {
                type: 'slider',
                show: true,
                yAxisIndex: [0],
                left: '93%',
                start: 0,
                end: 100,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            },
            {
                type: 'inside',
                xAxisIndex: [0],
                start: 1,
                end: 35
            },
            {
                type: 'inside',
                yAxisIndex: [0],
                start: 0,
                end: 100
            }],
        series: [
            {
                name:'模拟数据',
                type:'line',
                smooth:true,
                symbol: 'none',
                sampling: 'average',
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                },
                itemStyle: {
                    normal: {
                        color: 'rgb(131, 70, 255)'
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(68, 158, 255)'
                        }, {
                            offset: 1,
                            color: 'rgb(131, 70, 255)'
                        }])
                    }
                },
                data: echartsdata.tupleCount
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}


/**
 * 绘制大型数据折线图 红色
 * @param myChart
 * @param echartsdata
 */
function echartsRedBrokenLineInit(myChart,echartsdata){
    console.info(echartsdata)
    option = {
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        title: {
            left: 'center',
            text: 'Storm输入源吞吐量测试结果',
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {},
                magicType: {show: true, type: ['line', 'bar']},
                dataView : {show: true, readOnly: false}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: echartsdata.timeinfo
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%']
        },
        dataZoom: [ {
            type: 'slider',
            show: true,
            xAxisIndex: [0],
            start: 1,
            end: 35,
            handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
            handleSize: '80%',
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        },
            {
                type: 'slider',
                show: true,
                yAxisIndex: [0],
                left: '93%',
                start: 0,
                end: 100,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            },
            {
                type: 'inside',
                xAxisIndex: [0],
                start: 1,
                end: 35
            },
            {
                type: 'inside',
                yAxisIndex: [0],
                start: 0,
                end: 100
            }],
        series: [
            {
                name:'模拟数据',
                type:'line',
                smooth:true,
                symbol: 'none',
                sampling: 'average',
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                },
                itemStyle: {
                    normal: {
                        color: 'rgb(255, 70, 131)'
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(255, 158, 68)'
                        }, {
                            offset: 1,
                            color: 'rgb(255, 70, 131)'
                        }])
                    }
                },
                data: echartsdata.tupleCount
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}


/**
 * 绘制柱状图
 * @param myChart
 * @param echartsdata
 */
function echartsBarGraphInit(myChart,echartsdata,titleText){
    console.info(echartsdata)
    option = {
        tooltip : {
            trigger: 'item'
        },
        title: {
            left: 'center',
            //text: 'WordCount单词统计结果显示',
            text:titleText
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        grid: {
            top: '12%',
            left: '1%',
            right: '10%',
            containLabel: true
        },
        xAxis: [
            {
                type : 'category',
                data : echartsdata.timewordinfo
            }
        ],
        yAxis: [
            {
                type : 'value',
                boundaryGap: [0, '100%']
            }
        ],
        dataZoom: [
            {
                type: 'slider',
                show: true,
                xAxisIndex: [0],
                start: 1,
                end: 35,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            },
            {
                type: 'inside',
                xAxisIndex: [0],
                start: 1,
                end: 35
            }
        ],
        series : [
            {
                name: '模拟数据',
                type: 'bar',
                data: echartsdata.count
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function echartsDynamicLineInit(url,mychart,text,subtext){
    var data=[];
    var chart=mychart;
    var m_bool=true;
    option = {
        title: {
            text: text,
            subtext: subtext
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#283b56'
                }
            }
        },
        legend: {
            data:[]
        },
        toolbox: {
            show: true,
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: true,
                data: (function (){
                    var now = new Date();
                    var res = [];
                    //var len = 10;
                    //while (len--) {
                    //    res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
                    //    now = new Date(now - 2000);
                    //}
                    return res;
                })()
            }
        ],
        yAxis: [
            {
                type: 'value',
                scale: true,
                name: '数值',
                max: 100,
                min: 0,
                boundaryGap: [0.2, 0.2]
            }
        ],
        series: [
            //{
            //    name:'root1',
            //    type:'line',
            //    data:(function (){
            //        var res = [];
            //        //var len = 10;
            //        //while (len--) {
            //        //    res.push((Math.random()*10 + 5).toFixed(1) - 0);
            //        //}
            //        return res;
            //    })()
            //},
            //{
            //    name:'root2',
            //    type:'line',
            //    data:(function (){
            //        var res = [];
            //        //var len = 10;
            //        //while (len--) {
            //        //    res.push((Math.random()*10 + 5).toFixed(1) - 0);
            //        //}
            //        return res;
            //    })()
            //}
        ]
    };
    setInterval(function (){
        if(m_bool){
            $.ajax({
                url:url,
                dataType:'json',
                data:{},
                type:"post",
                success:function(data){
                    m_bool=true;
                    var hosts=data.hosts;
                    var values=data.value;
                    for(var i=0;i< hosts.length; i++){
                        var serie={};
                        serie.name=hosts[i];
                        serie.type='line';
                        serie.data=(function (){
                            var res = [];
                            //var len = 10;
                            //while (len--) {
                            //    res.push((Math.random()*10 + 5).toFixed(1) - 0);
                            //}
                            return res;
                        })();
                        //serie.areaStyle={
                        //    normal: {
                        //        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        //            offset: 0,
                        //            color: 'rgb(255, 158, 68)'
                        //        }, {
                        //            offset: 1,
                        //            color: 'rgb(255, 70, 131)'
                        //        }])
                        //    }
                        //};
                        serie.smooth= true;
                        serie.symbol='circle';
                        option.series.push(serie);
                        option.legend.data.push(hosts[i]);

                        var data0 = option.series[i].data;
                        //data0.shift();
                        data0.push(values[i]);
                    }
                    axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
                    //option.xAxis[0].data.shift();
                    option.xAxis[0].data.push(axisData);
                    chart.setOption(option);
                },
                error:function(XMLHttpRequest, textStatus, errorThrown) {
                    m_bool=true;
                    //alert(XMLHttpRequest.status);
                    //alert(XMLHttpRequest.readyState);
                    //alert(textStatus);
                }
            });
            m_bool=false;
        }
    }, 1000);
}

function echartsDynamicLineNetworkInit(url,mychart,text,subtext){
    var data=[];
    var chart=mychart;
    var m_bool=true;
    option = {
        title: {
            text: text,
            subtext: subtext
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#283b56'
                }
            }
        },
        legend: {
            data:[]
        },
        toolbox: {
            show: true,
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: true,
                data: (function (){
                    var now = new Date();
                    var res = [];
                    //var len = 10;
                    //while (len--) {
                    //    res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
                    //    now = new Date(now - 2000);
                    //}
                    return res;
                })()
            }
        ],
        yAxis: [
            {
                type: 'value',
                scale: true,
                name: '数值 单位(Kbps)',
                min: 0,
                boundaryGap: [0.2, 0.2]
            }
        ],
        series: [
            //{
            //    name:'root1',
            //    type:'line',
            //    data:(function (){
            //        var res = [];
            //        //var len = 10;
            //        //while (len--) {
            //        //    res.push((Math.random()*10 + 5).toFixed(1) - 0);
            //        //}
            //        return res;
            //    })()
            //},
            //{
            //    name:'root2',
            //    type:'line',
            //    data:(function (){
            //        var res = [];
            //        //var len = 10;
            //        //while (len--) {
            //        //    res.push((Math.random()*10 + 5).toFixed(1) - 0);
            //        //}
            //        return res;
            //    })()
            //}
        ]
    };
    setInterval(function (){
        if(m_bool){
            $.ajax({
                url:url,
                dataType:'json',
                data:{},
                type:"post",
                success:function(data){
                    m_bool=true;
                    var hosts=data.hosts;
                    var values=data.value;
                    for(var i=0;i< hosts.length; i++){
                        var serie={};
                        serie.name=hosts[i];
                        serie.type='line';
                        serie.data=(function (){
                            var res = [];
                            //var len = 10;
                            //while (len--) {
                            //    res.push((Math.random()*10 + 5).toFixed(1) - 0);
                            //}
                            return res;
                        })();
                        //serie.areaStyle={
                        //    normal: {
                        //        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        //            offset: 0,
                        //            color: 'rgb(255, 158, 68)'
                        //        }, {
                        //            offset: 1,
                        //            color: 'rgb(255, 70, 131)'
                        //        }])
                        //    }
                        //};
                        serie.smooth= true;
                        serie.symbol='circle';
                        option.series.push(serie);
                        option.legend.data.push(hosts[i]);

                        var data0 = option.series[i].data;
                        //data0.shift();
                        data0.push(values[i]);
                    }
                    axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
                    //option.xAxis[0].data.shift();
                    option.xAxis[0].data.push(axisData);
                    chart.setOption(option);
                },
                error:function(XMLHttpRequest, textStatus, errorThrown) {
                    m_bool=true;
                    //alert(XMLHttpRequest.status);
                    //alert(XMLHttpRequest.readyState);
                    //alert(textStatus);
                }
            });
        }
    }, 1000);
}
