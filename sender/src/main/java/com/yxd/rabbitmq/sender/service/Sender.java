package com.yxd.rabbitmq.sender.service;

import com.alibaba.fastjson.JSONObject;
import com.yxd.rabbitmq.constant.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author 李晓阳
 * @company 国药国华网络科技有限公司
 * @create 2018-04-26 15:05
 * @desc
 **/
@Component
@Slf4j
public class Sender implements RabbitTemplate.ConfirmCallback, ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("消息发送:" + correlationData);
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("消息发送失败:" + new String(message.getBody()));
    }

    /**
     * 发送消息，不需要实现任何接口，供外部调用
     *
     * @param object
     */
    public void send(Object object,String routingKey) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConstant.EXCHANGE, routingKey, JSONObject.toJSON(object).toString(), correlationId);
    }
}