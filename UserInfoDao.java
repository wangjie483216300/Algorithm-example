package com.Mybatis.Dao;

import com.Mybatis.Model.UserInfo;

import java.util.List;

public interface UserInfoDao {
     void save(UserInfo user);      //添加用户
     void update(UserInfo user);    //更新用户
     void delete(UserInfo user);    //删除用户
     UserInfo findById(Integer id); //根据用户id查找用户数据
     List<UserInfo>  findAll();     //返回所有用户数据
     List<String> findname();     //返回所有用户数据
}
