package com.yxd.rabbitmq.model.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author 李晓阳
 * @company 国药国华网络科技有限公司
 * @create 2018-06-11 18:39
 * @desc MessageVoForWebSocket
 **/
@Data
public class MessageVoForWebSocket {

    /**
     *
     */
    private String id;
    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 消息类型编码
     */
    private String msgTypeCode;

    /**
     * 接收方用户Ids
     */
    private List<Map<String, Object>> orgIdAndRoleIdMapList;

}
