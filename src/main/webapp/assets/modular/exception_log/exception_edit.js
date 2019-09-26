var ExceptionOperate={};
/**
 * 下载
 */
ExceptionOperate.downloadData=function(){
    window.open("/exceptionLog/downloadFile?id="+$("#id").val());
};

/**
 * 得到鼠标选择文字
 * @returns {string}
 */
ExceptionOperate.getSelectText=function(){
    return window.getSelection ? window.getSelection().toString() :
        document.selection.createRange().text;
}

/**
 * 添加关键字及解决办法
 */
ExceptionOperate.addKeyAndSolution=function(){
    if(ExceptionOperate.getSelectText().length>0){
        layer.open({
            formType:2,
            title:'是否添加关键字及解决办法？',
            area: ['600px', '400px'],
            content:"<div class='layui-row'><p><span style='display: block'>关键字:</span><textarea id='kw' style='width: 95%;resize: none;'>"+ExceptionOperate.getSelectText()+"</textarea></p><p><span style='display: block'>解决办法:</span><textarea style='width: 95%;resize: none;height:130px;' id='solution'></textarea></p></div>",
            btn:['确认','取消'],
            yes:function(index,solution){
                if($("#kw").val()!="" && $("#kw").val()!=null && $("#solution").val()!="" && $("#solution").val()!=null){
                    var ajax = new $ax(Feng.ctxPath + "/exceptionLog/addSolutionWithKW", function (data) {
                        Feng.success("添加成功!");
                        window.location.reload();
                    }, function (data) {
                        Feng.error("添加失败!" + data.responseJSON.message + "!");
                    });
                    ajax.set("id",$("#id").val());
                    ajax.set("keyWords",$("#kw").val());
                    ajax.set("solution",$("#solution").val());
                    ajax.start();
                }else{
                    Feng.error("关键字和解决办法不能为空!");
                }
            }
        });
    }
}

/**
 * 手动添加关键字
 */
ExceptionOperate.addKey=function(){
    layer.open({
        formType:2,
        title:'添加关键字',
        area: ['600px', '400px'],
        content:"<div class='layui-row'><p><span style='display: block'>关键字:</span><textarea id='kw' style='width: 95%;resize: none;'></textarea></p><p><span style='display: block'>解决办法:</span><textarea style='width: 95%;resize: none;height:130px;' id='solution'></textarea></p></div>",
        btn:['确认','取消'],
        yes:function(index,solution){
            if($("#kw").val()!="" && $("#kw").val()!=null && $("#solution").val()!="" && $("#solution").val()!=null){
                var ajax = new $ax(Feng.ctxPath + "/exceptionLog/addSolutionWithKW", function (data) {
                    Feng.success("添加成功!");
                    window.location.reload();
                }, function (data) {
                    Feng.error("添加失败!" + data.responseJSON.message + "!");
                });
                ajax.set("id",$("#id").val());
                ajax.set("keyWords",$("#kw").val());
                ajax.set("solution",$("#solution").val());
                ajax.start();
            }else{
                Feng.error("关键字和解决办法不能为空!");
            }
        }
    });

}

/**
 * 添加解决办法
 */
