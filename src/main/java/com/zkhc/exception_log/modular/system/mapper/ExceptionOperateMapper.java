package com.zkhc.exception_log.modular.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zkhc.exception_log.modular.system.entity.ExceptionOperate;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExceptionOperateMapper extends BaseMapper<ExceptionOperate> {
    /**
     * 操作日期列表(yyyy-mm-dd)
     * @return
     */
    List<String> selectDateList();

    /**
     * 根据日期查询操作列表
     * @param createTime
     * @return
     */
    List<Map<String, Object>> operateMsgByDate(@Param("createTime") String createTime);
}
