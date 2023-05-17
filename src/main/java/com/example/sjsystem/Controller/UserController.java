package com.example.sjsystem.Controller;


import com.example.sjsystem.Service.UserService;
import com.example.sjsystem.pojo.User;
import com.example.sjsystem.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public Result reg(@RequestBody User user){
        userService.reg(user);
        return Result.ok();
    }


    @PostMapping("/login")
    public Result login(String name,String password){
        User login=userService.login(name,password);
        return Result.ok(login);
    }


}
