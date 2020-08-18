package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;

public interface ItemService {

    //int getRows();

    EasyUITable findObjects(Integer page, Integer rows);


    int saveItem(Item item, ItemDesc itemDesc);


    int updateItemById(Item item,ItemDesc itemDesc);

    int deleteItem(Long ...ids);

    int updateItemStatus(Integer status,Long[] ids);

    int reshelfItems(Long[] ids);

    ItemDesc findItemDescById(Long itemId);
}
