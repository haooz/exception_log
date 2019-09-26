layui.use(['layer', 'table', 'ax', 'laydate'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var table = layui.table;

    /**
     * 异常列表
     */
    var ExceptionLog = {
        tableId: "exceptionLogTable"   //表格id
    };

    /**
     * 初始化表格的列
     */
    ExceptionLog.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'system', sort: true, title: '系统',width:documentWith*130/1200},
            {field: 'run_environment', sort: true, title: '运行环境',width:documentWith*80/1200},
            {field: 'host', sort: true, title: 'ip地址',width:documentWith*90/1200},
            {field: 'port', sort: true, title: '端口号',width:documentWith*60/1200},
            {field: 'createtime', sort: true, title: '异常发生时间',width:documentWith*120/1200},
            {field: 'content', sort: true, title: '异常内容',
                templet: function(d){
                    return d.content.substring(0,50)+"...";
                }},
            {field: 'view_num', sort: true, title: '浏览次数',width:documentWith*80/1200},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    ExceptionLog.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(ExceptionLog.tableId, {where: queryData});
    };

    /**
     * 导出excel按钮
     */
    ExceptionLog.exportExcel = function () {
        var checkRows = table.checkStatus(ExceptionLog.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    //清空日志
    ExceptionLog.cleanLog = function () {
        Feng.confirm("是否清空所有日志?", function () {
            var ajax = new $ax(Feng.ctxPath + "/ExceptionLog/delExceptionLog", function (data) {
                Feng.success("清空日志成功!");
                ExceptionLog.search();
            }, function (data) {
                Feng.error("清空日志失败!");
            });
            ajax.start();
        });
    };


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ExceptionLog.tableId,
        url: Feng.ctxPath + '/exceptionLog/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: ExceptionLog.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ExceptionLog.search();
    });

    // 搜索按钮点击事件
    $('#btnClean').click(function () {
        ExceptionLog.cleanLog();
    });

    // 导出excel
    $('#btnExp').click(function () {
        ExceptionLog.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ExceptionLog.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'remark') {
            ExceptionLog.openRemark(data);
        } else if (layEvent === 'keyWord') {
            ExceptionLog.openKeyWord(data);
        } else if (layEvent === 'solution') {
            ExceptionLog.openSolution(data);
        } else if (layEvent === 'detail') {
            ExceptionLog.openDetail(data);
        } else if (layEvent === 'delete') {
            ExceptionLog.delete(data);
        }
    });

    /**
     * 跳转备注
     */
    ExceptionLog.openRemark = function (data) {
        layer.open({
            type: 2,
            title: '备注',
            area: ['800px', '450px'], //宽高
            fix: false,
            maxmin: true,
            content: Feng.ctxPath + '/exceptionLog/remarkDetail/' + data.id,
            end: function () {
                table.reload(ExceptionLog.tableId);
            }
        });
    };

    /**
     * 跳转关键字
     */
    ExceptionLog.openKeyWord = function (data) {
        layer.open({
            type: 2,
            title: '关键字',
            area: ['800px', '450px'], //宽高
            fix: false,
            maxmin: true,
            content: Feng.ctxPath + '/exceptionLog/keyWords/' + data.id,
            end: function () {
                table.reload(ExceptionLog.tableId);
            }
        });
    };

    /**
     * 跳转解决办法
     */
    ExceptionLog.openSolution = function (data) {
        layer.open({
            type: 2,
            title: '解决办法',
            area: ['800px', '450px'], //宽高
            fix: false,
            maxmin: true,
            content: Feng.ctxPath + '/exceptionLog/solution/' + data.id,
            end: function () {
                table.reload(ExceptionLog.tableId);
            }
        });
    };

    /**
     * 删除后台异常信息管理
     */
    ExceptionLog.delete = function (data) {
        var ajax = new $ax(Feng.ctxPath + "/exceptionLog/delete", function (data) {
            Feng.success("删除成功!");
            ExceptionLog.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("exceptionLogId",data.id);
        ajax.start();
    };

    /**
     * 跳转详情
     */
    ExceptionLog.openDetail = function (data) {
        window.open(Feng.ctxPath + '/exceptionLog/exceptionLog_detail/' + data.id );
    };
});
