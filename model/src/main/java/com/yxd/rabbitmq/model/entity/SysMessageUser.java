package com.yxd.rabbitmq.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * POJO:SysMessageUser
 *
 * @author 李晓阳
 * @date 2018-6-2
 */
@ApiModel(value = "SysMessageUser", description = "消息与用户关系表")
@Data
public class SysMessageUser implements Serializable {

    private static final long serialVersionUID = 7348058100005489473L;

    public SysMessageUser() {
    }

    public SysMessageUser(String messageId, String receiveUserId, String readFlag, String receiveOrgId) {
        this.messageId = messageId;
        this.receiveUserId = receiveUserId;
        this.readFlag = readFlag;
        this.receiveOrgId = receiveOrgId;
    }

    /**
     * 消息ID
     */
    @ApiModelProperty(value = "消息ID", name = "messageId")
    private String messageId;
    /**
     * 接收用户ID
     */
    @ApiModelProperty(value = "接收用户ID", name = "receiveUserId")
    private String receiveUserId;
    /**
     * 已读标识(0:未读，1:已读)
     */
    @ApiModelProperty(value = "已读标识(0:未读，1:已读)", name = "readFlag")
    private String readFlag;

    /**
     * 已读标识(0:未读，1:已读)
     */
    @ApiModelProperty(value = "接收组织ID", name = "receiveOrgId")
    private String receiveOrgId;
}
