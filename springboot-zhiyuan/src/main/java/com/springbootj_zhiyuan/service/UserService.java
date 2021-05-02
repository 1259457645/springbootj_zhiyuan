package com.springbootj_zhiyuan.service;

import com.github.pagehelper.PageHelper;
import com.springbootj_zhiyuan.entity.User;
import com.springbootj_zhiyuan.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User findByAccount(User user){
        return userMapper.findByAccount(user.getAccount());
    }
    public User findUserById(String userId) {
        return userMapper.findUserById(userId);
    }
    public User findUserByAccount(String userAccount){return userMapper.findUserByAccount(userAccount);}
    public void insertUser(User user){ userMapper.insertUser(user.getAccount(),user.getUsername(),user.getPassword());}
    public int deleteUser(String id){
        return userMapper.deleteUser(id);
    }
    public int updatePw(String id, String password){
       return userMapper.updatePw(id,password);
    }

    public List<User> findByPage(int pageSize, int pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List<User> page = userMapper.findbyPage();
        return page;
    }
}
