package com.example.sjsystem.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sjsystem.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public interface UserService extends IService<User> {
    void reg(User user);

    User login(String name, String password);
}
