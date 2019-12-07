package com.zc.quickstart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangchi
 * @date 2019/12/4
 */
@SpringBootApplication
@MapperScan("com.zc.quickstart.mapper")
public class QuickStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuickStartApplication.class, args);
    }
}
