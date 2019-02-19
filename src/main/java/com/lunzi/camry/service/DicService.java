package com.lunzi.camry.service;

import com.lunzi.camry.domain.Dic;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lunzi
 * @since 2018-06-02
 */
public interface DicService extends IService<Dic> {
    List<Dic> selectByPrimaryKeyForUpdate(String keyCode) throws Exception;

}
