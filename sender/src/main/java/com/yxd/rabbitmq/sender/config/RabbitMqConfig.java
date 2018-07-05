package com.yxd.rabbitmq.sender.config;

import com.yxd.rabbitmq.constant.RabbitConstant;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李晓阳
 * @company 国药国华网络科技有限公司
 * @create 2018-04-26 15:05
 * @desc rabbitmq配置
 **/
@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.queuename}")
    private String queueName;

    /**
     * 声明队列
     *
     * @return
     */
    @Bean
    public Queue queueSysMessage() {
        // true表示持久化该队列
        return new Queue(queueName, true);
    }

    /**
     * 声明交换机
     *
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitConstant.EXCHANGE);
    }

    /**
     * 队列绑定到交换机
     *
     * @return
     */
    @Bean
    public Binding bindingContract() {
        return BindingBuilder.bind(queueSysMessage()).to(fanoutExchange());
    }

}