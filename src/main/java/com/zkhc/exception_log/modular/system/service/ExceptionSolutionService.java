package com.zkhc.exception_log.modular.system.service;

import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zkhc.exception_log.core.common.constant.DatasourceEnum;
import com.zkhc.exception_log.modular.system.entity.ExceptionSolution;
import com.zkhc.exception_log.modular.system.mapper.ExceptionSolutionMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 异常解决办法 服务实现类
 * </p>
 *
 * @author zkhc
 * @since 2018-11-16
 */
@Service
public class ExceptionSolutionService extends ServiceImpl<ExceptionSolutionMapper, ExceptionSolution> {
    public void addSolution(ExceptionSolution exceptionSolution) {
        this.baseMapper.addSolution(exceptionSolution);
    }
}
