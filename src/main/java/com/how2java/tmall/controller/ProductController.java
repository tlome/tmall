package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.util.Page;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * Created by tl on 2018/10/15.
 */
@Controller
public class ProductController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    private int start;

    @RequestMapping("admin_product_list")
    public String list(int cid, Model model, Page page){
        //记录当前的start删除的时候会用到
        start=page.getStart();

        Category c = categoryService.get(cid);

        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product> ps = productService.list(cid);
        productService.setFirstProductImage(ps);

        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+cid);

        model.addAttribute("c",c);
        model.addAttribute("page",page);
        model.addAttribute("ps",ps);

        return "admin/listProduct";
    }

    @RequestMapping("admin_product_add")
    public String add(Product p){
        p.setCreateDate(new Date());
        productService.add(p);
        return "redirect:admin_product_list?cid="+p.getCid();
    }

    @RequestMapping("admin_product_delete")
    public String delete(int id){
        int cid = productService.get(id).getCid();
        productService.delete(id);
        return "redirect:admin_product_list?start="+start+"&cid="+cid;
    }

    @RequestMapping("admin_product_edit")
    public String edit(Model model,int id){
        Product p = productService.get(id);
        model.addAttribute("p",p);
        return "admin/editproduct";
    }

    @RequestMapping("admin_product_update")
    public String update(Product p){
        productService.update(p);
        return "redirect:admin_product_list?cid="+p.getCid();
    }
}
