package com.gary.order.controller;

import com.gary.common.core.dto.AjaxResult;
import com.gary.common.security.annotation.Logical;
import com.gary.common.security.annotation.RequiresPermissions;
import com.gary.order.api.OrderApi;
import com.gary.order.dto.request.Order;
import com.gary.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname OrderController
 * @Description TODO
 * @Date 2022/1/27 16:08
 * @Auth gary
 */
@RestController
public class OrderController implements OrderApi {
    @Autowired
    OrderService orderService;

    @Override
    @RequiresPermissions(value = {"order:order"}, logical = Logical.AND)
    public AjaxResult order(Order order) {
        Boolean flag = orderService.order(order);
        if (flag){
            return AjaxResult.success("下单成功");
        }else {
            return AjaxResult.error("下单失败");
        }
    }

    @Override
    public AjaxResult test(){
        return AjaxResult.success();
    }
}
