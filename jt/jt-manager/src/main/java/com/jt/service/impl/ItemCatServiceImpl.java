package com.jt.service.impl;

import com.jt.annotation.CacheFind;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author huilong
 * @create 2020/8/3 10:26
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Autowired(required = false)  //告知spring容器该注入不是必需的，如果该对象被程序调用时再注入
    private Jedis jedis;

    @Override
    @CacheFind(key="ITEM_CAT_NAME")
    public ItemCat findItemCat(long itemCatId) {
        return itemCatMapper.selectById(itemCatId);

    }

    @Override
    @CacheFind(key="ITEM_CAT_LIST")
    public List<EasyUITree> findCatList(Long parentId) {
        List<ItemCat> catList = itemCatMapper.findParentCat(parentId);
        List<EasyUITree> list = new ArrayList<>();
        for (ItemCat itemCat : catList) {

            /*if(itemCat.getIsParent()){
                state = "closed";
            }else{
                state = "open";
            }*/
            String state = itemCat.getIsParent() ? "closed" : "open";
            EasyUITree easyUITree = new EasyUITree(itemCat.getId(), itemCat.getName(), state);

            list.add(easyUITree);
        }
        return list;


    }

    @Override
    public List<EasyUITree> findItemCatCache(Long parentId) {
        //key
        String key = "ITEM_CAT_LIST::" + parentId;
        List<EasyUITree> treeList = null;

        if (jedis.exists(key)) {


            //从缓存中取数据
            treeList = ObjectMapperUtil.toObject(jedis.get(key), List.class);

        } else {
            treeList = findCatList(parentId);
            //转成json串并添加缓存
            String json = ObjectMapperUtil.toJson(treeList);
            jedis.set(key, json);
        }
        return treeList;
    }


}
