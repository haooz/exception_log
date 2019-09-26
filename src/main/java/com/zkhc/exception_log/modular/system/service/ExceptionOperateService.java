package com.zkhc.exception_log.modular.system.service;

import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zkhc.exception_log.core.common.constant.DatasourceEnum;
import com.zkhc.exception_log.modular.system.entity.ExceptionOperate;
import com.zkhc.exception_log.modular.system.mapper.ExceptionOperateMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExceptionOperateService extends ServiceImpl<ExceptionOperateMapper, ExceptionOperate> {
    /**
     * 操作日期列表(yyyy-mm-dd)
     * @return
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public List<String> selectDateList(){
        return this.baseMapper.selectDateList();
    }

    /**
     * 根据日期查询操作列表
     * @param createTime
     * @return
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public List<Map<String, Object>> operateMsgByDate(@Param("createTime") String createTime){
        return this.baseMapper.operateMsgByDate(createTime);
    }
}
