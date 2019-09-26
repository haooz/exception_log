package com.zkhc.exception_log.modular.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zkhc.exception_log.modular.system.entity.ApplicationLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplicationLogMapper extends BaseMapper<ApplicationLog> {
    /**
     * 匹配Logging数据库中异常前后请求信息
     *
     * @param startTime
     * @param endTime
     * @param systemName
     * @param env
     * @return
     */
    List<String> requestErrorInfo(@Param("startTime") String startTime, @Param("endTime") String endTime,
                                  @Param("systemName") String systemName, @Param("env") String env);
}
