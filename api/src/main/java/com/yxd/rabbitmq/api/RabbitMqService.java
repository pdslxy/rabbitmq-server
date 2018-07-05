package com.yxd.rabbitmq.api;

import com.yxd.rabbitmq.model.entity.SysMessage;
import com.yxd.rabbitmq.model.vo.MessageVoForSave;

import java.util.List;

/**
 * @author 李晓阳
 * @company 国药国华网络科技有限公司
 * @create 2018-06-01 10:27
 * @desc 消息服务api接口
 **/
public interface RabbitMqService {

    /**
     * 发送消息
     *
     * @param messageVoForSave
     * @throws Exception
     */
    void sendMessage(MessageVoForSave messageVoForSave) throws Exception;


    /**
     * 根据id查找消息
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysMessage findMessage(String id) throws Exception;

    /**
     * 查找所有消息
     *
     * @return
     * @throws Exception
     */
    List<SysMessage> findMessage() throws Exception;


    /**
     * 根据条件查找消息
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<SysMessage> findMessage(SysMessage message) throws Exception;

}
