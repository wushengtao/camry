package com.lunzi.camry.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lunzi.camry.domain.ZhUser;
import com.lunzi.camry.mapper.ZhUserDao;
import com.lunzi.camry.service.ZhUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lunzi
 * @since 2019-02-21
 */
@Service
public class ZhUserServiceImpl extends ServiceImpl<ZhUserDao, ZhUser> implements ZhUserService {

    @Override
    public int getlastId() {
        EntityWrapper<ZhUser> entityWrapper=new EntityWrapper();
        entityWrapper.orderBy("id",false);
        entityWrapper.last("limit 1");
        return this.selectList(entityWrapper).get(0).getId();
    }
}
