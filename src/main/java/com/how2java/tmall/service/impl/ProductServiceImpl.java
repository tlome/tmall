package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.ProductMapper;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.ProductExample;
import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tl on 2018/10/14.
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;

    @Override
    public void add(Product p) {
        productMapper.insert(p);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product p) {
        productMapper.updateByPrimaryKeySelective(p);
    }

    @Override
    public Product get(int id) {
        Product p = productMapper.selectByPrimaryKey(id);
        setCategory(p);
        return p;
    }

    public void setCategory(List<Product> ps,Category category){
        for (Product p : ps)
            p.setCategory(category);
    }
    public void setCategory(Product p){
        int cid = p.getCid();
        Category c = categoryService.get(cid);
        p.setCategory(c);
    }

    @Override
    public List list(int cid) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id asc");
        List ps = productMapper.selectByExample(example);

        Category category = categoryService.get(cid);
        setCategory(ps,category);

        return ps;
    }

    @Override
    public void setFirstProductImage(Product p) {
        List<ProductImage> pis = productImageService.list(p.getId(),ProductImageService.type_single);
        if(!pis.isEmpty()){
            ProductImage pi = pis.get(0);
            p.setFirstProductImage(pi);
        }
    }
    @Override
    public void setFirstProductImage(List<Product> ps){
        for(Product p : ps){
            setFirstProductImage(p);
        }
    }

    @Override
    public void fill(List<Category> cs) {
        for(Category c : cs){
            fill(c);
        }
    }

    @Override
    public void fill(Category c) {
        List<Product> ps = list(c.getId());
       c.setProducts(ps);
    }

    @Override
    public void fillByRow(List<Category> cs) {
        int productNumberEachRow = 8;
        for(Category c : cs){
            List<Product> ps = c.getProducts();
            List<List<Product>> pByRow = new ArrayList<>();

        }
    }
}
