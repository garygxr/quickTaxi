package com.gary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname OrderApplication
 * @Description TODO
 * @Date 2022/1/27 16:16
 * @Auth gary
 */
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
        System.out.println("order server start success!!");
    }
}
