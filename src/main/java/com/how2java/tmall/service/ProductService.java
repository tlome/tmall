package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductImage;

import java.util.List;

/**
 * Created by tl on 2018/10/14.
 */
public interface ProductService {
    void add(Product p);
    void delete(int id);
    void update(Product p);
    Product get(int id);
    List list(int cid);
    void setFirstProductImage(Product p);
    void setFirstProductImage(List<Product> ps);

    void fill(List<Category> cs);
    void fill(Category c);
    void fillByRow(List<Category> cs);
}
