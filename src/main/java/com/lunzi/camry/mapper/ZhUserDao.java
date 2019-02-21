package com.lunzi.camry.mapper;

import com.lunzi.camry.domain.ZhUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lunzi
 * @since 2019-02-21
 */
public interface ZhUserDao extends BaseMapper<ZhUser> {


    Integer  batchInsert(List<ZhUser> zhUserList);
}
