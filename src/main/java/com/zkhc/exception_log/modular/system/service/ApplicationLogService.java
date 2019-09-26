package com.zkhc.exception_log.modular.system.service;

import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zkhc.exception_log.core.common.constant.DatasourceEnum;
import com.zkhc.exception_log.modular.system.entity.ApplicationLog;
import com.zkhc.exception_log.modular.system.mapper.ApplicationLogMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationLogService extends ServiceImpl<ApplicationLogMapper, ApplicationLog> {
    @DataSource(name= DatasourceEnum.DATA_SOURCE_LOGGING)
    public List<String> requestErrorInfo(String startTime, String endTime, String systemName, String env) {
        return this.baseMapper.requestErrorInfo(startTime,endTime,systemName,env);
    }
}
