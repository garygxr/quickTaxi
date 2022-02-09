package com.gary.order.api;

import com.gary.common.core.dto.AjaxResult;
import com.gary.order.dto.request.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname OrderController
 * @Description TODO
 * @Date 2022/1/27 16:05
 * @Auth gary
 */
@RequestMapping("/order")
public interface OrderApi {
    @PostMapping("/order")
    public AjaxResult order(@RequestBody Order order);

    @GetMapping("/test")
    AjaxResult test();
}
