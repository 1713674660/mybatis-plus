package com.zc.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangchi
 * @date 2019/12/11
 */
@SpringBootApplication
@MapperScan({"com.zc.generator.*.mapper", "com.zc.generator.*.**.mapper"})
public class GeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
    }
}
