
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <#include "../public/databasehead.ftl">

        <style type="text/css">
            #search {
                margin-top: 6;
            }
            #fm{
                margin:0;
                padding:10px 30px;
            }
            .ftitle{
                font-size:14px;
                font-weight:bold;
                color:#666;
                padding:5px 0;
                margin-bottom:10px;
                border-bottom:1px solid #ccc;
            }
            .fitem{
                margin-bottom:5px;
            }
            .fitem label{
                display:inline-block;
                width:80px;
            }
        </style>

      <script type="text/javascript">
          $(function(){
              $('#dg').datagrid('load', {
                  word : ''
              });

              $('#search').searchbox({
                  searcher : function(value, name) {
                      $('#dg').datagrid('load', {
                          word : value
                      });
                  },
                  prompt : 'Please Input Value'
              });
          });

          /**
           * 修改器
           * @param val 值
           * @param row 行
           */
          function countformatter(val,row){
              return '<span style="color:red;">'+val+'</span>';
          }
      </script>

  </head>
  
  <body>
      <table id="dg" title="t_wordcount表数据" class="easyui-datagrid"
             url="/wordcount/queryByPage"
             toolbar="#toolbar"
             pagination= true
             striped=true
             nowrap=true
             idField="id"
             rownumbers="true" fitColumns="true">

          <thead frozen="true">
              <tr>
                  <th field="ck" checkbox="true"></th>
                  <th field="id" width="50">id</th>
              </tr>
          </thead>

          <thead>
          <tr>
              <th field="word" width="50">单词</th>
              <th field="time" width="50">当前时间</th>
              <th field="count" formatter="countformatter" width="20">单词统计数量</th>
          </tr>
          </thead>
      </table>

      <div id="toolbar">
          <#--<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新建用户</a>-->
          <#--<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑用户</a>-->
          <#--<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除用户</a>-->
          <input id="search">
      </div>


  </body>
</html>
