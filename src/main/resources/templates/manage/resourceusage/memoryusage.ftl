
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <title>集群Memory监控界面</title>
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
          <h2>集群Memory利用率监控UI显示</h2>
          <div class="row center-block">
              <div class="btn-group" role="group" aria-label="...">
                  <button id="showresult" type="button" class="btn btn-primary">显示结果</button>
                  <button id="savereslut" type="button" class="btn btn-primary" data-toggle="modal" data-target="#saveresultModal">保存当前结果</button>
              </div>
          </div>
      </div>
  </div>
  <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->

  <div id="main" style="width: 100%;height:400px;background: #b0c1d2"></div>

  <script type="text/javascript">
      // init()方法
      $(function () {
          var myChart = echarts.init(document.getElementById('main'));
          echartsDynamicLineInit('/resoureusage/memoryusage',myChart,'集群Memory利用率监控UI显示','显示当前集群Memory');
      });

  </script>
  </body>
</html>
