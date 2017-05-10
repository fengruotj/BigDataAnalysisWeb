
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>StormBenchMark集中分析</title>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">

      <link href="/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
      <!-- 引入 ECharts 文件 -->
      <script src="/js/echarts.min.js"></script>
      <script src="/js/mycharts.js"></script>
      <script src="/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
      <script src="/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  </head>
  
  <body>

  <div class="container">
        <div class="jumbotron">
            <h2>StormBenchMark统计结果集中分析</h2>
            <div class="row center-block">
                <#--<div class="btn-group" role="group" aria-label="...">-->
                    <#--<button id="showresult" type="button" class="btn btn-primary">显示结果</button>-->
                    <#--<button id="savereslut" type="button" class="btn btn-primary" data-toggle="modal" data-target="#saveresultModal">保存当前结果</button>-->
                <#--</div>-->
            </div>
        </div>
  </div>

  <div class="container">
      <div class="form-group">
          <label class="col-sm-3 control-label">输入处理的文件名：</label>
          <div class="col-sm-4">
              <select id="spoutChartsSelect" name="usertype" class="form-control">
                  <option value="">====请选择====</option>
                  <#list spoutGsonFilenames as name>
                      <option value ="${name}">${name}</option>
                  </#list>
              </select>
          </div>
      </div>
  </div>
        <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
        <div id="spoutCharts" style="width: 100%;height:400px;background: #de3d3b"></div>

  <br/>
      <div class="container">
          <div class="form-group">
              <label class="col-sm-3 control-label">输入处理的文件名：</label>
              <div class="col-sm-4">
                  <select id="tupleChartsSelect" name="usertype" class="form-control">
                  <option value="">====请选择====</option>
                  <#list boltGsonFilenames as name>
                      <option value ="${name}">${name}</option>
                  </#list>
                  </select>
              </div>
          </div>
      </div>
        <div id="tupleCharts" style="width: 100%;height:400px;background: #b0c1d2"></div>

  <br/>
      <div class="container">
          <div class="form-group">
              <label class="col-sm-3 control-label">输入处理的文件名：</label>
              <div class="col-sm-4">
                  <select id="wordcountChartsSelect" name="usertype" class="form-control">
                      <option value="">====请选择====</option>
                  <#list wordcountGsonFilenames as name>
                      <option value ="${name}">${name}</option>
                  </#list>
                  </select>
              </div>
          </div>
      </div>
  <div id="wordcountCharts" style="width: 100%;height:400px;background: #00d95a"></div>

  <br/>
  <div class="container">
      <div class="form-group">
          <label class="col-sm-3 control-label">输入处理的文件名：</label>
          <div class="col-sm-4">
              <select id="hdfsbenchmarkChartsSelect" name="usertype" class="form-control">
                  <option value="">====请选择====</option>
              <#list hdfsbenchmarkGsonFilenames as name>
                  <option value ="${name}">${name}</option>
              </#list>
              </select>
          </div>
      </div>
  </div>
        <div id="hdfsbenchmarkCharts" style="width: 100%;height:400px;background: #00d95a"></div>

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
                 var spoutChart = echarts.init(document.getElementById('spoutCharts'));
                 echartsRedBrokenLineInit(spoutChart, {}, 'Storm输入源吞吐量测试结果');
                 var tupleChart = echarts.init(document.getElementById('tupleCharts'));
                 echartsBlueBrokenLineInit(tupleChart,{},'StormWordCount系统吞吐量');
                 var wordcountChart = echarts.init(document.getElementById('wordcountCharts'));
                 echartsBarGraphInit(wordcountChart, {}, 'WordCount单词统计结果显示');
                 var hdfsbenchmarkChart = echarts.init(document.getElementById('hdfsbenchmarkCharts'));
                 echartsBlueBrokenLineInit(hdfsbenchmarkChart,{},'HdfsBenchMark系统吞吐量(单位字节)');

                $("#spoutChartsSelect").change(function(){
                    var value=$(this).children('option:selected').val();//这就是selected的值
                    if(value!=""){
                        showSpoutEcharts(value);
                    }
                });

                 $("#tupleChartsSelect").change(function(){
                     var value=$(this).children('option:selected').val();//这就是selected的值
                     if(value!="") {
                         showTupleEcharts(value);
                     }
                 });

                 $("#wordcountChartsSelect").change(function(){
                     var value=$(this).children('option:selected').val();//这就是selected的值
                     if(value!="") {
                         showWordCountEcharts(value);
                     }
                 });

                 $("#hdfsbenchmarkChartsSelect").change(function(){
                     var value=$(this).children('option:selected').val();//这就是selected的值
                     if(value!="") {
                         showHdfsBenchMarkEcharts(value);
                     }
                 });
             });

         </script>
      <script type="text/javascript">

          //实时显示当前spout输入源的吞吐量
          function showSpoutEcharts(fileName){
              var myChart = echarts.init(document.getElementById('spoutCharts'));
              var url="/echarts/spoutTupleAnaylsis";
              $('#loaddata').modal('show')
              $.ajax({
                  url:url,
                  dataType:'json',
                  data:{'fileName':fileName},
                  type:"post",
                  success:function(data){
                      echartsRedBrokenLineInit(myChart, data, 'Storm输入源吞吐量测试结果');
                      $('#loaddata').modal('hide')
                  },
                  error:function(XMLHttpRequest, textStatus, errorThrown) {
                      alert(XMLHttpRequest.status);
                      alert(XMLHttpRequest.readyState);
                      alert(textStatus);
                  }
              });
          }

          function showTupleEcharts(fileName){
              var myChart = echarts.init(document.getElementById('tupleCharts'));
              var url="/echarts/boltTupleAnaylsis";
              $('#loaddata').modal('show')
              $.ajax({
                  url:url,
                  dataType:'json',
                  data:{'fileName':fileName},
                  type:"post",
                  success:function(data){
                      echartsBlueBrokenLineInit(myChart,data,'StormWordCount系统吞吐量');
                      $('#loaddata').modal('hide')
                  },
                  error:function(XMLHttpRequest, textStatus, errorThrown) {
                      alert(XMLHttpRequest.status);
                      alert(XMLHttpRequest.readyState);
                      alert(textStatus);
                  }
              });
          }

          function showWordCountEcharts(fileName){
              var myChart = echarts.init(document.getElementById('wordcountCharts'));
              var url="/echarts/wordcountAnaylsis";
              $('#loaddata').modal('show')
              $.ajax({
                  url:url,
                  dataType:'json',
                  data:{'fileName':fileName},
                  type:"post",
                  success:function(data){
                      echartsBarGraphInit(myChart, data, 'WordCount单词统计结果显示');
                      $('#loaddata').modal('hide')
                  },
                  error:function(XMLHttpRequest, textStatus, errorThrown) {
                      alert(XMLHttpRequest.status);
                      alert(XMLHttpRequest.readyState);
                      alert(textStatus);
                  }
              });
          }

          function showHdfsBenchMarkEcharts(fileName){
              var myChart = echarts.init(document.getElementById('hdfsbenchmarkCharts'));
              var url="/echarts/hdfsByteAnaylsis";
              $('#loaddata').modal('show')
              $.ajax({
                  url:url,
                  dataType:'json',
                  data:{'fileName':fileName},
                  type:"post",
                  success:function(data){
                      echartsBlueBrokenLineInit(myChart,data,'HdfsBenchMark系统吞吐量(单位字节)');
                      $('#loaddata').modal('hide')
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
