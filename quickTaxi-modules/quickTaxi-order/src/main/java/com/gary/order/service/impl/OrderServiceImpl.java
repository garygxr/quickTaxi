package com.gary.order.service.impl;

import com.gary.order.dto.request.Order;
import com.gary.order.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @Classname OrderServiceImpl
 * @Description TODO
 * @Date 2022/1/27 16:12
 * @Auth gary
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Boolean order(Order order){
        return true;
    }
}
