package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUITable;
import com.jt.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;


	@RequestMapping("/query")
	public EasyUITable findObjects(Integer page,Integer rows){

		return itemService.findObjects(page,rows);


	}

	@RequestMapping("/save")
	public JsonResult saveItem(Item item,ItemDesc itemDesc){
		itemService.saveItem(item,itemDesc);

		return new JsonResult("save ok");
	}

	@RequestMapping("/update")
	public JsonResult updateItem(Item item,ItemDesc itemDesc){

		itemService.updateItemById(item,itemDesc);
		return new JsonResult("update ok");
	}

	@RequestMapping("/delete")
	public JsonResult deleteItems(Long...ids){

		itemService.deleteItem(ids);
		return new JsonResult("delete ok");
	}


	@RequestMapping("/instock")
	public JsonResult instockItems(Long ...ids){
		itemService.updateItemStatus(2,ids);
		return new JsonResult("instock ok");
	}
	@RequestMapping("/reshelf")
	public JsonResult reshelfItems(Long ...ids){
		itemService.updateItemStatus(1,ids);
		return new JsonResult("reshelf ok");
	}

	@RequestMapping("/{status}")
	public JsonResult instockItems1(@PathVariable Integer status, Long ...ids){
		itemService.updateItemStatus(status,ids);
		return new JsonResult("instock ok");
	}

	/*@PostMapping("/instockOrReshelf")
	public JsonResult reshelfItems1(Long ...ids){
		itemService.updateItemStatus(1,ids);
		return new JsonResult("reshelf ok");
	}*/

	@RequestMapping("/query/item/desc/{itemId}")
	public JsonResult findItemDescById(@PathVariable Long itemId){
		return new JsonResult(itemService.findItemDescById(itemId));
	}
	
	
	
}
