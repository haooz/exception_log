$(function () {
    loadEchartSysTotalData();
    loadEchartSysPercentData();
    loadOperateLogData();
    loadEchartEnvTotalData();
    loadEchartLevelData();
})

/**
 * 获取各系统异常统计数据
 */
function loadEchartSysTotalData() {
    $.ajax({
        type: "get",
        dataType: "json",
        url: '/home/sysTotal',
        success: function (data) {
            var legendData = data.legendData;
            var xData = data.xData;
            setEchartSysTotal(legendData, xData, data, currentTime(xData[xData.length - 1], -10));
        }
    });
}

/**
 * 获取各系统异常占比数据
 */
function loadEchartSysPercentData() {
    $.ajax({
        type: "get",
        dataType: "json",
        url: '/home/sysPercent',
        success: function (data) {
            var legendData = data.legendData;
            var percentData = data.percentData;
            setEchartSysPercent(legendData, percentData);
        }
    });
}

/**
 * 获取各环境异常总数
 */
function loadEchartEnvTotalData() {
    $.ajax({
        type: "get",
        dataType: "json",
        url: '/home/envTotal',
        success: function (data) {
            var dataObj=data.envTotal;
            var env =[];
            var count=[];
            $.each(dataObj, function(i){
                env.push(dataObj[i].env);
                count.push(dataObj[i].total);
            })
            setEchartEnvTotal(env,count);
        }
    });
}

/**
 * 获取level信息
 */
function loadEchartLevelData() {
    $.ajax({
        type: "get",
        dataType: "json",
        url: '/home/requestLevel',
        success: function (data) {
            var dataObj=data.levelInfo;
            var xData =[];
            var yData =[];
            var count=0;
            var percentData=[];
            $.each(dataObj, function(i){
                xData.push(dataObj[i].name);
                yData.push(dataObj[i].value);
                count+=dataObj[i].value;
            })
            $.each(dataObj, function(i){
                var item = {
                    name: dataObj[i].name,
                    value: GetPercent(dataObj[i].value, count)
                }
                percentData.push(item);
            })
            console.log(xData)
            console.log(yData)
            console.log(percentData)
            setEchartLevel(xData,yData,percentData);
        }
    });
}


/**
 * 渲染各系统异常统计
 * @param legendData
 * @param dataX
 * @param data
 * @param startValue
 */
function setEchartSysTotal(legendData, dataX, data, startValue) {
    var myChartsSysTotal = echarts.init(document.getElementById('sysTotal'), myEchartsTheme);
    var optionSysTotal = {
        dataZoom: [
            {
                id: 'dataZoomX',
                type: 'slider',
                filterMode: 'filter',
                startValue: startValue
            }
        ],
        title: {
            text: '异常数量'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: legendData
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        /*toolbox: {
            feature: {
                saveAsImage: {}
            }
        },*/
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: dataX
        },
        yAxis: {
            type: 'value'
        },
        series: function () {
            var serie = [];
            for (var i = 0; i < legendData.length; i++) {
                var item = {
                    name: legendData[i],
                    type: 'line',
                    stack: '总量',
                    data: data[legendData[i] + "_yData"]
                }
                serie.push(item);
            }
            ;
            return serie;
        }()
    };
    myChartsSysTotal.setOption(optionSysTotal);
}

/**
 * 获取num天前/后日期（yyyy-MM-dd）
 * @param num
 * @returns {string}
 */
function currentTime(date, num) {
    var dd = new Date(date);
    dd.setDate(dd.getDate() + num + 1);// 获取日期
    var y = dd.getFullYear();
    var m = dd.getMonth() + 1;// 获取当前月份的日期
    var d = dd.getDate();
    var day = dd.getDay();
    m < 10 ? m = '0' + m : m;
    d < 10 ? d = '0' + d : d;
    var now_time = y + '-' + m + '-' + d;
    return now_time;
}

/**
 * 计算两个整数的百分比值
 * @param num
 * @param total
 * @returns {*}
 * @constructor
 */
function GetPercent(num, total) {
    num = parseFloat(num);
    total = parseFloat(total);
    if (isNaN(num) || isNaN(total)) {
        return "-";
    }
    return total <= 0 ? 0 : (Math.round(num / total * 10000) / 100.00);
}

