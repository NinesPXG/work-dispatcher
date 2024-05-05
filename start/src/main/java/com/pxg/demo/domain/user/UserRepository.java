package com.pxg.demo.domain.user;

public interface UserRepository {

    boolean create(UserInfo userInfo);

    boolean update(UserInfo userInfo);

    boolean remove(String userId);

    UserInfo findById(String userId);

}
