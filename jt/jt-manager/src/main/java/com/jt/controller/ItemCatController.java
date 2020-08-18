package com.jt.controller;

import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author huilong
 * @create 2020/8/3 9:55
 */
@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/queryItemName")
    public String findItemCat(long itemCatId) {


        return itemCatService.findItemCat(itemCatId).getName();
    }

    @RequestMapping("list")
    public List<EasyUITree> findCatList(Long id) {
        //String state = null;
        Long parentId = id==null?0:id;
        return itemCatService.findCatList(parentId);
        //return itemCatService.findItemCatCache(parentId);
    }
}