/**
 * 渲染各系统异常占比
 */
function setEchartSysPercent(legendData, percentData) {
    var myCharts = echarts.init(document.getElementById('sysPercent'), myEchartsTheme);
    option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'right',
            data: legendData
        },
        series: [
            {
                name: '系统',
                type: 'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '12',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: percentData
            }
        ]
    };
    myCharts.setOption(option);
}


/**
 * 获取动态数据
 */
function loadOperateLogData() {
    $.ajax({
        type: "get",
        dataType: "json",
        url: '/exceptionOperate/createTimeList',
        success: function (data) {
            $.each(data, function (i) {
                var detail = "";
                $.ajax({
                    type: "get",
                    dataType: "json",
                    url: '/exceptionOperate/list/' + data[i],
                    async: false,
                    success: function (result) {
                        if(result.length>0){
                            $.each(result, function (i) {
                                var userName = "";
                                if (typeof(result[i].user_name) != "undefined") {
                                    userName = result[i].user_name;
                                }
                                detail += "<li><span style='font-weight:600'>" + userName + "</span>\t<span class='text-danger'>" + result[i].msg + ":</span>\t" + result[i].content + "<span class='layui-badge-rim check' onclick=\"javascript:window.open('/exceptionLog/exceptionLog_detail/" + result[i].exception_id + "')\">查看</span></li>\n";
                            })
                        }else{
                            detail +="<span>暂无数据</span>";
                        }

                    }
                });
                $("#operateLog")
                    .append("<li class=\"layui-timeline-item\">\n" +
                        "                            <i class=\"layui-icon layui-timeline-axis\">&#xe63f;</i>\n" +
                        "                            <div class=\"layui-timeline-content layui-text\">\n" +
                        "                                <h3 class=\"layui-timeline-title\">\n" +
                        "                                    <small>" + data[i] + "</small>&emsp;\n" +
                        "                                </h3>\n" +
                        "                                <ul>\n" +
                        "" + detail + "" +
                        "                                </ul>\n" +
                        "                            </div>\n" +
                        "                        </li>");
                if(i>=5){
                    return false;
                }
            });
        }
    });
}

/**
 * 渲染各环境异常总数
 */
function setEchartEnvTotal(env,count) {
    var myCharts = echarts.init(document.getElementById('envTotal'), myEchartsTheme);
    var option = {
        title: {
            textStyle: {
                color: '#000',
                fontSize: 12
            }
        },
        tooltip: {},
        grid: {
            left: '0',
            right: '0',
            bottom: '10',
            top: '10',
            containLabel: true
        },
        xAxis: {
            data: env
        },
        yAxis: {},
        series: [{
            type: 'bar',
            data: count
        }]
    };
    myCharts.setOption(option);
}


/**
 * 渲染请求级别
 */
function setEchartLevel(xData,yData,percentData) {
    var myCharts = echarts.init(document.getElementById('requestLevel'), myEchartsTheme);
    var option = {
        baseOption: {
            timeline: {
                show:false
            },
            title: {
                subtext: ''
            },
            tooltip: {
            },
            legend: {
                x: 'right',
                data: xData,
            },
            calculable : true,
            xAxis: [
                {
                    'type':'category',
                    'data': xData,
                    splitLine: {show: false}
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: ''
                }
            ],
            series: [
                {name: '', type: 'bar',itemStyle: {
                    normal: {
                        color: function(params) {
                            var colorList = ["#009688", "#1E9FFF", "#5FB878", "#FFB980", "#D87A80", "#8d98b3", "#e5cf0d", "#97b552", "#95706d", "#dc69aa", "#07a2a4", "#9a7fd1", "#588dd5", "#f5994e", "#c05050", "#59678c", "#c9ab00", "#7eb00a", "#6f5553", "#c14089"];
                            return colorList[params.dataIndex]
                        }
                    }
                }},
                {
                    name: '',
                    type: 'pie',
                    center: ['75%', '35%'],
                    radius: '28%',
                    z: 999999
                }
            ]
        },
        options: [
            {
                title: {text: ''},
                series: [
                    {data: yData},
                    {data: percentData}
                ]
            }
        ]
    };
    myCharts.setOption(option);
}