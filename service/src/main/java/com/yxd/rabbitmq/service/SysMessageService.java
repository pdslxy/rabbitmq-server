package com.yxd.rabbitmq.service;

import com.yxd.rabbitmq.model.entity.SysMessage;
import com.yxd.rabbitmq.model.vo.MessageVoForSave;
import com.yxd.rabbitmq.model.vo.MessageVoForWebSocket;

import java.util.List;

/**
 * Service Interface:SysMessage
 *
 * @author 李晓阳
 * @date 2018-6-11
 */
public interface SysMessageService {

    /**
     * 根据id查询
     *
     * @param id
     * @return void    返回类型
     * @Title: findSysMessageById
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    SysMessage findSysMessageById(String id);

    /**
     * 查询全部
     *
     * @param search
     * @return List<SysMessage>    返回类型
     * @Title: findSysMessageAll
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    List<SysMessage> findSysMessageAll(SysMessage search);

    /**
     * 保存消息
     *
     * @param messageVoForSave
     * @return
     */
    MessageVoForWebSocket insertSysMessage(MessageVoForSave messageVoForSave);

    /**
     * 根据角色Id与组织Id
     *
     * @param orgId
     * @param roleIds
     * @param readFlag
     * @return
     */
    List<SysMessage> findMessageByOrgIdAndRoleIdsAndReadFlag(String orgId, String roleIds, String readFlag);
}