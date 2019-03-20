layui.define(['element','layer','laydate'],function(exports){

    var $ = layui.$, $body = $('body'),
        element = layui.element,
        layer = layui.layer;
    //全局初始化
    // layui.laydate.set({elem:'.layui-date'});

    var screen_size = {
        pc : [991, -1],
        pad : [768, 990],
        mobile : [0, 767]
    }

    var getDevice = function(){
        var width = $(window).width();
        for (var i in screen_size) {
            var sizes = screen_size[i],
                min = sizes[0],
                max = sizes[1];
            if(max == -1) max = width;
            if(min <= width && max >= width){
                return i;
            }
        }
        return null;
    }

    var isDevice = function(label){
        return getDevice() == label;
    }

    var isMobile = function(){
        return isDevice('mobile');
    }

    var Home = function(){

        var me = this, navItems = [];
        $('#Nav a').on('click',function(event){
            event.preventDefault();
            var $this = $(this), url = $this.attr('href'),
                title = $.trim($this.text());
            var ele = $this.closest('li.layui-nav-item');

            if( url && url!=='javascript:;' ){
                $.get(url,function(res){
                    me.loadPage(res)
                });
            }

            // ele.siblings('li.layui-nav-item').removeClass('layui-nav-itemed');
            ele.addClass('layui-nav-itemed')
        });

        // 默认触发第一个子菜单的点击事件
        // $('#Nav li.layui-nav-item:eq(0) > dl.layui-nav-child > dd > a:eq(0)').trigger('click');

        this.slideSideBar();
    }

    Home.prototype.slideSideBar = function() {
        var $slideSidebar = $('.slide-sidebar'),
            $pageContainer = $('.layui-body'),
            $mobileMask = $('.mobile-mask');

        var isFold = false;
        $slideSidebar.click(function(e){
            e.preventDefault();
            var $this = $(this), $icon = $this.find('i'),
                $admin = $body.find('.layui-layout-admin');
            var toggleClass = isMobile() ? 'fold-side-bar-xs' : 'fold-side-bar';
            if($icon.hasClass('ai-menufold')){
                $icon.removeClass('ai-menufold').addClass('ai-menuunfold');
                $admin.addClass(toggleClass);
                isFold = true;
                if(isMobile()) $mobileMask.show();
            }else{
                $icon.removeClass('ai-menuunfold').addClass('ai-menufold');
                $admin.removeClass(toggleClass);
                isFold = false;
                if(isMobile()) $mobileMask.hide();
            }
        });

        var tipIndex;
        // 菜单收起后的模块信息小提示
        $('#Nav li > a').hover(function(){
            var $this = $(this);
            if(isFold) {
                tipIndex = layer.tips($this.find('em').text(),$this);
            }
        }, function(){
            if(isFold && tipIndex ){
                layer.close(tipIndex);
                tipIndex = null
            }
        });

        Home.prototype.loadPage = function (page) {
            var $pageContainer = $('.layui-body');
            $pageContainer.empty().html(page);
        }

        if(isMobile()){
            $mobileMask.click(function(){
                $slideSidebar.trigger('click');
            });
        }
    }

    exports('phome',new Home);
});