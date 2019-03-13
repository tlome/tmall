package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;

import java.util.List;


/**
 * Created by tl on 2018/10/16.
 */
public interface OrderItemService {
    void add(OrderItem oi);
    void delete(int id);
    OrderItem get(int id);
    void update(OrderItem oi);

    void fill(List<Order> os);

    void fill(Order o);

}
