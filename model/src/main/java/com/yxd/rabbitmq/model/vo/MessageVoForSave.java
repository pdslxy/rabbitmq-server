package com.yxd.rabbitmq.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @author 李晓阳
 * @company 国药国华网络科技有限公司
 * @create 2018-04-17 14:41
 * @desc 消息保存VO
 **/
@ApiModel(value = "MessageVoForSave", description = "消息保存VO")
@Data
public class MessageVoForSave implements Serializable {

    private static final long serialVersionUID = -3403794520837175944L;
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容", name = "messageContent")
    private String messageContent;

    /**
     * 消息队列路由键
     */
    @ApiModelProperty(value = "消息队列路由键(参考EnumBusSys枚举类)", name = "routingName")
    private String routingName;

    /**
     * 发送方用户名
     */
    @ApiModelProperty(value = "消息类型编码", name = "msgTypeCode")
    private String msgTypeCode;


    @ApiModelProperty(value = "消息备注", name = "remark")
    private String remark;


    /**
     * 接收方机构Id集合
     */
    @ApiModelProperty(value = "接收方机构Id集合", name = "receiveOrgIdList")
    private List<String> receiveOrgIdList;

    /**
     * 发送用户Ids
     */
    @ApiModelProperty(value = "发送用户Id", name = "sendUserId")
    private String sendUserId;

    /**
     * 发送方用户名
     */
    @ApiModelProperty(value = "发送方用户名", name = "sendUserName")
    private String sendUserName;


}
