package com.zkhc.exception_log.core.common.constant.dictmap;

import com.zkhc.exception_log.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 异常字典
 */
public class ExceptionLogDict extends AbstractDictMap {
    @Override
    public void init() {
        put("id", "异常编号");
        put("system", "系统");
        put("runEnvironment", "运行环境");
        put("host", "ip地址");
        put("port", "端口号");
        put("createtime", "异常时间");
        put("content", "内容");
        put("remark", "备注");
        put("keyWord", "关键字编号");
        put("solutionId", "解决办法");
        put("solution", "解决办法");
        put("keyWords", "关键字");
        put("uName", "添加人");
        put("exceptionLogId", "异常编号");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("solutionId", "getSolutionById");
    }
}
