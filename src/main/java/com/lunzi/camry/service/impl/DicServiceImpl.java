package com.lunzi.camry.service.impl;

import com.lunzi.camry.domain.Dic;
import com.lunzi.camry.mapper.DicDao;
import com.lunzi.camry.service.DicService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lunzi
 * @since 2018-06-02
 */
@Service
public class DicServiceImpl extends ServiceImpl<DicDao, Dic> implements DicService {
    @Autowired
    DicDao dicDao;

    @Override
    public List<Dic> selectByPrimaryKeyForUpdate(String keyCode) throws Exception{
        return dicDao.selectByPrimaryKeyForUpdate(keyCode);
    }
}
