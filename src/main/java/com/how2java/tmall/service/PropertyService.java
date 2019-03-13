package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Property;

import java.util.List;

/**
 * Created by tl on 2018/10/13.
 */
public interface PropertyService {
    void add(Property c);
    void delete(int id);
    void update(Property c);
    Property get(int id);
    List list(int cid);
}
