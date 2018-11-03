package com.leyou.item.web;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.pojo.item;
import com.leyou.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: FuJiaCheng
 * @Date: Create In 19:30 2018/11/2
 * @Modified:
 * @annotation:
 */

@RestController
@RequestMapping("item")
public class ItemController {
    
    @Autowired
    private ItemService itemService;
    
    @PostMapping
    public ResponseEntity<item> saveItem(item item) {
        // 校验一下价格
        if (item.getPrice() == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            throw new LyException(ExceptionEnum.PRICE_CANNOT_BE_NULL);
        }
        com.leyou.item.pojo.item item1 = itemService.saveItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item1);
    }
    
}