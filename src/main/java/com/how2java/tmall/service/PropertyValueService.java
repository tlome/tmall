package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.PropertyValue;

import java.util.List;

/**
 * Created by tl on 2018/10/15.
 */
public interface PropertyValueService {

    void init(Product product);
    void update(PropertyValue propertyValue);

    PropertyValue get(int ptid,int pid);
    List<PropertyValue> list(int pid);
}
