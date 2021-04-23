package com.zyx.controller;

import com.zyx.entity.user.User;
import com.zyx.repository.UserRepository;
import com.zyx.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get-user")
    public Result getUser(){
        List<User> all = userRepository.findAll();
        return Result.dataOf(all);
    }

    @GetMapping("/one-user")
    public Result oneUser(@RequestParam(value = "userId") String userId){
        User user = userRepository.findByUserId(userId);
        return Result.dataOf(user);
    }

    @GetMapping("/select-user")
    public Result selectUser(){
        User user = userRepository.getUser();
        return Result.dataOf(user);
    }

    @GetMapping("page-user")
    public Result pageUser(Pageable pageable){
        Page all = userRepository.findAll(pageable);
        return Result.pageOf(all);

    }

    @GetMapping("page2-user")
    public Result page2User(Pageable pageable){
        Page all = userRepository.findAll("", pageable);
        return Result.pageOf(all);

    }
    @GetMapping("/test")
    public Result test(){
        return Result.dataOf("Hello World");
    }
}
