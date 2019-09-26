package com.zkhc.exception_log.modular.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zkhc.exception_log.modular.system.entity.ExceptionLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HomeMapper extends BaseMapper<ExceptionLog> {
    /**
     * 异常时间范围(最小、最大)
     * @return
     */
    Map<String,Object> dateScope();

    /**
     * 各系统每日异常总数
     * @param system,startTime,endTime
     * @return
     */
    List<String> systemTotal(@Param("system") String system, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 异常总数
     * @return
     */
    int totalCount();

    /**
     *各系统异常总数
     * @return
     */
    List<Map<String,Object>> totalCountBySys();

    /**
     * 系统名
     * @return
     */
    List<String> systemList();

    /**
     * 各环境异常总数
     * @return
     */
    List<Map<String,Object>> envTotal();

    /**
     * 请求level信息
     * @return
     */
    List<Map<String,Object>> levelInfo(@Param("timeValue") String timeValue);

    /**
     * level列表
     * @return
     */
    List<String> levelList();
}
