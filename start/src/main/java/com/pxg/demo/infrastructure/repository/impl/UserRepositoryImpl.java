package com.pxg.demo.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxg.demo.domain.user.UserInfo;
import com.pxg.demo.domain.user.UserRepository;
import com.pxg.demo.infrastructure.repository.convertor.UserInfoConvertor;
import com.pxg.demo.infrastructure.repository.mapper.UserInfoMapper;
import com.pxg.demo.infrastructure.repository.po.UserInfoPO;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends ServiceImpl<UserInfoMapper, UserInfoPO> implements UserRepository {

    @Override
    public boolean create(UserInfo userInfo) {
        UserInfoPO po = UserInfoConvertor.INSTANCE.do2Po(userInfo);
        return save(po);
    }

    @Override
    public boolean update(UserInfo userInfo) {
        UserInfoPO po = UserInfoConvertor.INSTANCE.do2Po(userInfo);
        return updateById(po);
    }

    @Override
    public boolean remove(String userId) {
        return removeById(userId);
    }

    @Override
    public UserInfo findById(String userId) {
        return UserInfoConvertor.INSTANCE.po2Do(getById(userId));
    }

}
