package com.jt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.exception.ServiceException;
import com.jt.mapper.ItemCatMapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private ItemCatMapper itemCatMapper;

	@Autowired
	private ItemDescMapper itemDescMapper;




	@Override
	public EasyUITable findObjects(Integer page,Integer rows) {



		/*long rowsCount = itemMapper.selectCount(null);

		Integer startIndex = (page -1) * rows;
		List<Item> records = itemMapper.findPageObjects(startIndex,rows);*/

		QueryWrapper queryWrapper = new QueryWrapper();
		IPage<Item> iPage = new Page<>(page,rows);

		queryWrapper.orderByDesc("updated");
		//IPage iPage= itemMapper.selectPage(iPage,queryWrapper);
		IPage<Item> iPage1 = itemMapper.selectPage(iPage, queryWrapper);
		long rowsCount2 = itemMapper.selectCount(null);
		long rowsCount = iPage1.getTotal(); //根据分页工具动态获取
		List<Item> records = iPage1.getRecords();
		//System.out.println(rowsCount2+"==="+rowsCount);


		return new EasyUITable(rowsCount2,records);
	}

	/**
	 * 注意事项：完成数据库更新操作时，需要注意数据库事务问题
	 * @param item
	 * @return
	 */
	@Override
	@Transactional
	public int saveItem(Item item, ItemDesc itemDesc) {


		//保证入库时间一致
		item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
		int rows = itemMapper.insert(item);
		//mybatis plus在内部默认实现了主键自增后动态回显，可直接取值传参以保证itemId和主表的id一致
		itemDesc.setItemId(item.getId());
		itemDescMapper.insert(itemDesc);
		if(rows == 0)throw new ServiceException("保存失败");
		return rows;

	}

	@Override
	@Transactional
	public int updateItemById(Item item,ItemDesc itemDesc) {
		//item.setUpdated(new Date());
		int rows = itemMapper.updateById(item);

		itemDesc.setItemId(item.getId());
		itemDescMapper.updateById(itemDesc);
		if(rows == 0)throw new ServiceException("保存失败");
		return rows;

	}

	@Override
	public int deleteItem(Long ...ids) {
		//int rows = itemMapper.deleteById(ids);
		int rows = itemMapper.deleteBatchIds(Arrays.asList(ids));
		itemDescMapper.deleteBatchIds(Arrays.asList(ids));
		//int rows = itemMapper.deleteItemById(ids);
		if(rows==0)throw new ServiceException("记录可能已经不存在");
		return rows;
	}

	@Override
	public int updateItemStatus(Integer status,Long[] ids) {
		/*Item item = new Item();
		item.setStatus(2);
		UpdateWrapper updateWrapper = new UpdateWrapper();
		updateWrapper.eq("id",ids);
		int rows = itemMapper.update(item, updateWrapper);*/
		int rows = itemMapper.updateStatus(status, ids);
		if(rows == 0) throw  new ServiceException("更新上架状态失败");
		return rows;
	}

	@Override
	public int reshelfItems(Long[] ids) {
		Item item = new Item();
		item.setStatus(1);
		UpdateWrapper updateWrapper = new UpdateWrapper();
		updateWrapper.eq("id",ids);
		int rows = itemMapper.update(item, updateWrapper);
		return rows;
	}


	@Override
	public ItemDesc findItemDescById(Long itemId) {


		ItemDesc itemDesc = itemDescMapper.selectById(itemId);
		if(itemDesc.getItemDesc()==null)itemDesc.setItemDesc("暂无商品详情");
		return itemDesc;
	}
}
