<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/layui.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/view.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/asserts/ztree/css/metroStyle/metroStyle.css">
    <link rel="icon" href="/favicon.ico">
    <title>管理后台</title>
</head>

<body class="layui-view-body">
    <div id="form_add" class="layui-card" style="display: none">
    <%--<div class="layui-card-header">表单</div>--%>
    <form class="layui-form layui-card-body" lay-filter="form_add">
        <div class="layui-form-item layui-hide">
            <label class="layui-form-label">ID</label>
            <div class="layui-input-block">
                <input type="text" name="userId" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">登录名</label>
            <div class="layui-input-block">
                <input type="text" name="loginName"  lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="userCaption"  lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">生日</label>
            <div class="layui-input-block">
                <input type="text" name="birthday"  lay-verify="date" placeholder="请输入" autocomplete="off" class="layui-input layui-date">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电话号码</label>
            <div class="layui-input-block">
                <input type="text" name="telephone"  lay-verify="phone" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电子邮箱</label>
            <div class="layui-input-block">
                <input type="text" name="email"  lay-verify="email" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">QQ</label>
            <div class="layui-input-block">
                <input type="text" name="qq" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">微信</label>
            <div class="layui-input-block">
                <input type="text" name="weixin" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">机构</label>
            <div class="layui-input-block">
                <select name="agency.agencyId">
                    <option></option>
                    <option value="1">信息部</option>
                    <option value="2">科技部</option>
                    <option value="3">市场部</option>
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
    <div id="form_authc" class="layui-card" style="display: none">
        <%--<div class="layui-card-header">表单</div>--%>
        <form class="layui-form layui-card-body" lay-filter="form_authc">
            <div class="layui-form-item layui-hide">
                <label class="layui-form-label">ID</label>
                <div class="layui-input-block">
                    <input type="text" name="userId" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">登录名</label>
                <div class="layui-input-block">
                    <input type="text" name="loginName" readonly autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="userCaption"  readonly autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-hide">
                <label class="layui-form-label">已选角色</label>
                <div class="layui-input-block">
                    <input type="text" name="roles" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">角色</label>
                <div class="layui-input-block">
                    <div id="tree_role" class="ztree"></div>
                </div>
            </div>

            <div class="layui-input-block layui-hide">
                <button id="btn_form_authc_submit" class="layui-btn" lay-submit lay-filter="form_authc_submit">立即提交</button>
                <button id="btn_form_authc_reset" type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </form>
    </div>
    <div id="content-user" class="layui-content">
        <div class="layui-row">
            <div class="layui-card">
                <div class="layui-card-body">
                    <div class="form-box">
                        <div class="layui-form layui-form-item">
                            <div class="layui-inline">
                                <div class="layui-form-mid">用户名:</div>
                                <div class="layui-input-inline" style="width: 200px;">
                                  <input type="text" name="userCaption" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid">电话:</div>
                                <div class="layui-input-inline" style="width: 200px;">
                                  <input type="text" name="telephone" autocomplete="off" class="layui-input">
                                </div>
                                <button class="layui-btn layui-btn-blue  layui-btn-radius layui-btn-sm">查 询</button>
                                <button class="layui-btn layui-btn-primary layui-btn-radius layui-btn-sm">重 置</button>
                            </div>
                        </div>
                        <div class="layui-btn-container layui-col-md-offset9">
                        <button data-method="tableAdd" class="layui-btn layui-btn-blue layui-btn-radius layui-btn-sm"><i class="layui-icon">&#xe654;</i>新增</button>
                        <button data-method="tableRemove" class="layui-btn layui-btn-blue layui-btn-radius layui-btn-sm"><i class="layui-icon">&#xe640;</i>删除</button>
                        <button data-method="authcRole" class="layui-btn layui-btn-blue layui-btn-radius layui-btn-sm"><i class="layui-icon">&#xe672;</i>角色授权</button>
                        </div>
                        <table id="table_user" class="layui-table" lay-filter="table_user"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--<script src="<%=request.getContextPath()%>/resources/layui.js"></script>--%>
    <script src="<%=request.getContextPath()%>/resources/asserts/ztree/js/jquery-1.4.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/asserts/ztree/js/jquery.ztree.all.min.js"></script>
    <script>
        layui.use(['element','table','form','layer','component','laydate'],function(){
            var element = layui.element;
            var table = layui.table,
                form = layui.form,
                layer = layui.layer,
                $ = layui.$,
                laydate = layui.laydate,
                component = layui.component;

            //初始化日期控件
            laydate.render({
                elem: '.layui-date' //指定元素
            });

            //展示已知数据
            table.render({
                id:'table_user',
                elem: '#table_user'
                ,url:'<%=request.getContextPath()%>/user/getPageUser'
                ,request:{
                    pageName:'page:index',
                    limitName:'page:size'
                }
                ,response: {
                    // statusName: 'status' //规定数据状态的字段名称，默认：code
                    statusCode: 200 //规定成功的状态码，默认：0,必须的，不然会异常
                    // ,msgName: 'hint' //规定状态信息的字段名称，默认：msg
                    ,countName: 'totleCount' //规定数据总数的字段名称，默认：count
                    ,dataName: 'records' //规定数据列表的字段名称，默认：data
                }
                ,cols: [[ //标题栏
                    {type: 'checkbox', fixed: 'left',align:'center'},
                    {field: 'userId', title: 'ID', width: 80, hide:true}
                    ,{field: 'loginName', title: '登录名', width: 100,sort:true}
                    ,{field: 'userCaption', title: '用户名', minWidth: 120}
                    ,{field: 'birthday', title: '生日', minWidth: 100}
                    ,{field: 'telephone', title: '电话', minWidth: 100}
                    ,{field: 'email', title: '邮箱', width: 120}
                    ,{field: 'qq', title: 'QQ', width: 100}
                    ,{field: 'weixin', title: '微信', width: 100, sort: false}
                ]]
                ,skin: 'line' //表格风格
                ,page: true
            });
            table.on('rowDouble(table_user)', function(obj){
                showAddForm(function(){
                    $.ajax({
                        url:'<%=request.getContextPath()%>/user/getUser',
                        method:'GET',
                        data:'id='+obj.data.userId,
                        success:function (res) {
                            form.val('form_add',component.objConvert(res));
                        }
                    })
                })
            });
            table.on('row(table_user)', function(obj){
                //选中无效
                // if(!$(this).parents('.layui-table-body').hasClass('layui-table-main')){
                //     return;
                // }
                // var trs = obj.tr;
                // var tr = trs[0],ck = trs[1];
                // var ckInput  = $(ck).find("input[type='checkbox']");
                // if(ckInput.is(':checked')){
                //     ckInput.attr("checked",false).next().removeClass('layui-form-checked');
                // }else{
                //     ckInput.attr("checked", 'checked').next().addClass('layui-form-checked');
                // }
            });

            //提交按钮监听
            form.on('submit(form_submit)',function (data) {
                $.ajax({
                    url:'<%=request.getContextPath()%>/user/saveUser',
                    method:'POST',
                    data:data.field,
                    contentType: "application/x-www-form-urlencoded",
                    success:function(res){
                        var msg = '提交成功！';
                        if(res.code =='0000'){
                            msg = res.msg;
                        }else{
                            layer.close($(data.form).data('layer-index'));
                        }
                        layer.alert(msg);
                    },
                    failure:function () {
                        layer.alert('提交失败！',{icon:2});
                    }
                })
            });
            //授权
            form.on('submit(form_authc_submit)',function (data) {
                //用户id ,角色(roles[0].rileId ....)
                //最好通过user实体自动处理，但是千万注意，用户此时的信息只有id,调用update用户的其他信息将会覆盖为null

            })

            function showAddForm(callback){
                layer.open({
                    type:'1',
                    area: ['600px','500px'],
                    shade: 0,
                    fixed:true,
                    skin:'custom-layer-class',
                    offset: 'auto',
                    title:'新增用户',
                    content:$('#form_add'),
                    btn:['提交','重置','取消'],
                    yes :function(index, layero){
                        layero.find('form')[0].reset()
                        layero.find('form').data('layer-index',index);
                    },
                    btn2:function (index, layero) {
                        // form.render(null, 'form_add');
                        $('#btn_form_add_reset').click();
                        return false;
                    },
                    btn3:function (index, layero) {
                        layer.close(index);
                    },
                    success : function(layero, index){
                        //form.render(null, 'test1'); // 重置
                        layero.find('form')[0].reset();
                        $.isFunction(callback) && callback.call(this);
                    }
                });
            }
            function showAuthcForm(callback){
                layer.open({
                    type:'1',
                    area: ['600px','500px'],
                    fixed:true,
                    shade: 0,
                    skin:'custom-layer-class',
                    offset: 'auto',
                    title:'角色设置',
                    content:$('#form_authc'),
                    btn:['提交','取消'],
                    yes :function(index, layero){
                        layero.find('form')[0].reset()
                        layero.find('form').data('layer-index',index);
                    },
                    btn2:function (index, layero) {
                        layer.close(index);
                    },
                    success : function(layero, index){
                        //form.render(null, 'test1'); // 重置
                        layero.find('form')[0].reset();
                        $.isFunction(callback) && callback.call(this);
                    }
                });
            }
            //按钮事件
            var active = {
                tableAdd:function(){
                    showAddForm();
                },
                tableRemove:function(){
                    var userIds = table.checkStatus('table_user').data.map(function(e){return e.userId});
                    if(!userIds || userIds.length==0){layer.alert('请选择一条数据！'); return;}
                    layer.msg('确定删除该条数据？', {
                        time: 20000, //20s后自动关闭
                        btn: ['确定', '取消'],
                        yes:function(index, layero){
                            $.ajax({
                                url:'<%=request.getContextPath()%>/user/removeUser',
                                async:false,
                                data:'id='+userIds.join(","),//自行处理多行删除
                                success:function(res){
                                    layer.msg('删除成功！',{offset:'t'});
                                    layer.close(index);
                                    table.reload('table_user');
                                }
                            })
                        }
                    });
                },
                authcRole:function () {
                    var userIds = table.checkStatus('table_user').data.map(function(e){return e.userId});
                    if(!userIds || userIds.length==0){layer.alert('请选择一条数据！'); return;}
                    showAuthcForm(function(){
                        $.ajax({
                            url:'<%=request.getContextPath()%>/user/getUserAndAllroles',
                            data:{id:userIds[0]}
                        }).done(function(trees){
                            $.get('<%=request.getContextPath()%>/user/getUser',{id:userIds[0]},function(user){
                                form.val('form_authc',user);
                            });
                            var setting = {
                                check: {
                                    enable: true
                                },
                                data: {
                                    simpleData: {
                                        enable: true
                                    }
                                }
                            };
                            jQuery.fn.zTree.init($("#tree_role"), setting, trees);
                        });
                    });
                }
            };

            $('#content-user .layui-btn').on('click', function(){
                var othis = $(this), method = othis.data('method');
                active[method] ? active[method].call(this, othis) : '';
            });
        });
    </script>
</body>
</html>