ExceptionOperate.addReason=function(){
    layer.prompt({title: '请输入解决办法',formType:2,area: ['500px', '200px'],}, function(solution, index){
        layer.close(index);
        if(solution!=null && solution!="" && solution.length>0){
            var ajax = new $ax(Feng.ctxPath + "/exceptionLog/addSolution", function (data) {
                Feng.success("添加成功!");
                window.location.reload();
            }, function (data) {
                Feng.error("添加失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id",$("#id").val());
            ajax.set("solution",solution);
            ajax.start();
        }
    });
}

/**
 * 已解决类似问题
 */
ExceptionOperate.sameSolution=function(){
    var index = layer.open({
        type: 2,
        title: '类似已解决问题',
        area: ['600px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/exceptionLog/sameListPage/'+$("#exception").val()+"/"+$("#id").val()
    });
    this.layerIndex = index;
}

/**
 * 添加备注
 */
ExceptionOperate.addRemark=function(){
    layer.open({
        formType:2,
        title:'添加备注',
        area: ['600px', '400px'],
        content:"<div class='layui-row'><p><span style='display: block'>姓名:</span><input id='name' style='width: 95%;line-height:26px;'></p><p><span style='display: block'>备注:</span><textarea style='width: 95%;resize: none;height:130px;' id='remark'></textarea></p></div>",
        btn:['确认','取消'],
        yes:function(index,remark){
            if($("#name").val()!="" && $("#name").val()!=null && $("#remark").val()!="" && $("#remark").val()!=null){
                var ajax = new $ax(Feng.ctxPath + "/exceptionLog/addRemark", function (data) {
                    Feng.success("添加成功!");
                    window.location.reload();
                }, function (data) {
                    Feng.error("添加失败!" + data.responseJSON.message + "!");
                });
                ajax.set("id",$("#id").val());
                ajax.set("uName",$("#name").val());
                ajax.set("remark",$("#remark").val());
                ajax.start();
            }else{
                Feng.error("姓名和备注不能为空!");
            }
        }
    });
}

/**
 * 回到顶部
 */
ExceptionOperate.goTop=function(selector){
    $('html,body').animate({'scrollTop':0},600);
}

/**
 * 类似解决办法滚屏
 */
ExceptionOperate.FloatAd=function(selector){
    var obj = $(selector);
    if (jsonObj.length == 0) return;//如果没有内容，不执行
    var windowHeight = $(window).height();//浏览器高度
    var windowWidth = $(window).width();//浏览器宽度
    var dirX = -1.5;//每次水平漂浮方向及距离(单位：px)，正数向右，负数向左，如果越大的话就会看起来越不流畅，但在某些需求下你可能会需要这种效果
    var dirY = -1;//每次垂直漂浮方向及距离(单位：px)，正数向下，负数向上，如果越大的话就会看起来越不流畅，但在某些需求下你可能会需要这种效果

    var delay = 30;//定期执行的时间间隔，单位毫秒
    obj.css({ left: windowWidth / 2 - obj.width() / 2 + "px", top: windowHeight / 2 - obj.height() / 2 + "px" });//把元素设置成在页面中间
    obj.show();//元素默认是隐藏的，避免上一句代码改变位置视觉突兀，改变位置后再显示出来
    var handler = setInterval(move, delay);//定期执行，返回一个值，这个值可以用来取消定期执行

    obj.hover(function() {//鼠标经过时暂停，离开时继续
        clearInterval(handler);//取消定期执行
    }, function() {
        handler = setInterval(move, delay);
    });

    obj.find(".close").click(function() {//绑定关闭按钮事件
        close();
    });
    $(window).resize(function() {//当改变窗口大小时，重新获取浏览器大小，以保证不会过界（飘出浏览器可视范围）或漂的范围小于新的大小
        windowHeight = $(window).height();//浏览器高度
        windowWidth = $(window).width();//浏览器宽度
    });
    function move() {//定期执行的函数，使元素移动
        var currentPos = obj.position();//获取当前位置，这是JQuery的函数，具体见：http://hemin.cn/jq/position.html
        var nextPosX = currentPos.left + dirX;//下一个水平位置
        var nextPosY = currentPos.top + dirY;//下一个垂直位置

        /*if (nextPosX >= windowWidth - obj.width()) {//当漂浮到右边时关闭漂浮窗口，如不需要可删除
            close();
        }*/

        if (nextPosX <= 0 || nextPosX >= windowWidth - obj.width()) {//如果达到左边，或者达到右边，则改变为相反方向
            dirX = dirX * -1;//改变方向
            nextPosX = currentPos.left + dirX;//为了不过界，重新获取下一个位置
        }
        if (nextPosY <= 0 || nextPosY >= windowHeight - obj.height() - 5) {//如果达到上边，或者达到下边，则改变为相反方向。
            dirY = dirY * -1;//改变方向
            nextPosY = currentPos.top + dirY;//为了不过界，重新获取下一个位置
        }
        obj.css({ left: nextPosX + "px", top: nextPosY + "px" });//移动到下一个位置
    }

    function close() {//停止漂浮，并销毁漂浮窗口
        clearInterval(handler);
        obj.remove();
    }
}

/**
 * 关闭
 */
ExceptionOperate.minTanChuang=function(){
    $("#btn_min").click();
}

/**
 * 弹框
 */
ExceptionOperate.TanChuang=function(){
    var oDiv = document.getElementById('miaov_float_layer');
    var oBtnMin = document.getElementById('btn_min');
    var oBtnClose = document.getElementById('btn_close');
    var oDivContent = oDiv.getElementsByTagName('div')[0];

    var iMaxHeight = 0;

    var isIE6 = window.navigator.userAgent.match(/MSIE 6/ig) && !window.navigator.userAgent.match(/MSIE 7|8/ig);

    oDiv.style.display = 'block';
    iMaxHeight = oDivContent.offsetHeight;

    if (isIE6) {
        oDiv.style.position = 'absolute';
        repositionAbsolute();
        miaovAddEvent(window, 'scroll', repositionAbsolute);
        miaovAddEvent(window, 'resize', repositionAbsolute);
    }
    else {
        oDiv.style.position = 'fixed';
        repositionFixed();
        miaovAddEvent(window, 'resize', repositionFixed);
    }

    oBtnMin.timer = null;
    oBtnMin.isMax = true;
    oBtnMin.onclick = function() {
        startMove
        (
            oDivContent, (this.isMax = !this.isMax) ? iMaxHeight : 0,
            function() {
                oBtnMin.className = oBtnMin.className == 'min' ? 'max' : 'min';
            }
        );
    };

    oBtnClose.onclick = function() {
        oDiv.style.display = 'none';
        oDiv.innerHTML = "";
        $.cookie("isClose",'no',{ expires:1/8640});  //关闭十秒后才能再次弹出
    };
}

$(function(){
    $(".side ul li").hover(function(){
        $(this).find(".sidebox").stop().animate({"width":"124px"},200).css({"opacity":"1","filter":"Alpha(opacity=100)","background":"#ae1c1c"})
    },function(){
        $(this).find(".sidebox").stop().animate({"width":"54px"},200).css({"opacity":"0.8","filter":"Alpha(opacity=80)","background":"#018D75"})
    });

    ExceptionOperate.FloatAd("#floadAD");
    for(var i = 0; i<jsonObj.length; i++){
        $("#exceptionList").append(" <li value="+jsonObj[i].solution+">"+jsonObj[i].solution+"</li>");
    }

    for(var i = 0; i<remarksObj.length; i++){
        $("#remarks").append(" <li value="+remarksObj[i]+">"+remarksObj[i]+"</li>");
    }

    if($.cookie("isClose") != 'yes') {
        ExceptionOperate.TanChuang();
        setTimeout("ExceptionOperate.minTanChuang()",5000); //5秒自动关闭
    }
})