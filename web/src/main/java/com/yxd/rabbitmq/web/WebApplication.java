package com.yxd.rabbitmq.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 李晓阳
 * @company 国药国华网络科技有限公司
 * @create 2018-05-15 9:19
 * @desc 启动类
 **/

@SpringBootApplication(scanBasePackages = "com.yxd.*")
@EnableSwagger2
@MapperScan({"com.yxd.baseservice.**", "com.yxd.rabbitmq.dao.**"})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
