package com.zkhc.exception_log.modular.system.service;

import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zkhc.exception_log.core.common.constant.DatasourceEnum;
import com.zkhc.exception_log.modular.system.entity.ExceptionLog;
import com.zkhc.exception_log.modular.system.mapper.HomeMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class HomeService extends ServiceImpl<HomeMapper, ExceptionLog> {
    /**
     * 异常时间范围(最小、最大)
     * @return
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public Map<String,Object> dateScope(){
        return this.baseMapper.dateScope();
    }

    /**
     * 各系统每日异常总数
     * @param system,startTime,endTime
     * @return
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public List<String> systemTotal(String system,String startTime,String endTime){
        return this.baseMapper.systemTotal(system,startTime,endTime);
    }

    /**
     * 异常总数
     * @return
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public int totalCount(){
        return this.baseMapper.totalCount();
    }

    /**
     *各系统异常总数
     * @return
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public List<Map<String,Object>> totalCountBySys(){
        return this.baseMapper.totalCountBySys();
    }

    /**
     * 系统名
     * @return
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public List<String> systemList(){
        return this.baseMapper.systemList();
    }

    /**
     * 各环境异常总数
     * @return
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public List<Map<String,Object>> envTotal(){
        return this.baseMapper.envTotal();
    }

    /**
     * 请求level信息
     * @return
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_LOGGING)
    public List<Map<String,Object>> levelInfo(@Param("timeValue") String timeValue){
        return this.baseMapper.levelInfo(timeValue);
    }

    /**
     * level列表
     * @return
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_LOGGING)
    public List<String> levelList(){
        return this.baseMapper.levelList();
    }
}
