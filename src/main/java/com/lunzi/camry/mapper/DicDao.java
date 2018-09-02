package com.lunzi.camry.mapper;

import com.lunzi.camry.domain.Dic;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lunzi
 * @since 2018-06-02
 */
public interface DicDao extends BaseMapper<Dic> {
    List<Dic> selectByPrimaryKeyForUpdate(String keyCode);

}
