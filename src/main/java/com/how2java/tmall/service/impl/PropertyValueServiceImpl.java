package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.PropertyValueMapper;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.pojo.PropertyValue;
import com.how2java.tmall.pojo.PropertyValueExample;
import com.how2java.tmall.service.PropertyService;
import com.how2java.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tl on 2018/10/15.
 */
@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Autowired
    PropertyService propertyService;
    @Autowired
    PropertyValueMapper propertyValueMapper;

    @Override
    //为product初始化各种属性，单并没有赋值（例如插入了颜色属性，但没有赋值）
    public void init(Product product) {
        List<Property> pts = propertyService.list(product.getCid());
        for(Property pt : pts){
            PropertyValue pv = get(pt.getId(),product.getId());
            if(null==pv){
                pv = new PropertyValue();
                pv.setPid(product.getId());
                pv.setPtid(pt.getId());
                propertyValueMapper.insert(pv);
            }
        }
    }

    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
    }

    @Override
    public PropertyValue get(int ptid, int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPtidEqualTo(ptid)
                                .andPidEqualTo(pid);
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        if(pvs.isEmpty()){
            return null;
        }
        return pvs.get(0);
    }

    @Override
    public List<PropertyValue> list(int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> result = propertyValueMapper.selectByExample(example);

        for(PropertyValue pv : result){
            Property property = propertyService.get(pv.getPtid());
            pv.setProperty(property);
        }

        return result;
    }
}
