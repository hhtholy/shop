package com.mini.controller;

import com.mini.entity.Property;
import com.mini.service.PropertyService;
import com.mini.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hht
 * @create 2019-01-15 15:09
 *
 * 和属性相关的控制层
 */
@RestController
public class PropertyController {


    //注入Service
    @Autowired
    private PropertyService propertyService;

    /**
     * 分页查询一个分类下的属性
     * @param currentPage
     * @param size
     * @return
     */
        @GetMapping("/categories/{cid}/properties")
         public Page<Property> getProperties(@PathVariable("cid") Integer cid,@RequestParam(value = "currentPage", defaultValue="0") Integer currentPage,
                                             @RequestParam(value = "size", defaultValue="4") Integer size){

             return propertyService.getPropertyPage(cid,currentPage,size,5);
        }


    /**
     * 添加 属性
     * @param property
     * @return
     */
    @PostMapping("/properties")
        public Property addProperty(@RequestBody Property property){

        propertyService.addProperty(property);

        return property;
        }

    /**
     * 根据id删除 属性
     * @param id
     * @return
     */
    @DeleteMapping("/properties/{id}")
    public String deleteProperty(@PathVariable("id") Integer id){

        String result = propertyService.deleteProperty(id);
        return result;
    }

    /**
     * 根据 id去 查询属性
     * @param id
     * @return
     */
    @GetMapping("/properties/{id}")
    public Property getProperty(@PathVariable("id") Integer id){
          return propertyService.getProperty(id);
    }


    /**
     * 更新属性
     * @param property
     * @return
     */

    @PutMapping("/properties/{id}")
    public Property updateProperty(@PathVariable("id") Integer id,@RequestBody Property property){

        return propertyService.updateProperty(property);
    }



}
