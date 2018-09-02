package com.lunzi.camry.service.impl;

import com.lunzi.camry.domain.User;
import com.lunzi.camry.mapper.UserDao;
import com.lunzi.camry.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lunzi
 * @since 2018-05-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
