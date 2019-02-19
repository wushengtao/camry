package com.lunzi.camry.mapper;

import com.lunzi.camry.domain.Exe;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lunzi
 * @since 2018-10-14
 */
public interface ExeDao extends BaseMapper<Exe> {
     Integer testUpdate(Long value,Integer newVersion,Long id,Integer version);

}
