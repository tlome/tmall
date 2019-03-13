package com.how2java.tmall.service;

import com.how2java.tmall.pojo.User;

import java.util.List;

/**
 * Created by tl on 2018/10/16.
 */
public interface UserService {

    void add(User u);
    void delete(int id);
    void update(User u);
    User get(int id);
    List<User> list();
}
