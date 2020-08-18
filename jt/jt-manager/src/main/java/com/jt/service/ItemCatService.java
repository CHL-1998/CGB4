package com.jt.service;

import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;

import java.util.List;

/**
 * @Author huilong
 * @create 2020/8/3 10:26
 */
public interface ItemCatService {
    ItemCat findItemCat(long itemCatId);

    List<EasyUITree> findCatList(Long parentId);

    List<EasyUITree> findItemCatCache(Long parentId);
}
