package com.mfanw.element;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * JWT 测试
 *
 * @author mengwei
 */
@CrossOrigin
@MapperScan("com.mfanw.element.dao.mapper")
@SpringBootApplication
public class ElementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElementApplication.class, args);
    }

}