package com.lunzi.camry.service;

import com.lunzi.camry.domain.ZhUser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lunzi
 * @since 2019-02-21
 */
public interface ZhUserService extends IService<ZhUser> {

    int getlastId();



}
