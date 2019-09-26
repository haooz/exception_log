layui.use(['layer', 'table', 'ax'], function () {
    var table = layui.table;
    var $ax = layui.ax;
    //转换静态表格
    table.init('demo', {
        height: 315 //设置高度
        , limit: 10 //注意：请务必确保 limit 参数（默认：10）是与你服务端限定的数据条数一致
        //支持所有基础参数
    });

    $(".delete").click(function(){
        cleanLog($(this).parent().parent().prev().find("span").text());
    });

    function cleanLog(obj) {
        Feng.confirm("是否删除该条备注?", function () {
            var ajax = new $ax(Feng.ctxPath + "/exceptionLog/deleteRemark", function (data) {
                Feng.success("删除成功!");
                location.reload();
            }, function (data) {
                Feng.error("删除失败!");
            });
            ajax.set("id",id);
            ajax.set("remark",obj);
            ajax.start();
        });
    };
})