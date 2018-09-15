package com.lunzi.camry.mapper;

import com.lunzi.camry.domain.MethodLock;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lunzi
 * @since 2018-09-12
 */
public interface MethodLockDao extends BaseMapper<MethodLock> {
    MethodLock selectLockForUpdate(String lockName);
}
