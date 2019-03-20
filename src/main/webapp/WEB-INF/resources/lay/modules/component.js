layui.define(['element','layer','laydate'],function(exports){
    var Component = function(){

    }

    Component.prototype.loadField = function(){

    }

    Component.prototype.objConvert = function(obj){
        var elem = {};
        if(obj && layer.$.type(obj) == 'object'){
            reBuildObj(obj);
        }
    }

    function reBuildObj(obj,prefix){
        var elem = {},prefix = prefix||'';
        if(layer.$.type(obj) == 'object'){
            for(var p in obj){
                var _elem = reCatch(obj[p],prefix+'.'+p);
            }
        }else{
            prefix && (elem[prefix.substring(prefix.indexOf('.')+1)]=obj)
        //   prefix &&( prefix.indexOf('.')==0?(elem[prefix.substring(1)] = obj): (elem[prefix] = obj));
        }
        return elem;
    }

    exports('component',new Component);
});