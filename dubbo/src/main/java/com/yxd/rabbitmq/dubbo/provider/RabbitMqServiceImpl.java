package com.yxd.rabbitmq.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.yxd.rabbitmq.api.RabbitMqService;
import com.yxd.rabbitmq.constant.MessageConstant;
import com.yxd.rabbitmq.model.entity.SysMessage;
import com.yxd.rabbitmq.model.vo.MessageVoForSave;
import com.yxd.rabbitmq.model.vo.MessageVoForWebSocket;
import com.yxd.rabbitmq.sender.service.Sender;
import com.yxd.rabbitmq.service.SysMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author 李晓阳
 * @company 国药国华网络科技有限公司
 * @create 2018-05-15 9:19
 * @desc rabbitmq接口
 **/
@Service
@Slf4j
public class RabbitMqServiceImpl implements RabbitMqService {

    @Autowired
    private Sender sender;

    @Autowired
    private SysMessageService sysMessageService;

    @Override
    public void sendMessage(MessageVoForSave messageVoForSave) throws Exception {
        Predicate<List> pred = list -> list != null && list.size() > 0;
        Asserts.notEmpty(messageVoForSave.getRoutingName(), "routingName");
        Asserts.notEmpty(messageVoForSave.getMessageContent(), "messageContent");
        Asserts.notEmpty(messageVoForSave.getSendUserId(), "sendUserId");
        Asserts.notEmpty(messageVoForSave.getSendUserName(), "sendUserName");
        Asserts.notEmpty(messageVoForSave.getMsgTypeCode(), "msgTypeCode");
        Asserts.check(pred.test(messageVoForSave.getReceiveOrgIdList()), "receiveOrgIdList 不能为空");
        try {
            MessageVoForWebSocket messageVoForWebSocket = sysMessageService.insertSysMessage(messageVoForSave);
            if (!messageVoForSave.getMsgTypeCode().equalsIgnoreCase(MessageConstant.TYPE_BULLETIN)) {
                sender.send(messageVoForWebSocket, messageVoForSave.getRoutingName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送消息接口出错：" + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public SysMessage findMessage(String id) throws Exception {
        return sysMessageService.findSysMessageById(id);
    }

    @Override
    public List<SysMessage> findMessage() throws Exception {
        return sysMessageService.findSysMessageAll(null);
    }

    @Override
    public List<SysMessage> findMessage(SysMessage message) throws Exception {
        return sysMessageService.findSysMessageAll(message);
    }
}
