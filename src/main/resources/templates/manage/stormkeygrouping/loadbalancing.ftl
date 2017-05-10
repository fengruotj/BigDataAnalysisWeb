
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>KeyGroupingBalancing 负载均衡实例负载情况UI</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- ECharts单文件引入 -->
    <#--<script src="http://echarts.baidu.com/build/dist/echarts-all.js"></script>-->
    <script src="/js/echarts.min.js"></script>
    <script src="/js/mycharts.js"></script>
    <script src="/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script src="/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
</head>

<body>

<div class="container">
    <div class="jumbotron">
        <h2>KeyGroupingBalancing 负载均衡实例负载情况UI</h2>
        <div class="row center-block">
            <div class="btn-group" role="group" aria-label="...">
                <button id="showPieResult" type="button" class="btn btn-primary">显示饼状图结果</button>
                <button id="showLineResult" type="button" class="btn btn-primary">显示柱状图结果</button>
                <button id="cleanCache" type="button" class="btn btn-primary">清除缓存</button>
                <button id="savereslut" type="button" class="btn btn-primary" data-toggle="modal" data-target="#saveresultModal">保存当前结果</button>
            </div>
        </div>
    </div>
</div>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 100%;height:400px;background: #b0c1d2"></div>

<!-- 保存当前结果模态框（Modal） -->
<div class="modal fade" id="saveresultModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    保存当前统计结果
                </h4>
            </div>
            <div class="modal-body">
                <div class="input-group">
                    <input id="fileNameText" type="text" class="form-control" placeholder="输入文件名" aria-describedby="basic-addon2">
                    <span class="input-group-addon" id="basic-addon2">.json</span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button id="saveresultbtn" type="button" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="loaddata" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3>正在加载中........</h3>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script type="text/javascript">

    // init()方法
    $(function () {
        var myChart = echarts.init(document.getElementById('main'));
        echartsPieBarInit(myChart,{},'每个bolt实例负载均衡情况');
        $("#showPieResult").click(function(){
            $('#loaddata').modal('show')
            showPieEcharts();
        });
        $("#showLineResult").click(function(){
            $('#loaddata').modal('show')
            showLineEcharts();
        });
        $("#cleanCache").click(function(){
            $('#loaddata').modal('show')
            cleanCache();
        });
        $("#saveresultbtn").click(function(){
            var fileName=$("#fileNameText").val();
            console.info(fileName);
            $('#loaddata').modal('show')
            saveEchartsResult(fileName);
        });
    });

</script>
<script type="text/javascript">

    //实时显示当前Bolt实例负载均衡情况 饼状图显示
    function showPieEcharts(){
        var myChart = echarts.init(document.getElementById('main'));
        var url="/echarts/Pie/keyGroupingLoadBalancing"
        $.ajax({
            url:url,
            dataType:'json',
            data:{},
            type:"post",
            success:function(data){
                echartsPieBarInit(myChart,data,'每个bolt实例负载均衡情况');
                $('#loaddata').modal('hide')
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    }

    /**
     * 清除后台Redis缓存
     */
    function cleanCache() {
        var url='/keygrouping/cleanRedisCacheLoadBalancing';
        $.ajax({
            url:url,
            dataType:'json',
            data:{},
            type:"post",
            success:function(data){
                $('#loaddata').modal('hide')
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    }

    /**
     * 实时显示当前Bolt实例负载均衡情况 柱状图显示
     */
    function showLineEcharts() {
        var myChart = echarts.init(document.getElementById('main'));
        var url="/echarts/Line/keyGroupingLoadBalancing"
        $.ajax({
            url:url,
            dataType:'json',
            data:{},
            type:"post",
            success:function(data){
                echartsBarGraphInit(myChart,data,'每个bolt实例负载均衡情况');
                $('#loaddata').modal('hide')
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    }

    //保存当前输出结果以json格式保存，方便后面查看
    function saveEchartsResult(fileName){
        var url="/spout/saveresultspoutcount"
        $.ajax({
            url:url,
            dataType:'json',
            data:{'fileName':fileName},
            type:"post",
            success:function(data){
                //保存成功
                $('#saveresultModal').modal('hide')
                $('#loaddata').modal('hide')
                alert("结果已经保存");
            },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    }
</script>
</body>
</html>

