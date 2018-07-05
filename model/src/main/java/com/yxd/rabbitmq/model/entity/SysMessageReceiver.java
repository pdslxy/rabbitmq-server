package com.yxd.rabbitmq.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * POJO:SysMessageReceiver
 * 
 * @author 李晓阳
 * @date 2018-6-11
 */
 @ApiModel(value = "SysMessageReceiver", description = "xxx对象")
@Data
public class SysMessageReceiver {
	
	 	/**
		 *  消息ID
		 */
		@ApiModelProperty(value = "消息ID", name = "messageId")
		private String	messageId;		
	 	/**
		 *  接收用户ID
		 */
		@ApiModelProperty(value = "接收用户ID", name = "receiveUserId")
		private String	receiveUserId;		
	 	/**
		 *  接收机构ID
		 */
		@ApiModelProperty(value = "接收机构ID", name = "receiveOrgId")
		private String	receiveOrgId;		
	 	/**
		 *  接收角色ID
		 */
		@ApiModelProperty(value = "接收角色ID", name = "receiveRoleId")
		private String	receiveRoleId;		
	 	/**
		 *  已读标识(0:未读，1:已读)
		 */
		@ApiModelProperty(value = "已读标识(0:未读，1:已读)", name = "readFlag")
		private String	readFlag;		

}
