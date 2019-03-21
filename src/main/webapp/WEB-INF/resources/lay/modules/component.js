layui.define(['element','layer','laydate'],function(exports){
    var $ = layui.$;
    var Component = function(){

    }

    Component.prototype.loadField = function(){

    }

    Component.prototype.objConvert = function(obj){
        if(obj && $.type(obj) == 'object'){
            return reBuildObj(obj);
        }
        return obj
    }

    function reBuildObj(obj,prefix){
        var elem = {},prefix = prefix||'';
        if($.type(obj) == 'object'){
            for(var p in obj){
                var _elem = reBuildObj(obj[p],prefix+'.'+p);
                $.extend(elem,_elem);
            }
        }else{
            prefix && (elem[prefix.substring(prefix.indexOf('.')+1)]=obj)
        //   prefix &&( prefix.indexOf('.')==0?(elem[prefix.substring(1)] = obj): (elem[prefix] = obj));
        }
        return elem;
    }

    exports('component',new Component);
});