package com.zkhc.exception_log.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.alibaba.fastjson.JSON;
import com.zkhc.exception_log.modular.system.entity.ExceptionLog;
import com.zkhc.exception_log.modular.system.service.ExceptionOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常日志操作控制器
 */
@Controller
@RequestMapping("/exceptionOperate")
public class ExceptionOperateController extends BaseController {
    @Autowired
    private ExceptionOperateService exceptionOperateService;

    /**
     * 操作日期集合
     */
    @RequestMapping("/createTimeList")
    @ResponseBody
    public Object createTimeList() {
        return JSON.toJSON(exceptionOperateService.selectDateList());
    }

    /**
     * 操作集合
     */
    @RequestMapping("/list/{createTime}")
    @ResponseBody
    public Object list(@PathVariable String createTime, Model model) {
        return JSON.toJSON(exceptionOperateService.operateMsgByDate(createTime));
    }
}
