package com.gary.user.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.gary.common.core.constant.HttpStatus;
import com.gary.common.core.dto.AjaxResult;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Classname UserBlockExceptionHanlder
 * @Description 统一限流降级处理
 * @Date 2022/1/27 14:56
 * @Auth gary
 */
@Component
public class UserBlockExceptionHanlder implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        response.setStatus(HttpStatus.SUCCESS );
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Content-Type","application/json;charset=utf-8");
        AjaxResult error = AjaxResult.error(HttpStatus.FLOW_LIMITING, "系统繁忙，请稍后重试...");
        String s = JSONObject.toJSONString(error);
        PrintWriter writer = response.getWriter();
        writer.write(s);
        writer.flush();
        writer.close();
    }
}
