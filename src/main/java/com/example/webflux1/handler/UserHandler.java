package com.example.webflux1.handler;

import com.example.webflux1.domian.User;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.relational.core.query.Update;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

import static org.springframework.data.relational.core.query.Criteria.where;


/**
 * 处理器类
 * Mono：实现发布者，并返回 0 或 1 个元素，即单对象。
 * Flux：实现发布者，并返回 N 个元素，即 List 列表对象。
 */
@Component("userHandler")
public class UserHandler {

    @Resource
    private DatabaseClient databaseClient;

    public Mono<Integer> save(User user){
        return databaseClient
                .insert()
                .into(User.class)
                .using(user)
                .fetch()
                .rowsUpdated();
    }

    public Flux<User> findAllUser(){
        return databaseClient
                .select()
                .from("user")
                .as(User.class)
                .fetch()
                .all();
    }

    public Mono<User> findUserById(Integer id){
        return databaseClient
                .select()
                .from("user")
                .matching(where("id").is(id))
                .as(User.class)
                .fetch()
                .one();
    }

    public Mono<Integer> deleteUserById(Integer id){
        return databaseClient
                .delete()
                .from("user")
                .matching(where("id").is(id))
                .fetch()
                .rowsUpdated();
    }

    public Mono<Integer> modifyCity(User user){
        return databaseClient
                .update()
                .table("user")
                .using(Update.update("username", user.getUsername())
                        .set("password", user.getPassword())
                        .set("flag", user.getFlag()))
                .matching(where("id").is(user.getId()))
                .fetch()
                .rowsUpdated();
    }
}
