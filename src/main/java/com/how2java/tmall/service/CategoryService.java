package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.util.Page;

import java.util.List;

/**
 * Created by tl on 2018/10/11.
 */
public interface CategoryService {

//    List<Category> list(Page page);
//
//    int total();

    List<Category> list();

    void add(Category category);

    void delete(int id);

    Category get(int id);

    void update(Category category);
}
