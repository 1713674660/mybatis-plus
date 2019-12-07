package com.zc.wrapper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangchi
 * @date 2019/12/4
 */
@SpringBootApplication
@MapperScan("com.zc.wrapper.mapper")
public class WrapperApplication {
    public static void main(String[] args) {
        SpringApplication.run(WrapperApplication.class, args);
    }
}
