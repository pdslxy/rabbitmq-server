package com.yxd.rabbitmq.dubbo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


/**
 * @author 李晓阳
 * @company 国药国华网络科技有限公司
 * @create 2018-05-15 9:19
 * @desc dubbo配置
 **/

@Configuration
@ImportResource({"classpath:dubbo/*.xml"})
public class PropertiesConfig {

}