package com.example.sjsystem.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sjsystem.Mapper.UserMapper;
import com.example.sjsystem.Service.UserService;
import com.example.sjsystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User  user){

        userMapper.insert(user);

    }


    @Override
    public User login(String name,String password){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",name);
        queryWrapper.eq("password",password);

        return userMapper.selectOne(queryWrapper);
    }

}
