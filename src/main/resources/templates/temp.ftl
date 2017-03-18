
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <#include "manage/public/head.ftl">
    <title>My FTL starting page</title>
  </head>

  <body>
  <h1>DataGridfdsfa1234abc222xsaadasdqwewqeqwewq</h1>

  <table class="easyui-datagrid" style="width:600px;height:250px"
         url="data/datagrid_data.json"
         title="DataGrid - Complex Toolbar" toolbar="#tb"
         singleSelect="true" fitColumns="true">
      <thead>
      <tr>
          <th field="itemid" width="60">Item ID</th>
          <th field="productid" width="80">Product ID</th>
          <th field="listprice" align="right" width="70">List Price</th>
          <th field="unitcost" align="right" width="70">Unit Cost</th>
          <th field="attr1" width="200">Address</th>
          <th field="status" width="50">Status</th>
      </tr>
      </thead>
  </table>

  <div id="tb" style="padding:5px;height:auto">
      <div style="margin-bottom:5px">
          <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
          <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
          <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
          <a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>
          <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
      </div>
      <div>
          Date From: <input class="easyui-datebox" style="width:80px">
          To: <input class="easyui-datebox" style="width:80px">
          Language:
          <input class="easyui-combobox" style="width:100px"
                 url="data/combobox_data.json"
                 valueField="id" textField="text">
          <a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
      </div>
  </div>

  </body>
</html>
</html>
