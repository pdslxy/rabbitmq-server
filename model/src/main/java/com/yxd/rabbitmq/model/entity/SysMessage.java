package com.yxd.rabbitmq.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * POJO:SysMessage
 * 
 * @author 李晓阳
 * @date 2018-6-11
 */
 @ApiModel(value = "SysMessage", description = "xxx对象")
@Data
public class SysMessage {
	
	 	/**
		 *  ID
		 */
		@ApiModelProperty(value = "ID", name = "id")
		private String	id;		
	 	/**
		 *  消息类型ID
		 */
		@ApiModelProperty(value = "消息类型Code", name = "msgTypeCode")
		private String	msgTypeCode;		
	 	/**
		 *  消息内容
		 */
		@ApiModelProperty(value = "消息内容", name = "messageContent")
		private String	messageContent;		
	 	/**
		 *  创建时间
		 */
		@ApiModelProperty(value = "创建时间", name = "createDate")
		private Date	createDate;		
	 	/**
		 *  发送用户Id
		 */
		@ApiModelProperty(value = "发送用户Id", name = "sendUserId")
		private String	sendUserId;		
	 	/**
		 *  发送方用户名
		 */
		@ApiModelProperty(value = "发送方用户名", name = "sendUserName")
		private String	sendUserName;		
	 	/**
		 *  消息备注
		 */
		@ApiModelProperty(value = "消息备注", name = "remark")
		private String	remark;

}
