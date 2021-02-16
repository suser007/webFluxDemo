package com.example.webflux1.controller;


import com.example.webflux1.domian.User;
import com.example.webflux1.handler.UserHandler;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserHandler userHandler;

    @GetMapping("/findUserById")
    public Mono<User> findUserById(@RequestParam Integer id){
        return userHandler.findUserById(id);
    }

    @GetMapping("/findAllUser")
    public Flux<User> findAllUser(){
        return userHandler.findAllUser();
    }

    @PostMapping("/insertUser")
    public Mono<Integer> saveUser(@RequestBody User user){
        return userHandler.save(user);
    }

    @PutMapping("/modifyUser")
    public Mono<Integer> modifyUser(@RequestBody User user){
        return userHandler.modifyCity(user);
    }

    @DeleteMapping("/deleteUserById")
    public Mono<Integer> deleteUserById(@RequestParam Integer id){
        return userHandler.deleteUserById(id);
    }
}
