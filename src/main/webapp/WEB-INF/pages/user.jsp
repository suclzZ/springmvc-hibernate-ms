<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/layui.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/view.css"/>
    <link rel="icon" href="/favicon.ico">
    <title>管理后台</title>
</head>
<body class="layui-view-body">
    <div class="layui-content">
        <div class="layui-page-header layui-hide">
            <div class="pagewrap">
                <span class="layui-breadcrumb">
                  <a href="">首页</a>
                  <a href="">用户</a>
                  <a><cite>用户管理</cite></a>
                </span>
                <h2 class="title">用户管理</h2>
            </div>
        </div>
        <div id="form_add" class="layui-card" style="display: none">
            <%--<div class="layui-card-header">表单</div>--%>
            <form class="layui-form layui-card-body" action="" lay-filter="form_add">
                <div class="layui-form-item">
                    <label class="layui-form-label">登录名</label>
                    <div class="layui-input-block">
                        <input type="text" name="loginName" required  lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-block">
                        <input type="text" name="userCaption" required  lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">生日</label>
                    <div class="layui-input-block">
                        <input type="text" name="birthday" required  lay-verify="date" placeholder="请输入" autocomplete="off" class="layui-input layui-date">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">电话号码</label>
                    <div class="layui-input-block">
                        <input type="text" name="telephone" required  lay-verify="phone" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">电子邮箱</label>
                    <div class="layui-input-block">
                        <input type="text" name="email" required  lay-verify="email" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">QQ</label>
                    <div class="layui-input-block">
                        <input type="text" name="qq" required  placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">微信</label>
                    <div class="layui-input-block">
                        <input type="text" name="weixin" required  placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">机构</label>
                    <div class="layui-input-block">
                        <select name="agency.agencyId">
                            <option value=""></option>
                            <option value="0">北京</option>
                            <option value="1">上海</option>
                            <option value="2">广州</option>
                            <option value="3">深圳</option>
                            <option value="4">杭州</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">描述</label>
                    <div class="layui-input-block">
                        <textarea name="description" placeholder="请输入内容" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-input-block layui-hide">
                    <button id="btn_form_add_submit" class="layui-btn" lay-submit lay-filter="form_submit">立即提交</button>
                    <button id="btn_form_add_reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </form>
        </div>
        <div class="layui-row">
            <div class="layui-card">
                <div class="layui-card-body">
                    <div class="form-box">
                        <div class="layui-form layui-form-item">
                            <div class="layui-inline">
                                <div class="layui-form-mid">用户名:</div>
                                <div class="layui-input-inline" style="width: 100px;">
                                  <input type="text" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid">邮箱:</div>
                                <div class="layui-input-inline" style="width: 100px;">
                                  <input type="text" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid">性别:</div>
                                <button class="layui-btn layui-btn-blue  layui-btn-radius layui-btn-sm">查 询</button>
                                <button class="layui-btn layui-btn-primary layui-btn-radius layui-btn-sm">重 置</button>
                            </div>
                        </div>
                        <button id="btn_add" class="layui-btn layui-btn-blue layui-btn-radius layui-btn-sm" lay-filter="add"><i class="layui-icon">&#xe654;</i>新增</button>
                        <table id="table_user"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()%>/resources/layui.all.js"></script>
    <script>
        // layui.use('element','table','from','layer','$','laydate',function(){
        //
        // });

      var element = layui.element;
      var table = layui.table,
          form = layui.form,
          layer = layui.layer,
          $ = layui.$,
          laydate = layui.laydate;

      //初始化日期控件
      laydate.render({
          elem: '.layui-date' //指定元素
      });
  
      //展示已知数据
      table.render({
        elem: '#table_user'
        ,url:'<%=request.getContextPath()%>/user/getPageUser'
        ,request:{
              pageName:'pageIndex',
              limitName:'pageSize'
        }
        ,response: {
          // statusName: 'status' //规定数据状态的字段名称，默认：code
          statusCode: 200 //规定成功的状态码，默认：0,必须的，不然会异常
          // ,msgName: 'hint' //规定状态信息的字段名称，默认：msg
          ,countName: 'totleCount' //规定数据总数的字段名称，默认：count
          ,dataName: 'records' //规定数据列表的字段名称，默认：data
        }
        ,cols: [[ //标题栏
          {field: 'userId', title: 'ID', width: 80, sort: true}
          ,{field: 'loginName', title: '登录名', width: 100}
          ,{field: 'userCaption', title: '用户名', minWidth: 120}
          ,{field: 'birthday', title: '生日', minWidth: 100}
          ,{field: 'telephone', title: '电话', minWidth: 100}
          ,{field: 'email', title: '邮箱', width: 120}
          ,{field: 'qq', title: 'QQ', width: 100}
          ,{field: 'weixin', title: '微信', width: 100, sort: false}
        ]]
        ,skin: 'line' //表格风格
        ,even: true
        ,page: true //是否显示分页
        ,limits: [15, 20, 30]
        // ,limit: 15 //每页默认显示的数量
      });

      //提交按钮监听
      form.on('submit(form_submit)',function (data) {
          debugger
          $.ajax({
              url:'<%=request.getContextPath()%>/user/saveUser',
              method:'POST',
              data:data.field,
              contentType: "application/x-www-form-urlencoded",
              success:function(res){
                  layer.alert('提交成功！');
              }
          })
      });
      //form.render(null, 'test1'); // 重置

      //表单填值
      // form.val("formTest", { //filtter
      //     "username": "贤心" // "name": "value"
      //     ,"sex": "女"
      //     ,"auth": 3
      //     ,"check[write]": true
      //     ,"open": false
      //     ,"desc": "我爱layui"
      // })

      //新增
      $('#btn_add').on('click', function(data){//btn(add)
          layer.open({
              type:'1',
              area: '800px',
              offset: 'auto',
              title:'新增用户',
              content:$('#form_add'),
              btn:['提交','重置','取消'],
              yes :function(){
                  $('#btn_form_add_submit').click();
              },
              btn2:function () {
                  form.render(null, 'form_add');
              },
              btn3:function () {

              }
          })
      });
    </script>
</body>
</html>