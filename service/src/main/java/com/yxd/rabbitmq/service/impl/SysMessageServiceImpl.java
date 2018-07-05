package com.yxd.rabbitmq.service.impl;

import com.yxd.rabbitmq.constant.MessageConstant;
import com.yxd.rabbitmq.dao.mapper.SysMessageMapper;
import com.yxd.rabbitmq.dao.mapper.SysMessageReceiverMapper;
import com.yxd.rabbitmq.model.entity.SysMessage;
import com.yxd.rabbitmq.model.entity.SysMessageReceiver;
import com.yxd.rabbitmq.model.vo.MessageVoForSave;
import com.yxd.rabbitmq.model.vo.MessageVoForWebSocket;
import com.yxd.rabbitmq.service.SysMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Service Implementation:SysMessage
 *
 * @author 李晓阳
 * @date 2018-6-11
 */
@Service
@Slf4j
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class SysMessageServiceImpl implements SysMessageService {

    @Autowired
    private SysMessageMapper sysMessageMapper;

    @Autowired
    private SysMessageReceiverMapper sysMessageReceiverMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public MessageVoForWebSocket insertSysMessage(MessageVoForSave messageVoForSave) {
        MessageVoForWebSocket messageVoForWebSocket = new MessageVoForWebSocket();
        List<Map<String, Object>> orgAndRoleIdMapList = new ArrayList<>();
        //保存消息
        SysMessage sysMessage = new SysMessage();
        BeanUtils.copyProperties(messageVoForSave, sysMessage);
        sysMessageMapper.insert(sysMessage);
        SysMessageReceiver smr = new SysMessageReceiver();
        smr.setMessageId(sysMessage.getId());
        smr.setReadFlag(MessageConstant.UNREAD);
        messageVoForWebSocket.setId(sysMessage.getId());
        messageVoForWebSocket.setMsgTypeCode(messageVoForSave.getMsgTypeCode());
        messageVoForWebSocket.setMessageContent(messageVoForSave.getMessageContent());
        //保存消息与用户关系
        messageVoForSave.getReceiveOrgIdList().forEach(receiveOrgId -> {
                    //查询机构下订阅该类型的所有角色
                    List<String> roleIdList = sysMessageMapper.findRoleIds(messageVoForSave.getMsgTypeCode(), receiveOrgId);
                    Map<String, Object> map = new HashMap<>(2);
                    map.put("orgId", receiveOrgId);
                    map.put("roleIdList", roleIdList);
                    orgAndRoleIdMapList.add(map);
                    //保存消息关联关系
                    smr.setReceiveOrgId(receiveOrgId);
                    roleIdList.forEach(roleId -> {
                        smr.setReceiveRoleId(roleId);
                        sysMessageReceiverMapper.insert(smr);
                    });
                }
        );
        messageVoForWebSocket.setOrgIdAndRoleIdMapList(orgAndRoleIdMapList);
        return messageVoForWebSocket;
    }

    @Override
    public List<SysMessage> findMessageByOrgIdAndRoleIdsAndReadFlag(String orgId, String roleIds, String readFlag) {
        String[] roleIdArr = roleIds.split(",");
        return sysMessageMapper.findMessageByOrgIdAndRoleIdsAndReadFlag(orgId, roleIdArr, readFlag);
    }


    /**
     * 根据id查询
     *
     * @param id
     * @return void    返回类型
     * @Title: findSysMessageById
     * @date 2018-6-11
     * @user by 李晓阳
     */
    @Override
    public SysMessage findSysMessageById(String id) {
        return sysMessageMapper.findById(id);
    }


    /**
     * 查询全部
     *
     * @return List<SysMessage>    返回类型
     * @Title: findSysMessageAll
     * @date 2018-6-11
     * @user by 李晓阳
     */
    @Override
    public List<SysMessage> findSysMessageAll(SysMessage search) {
        return sysMessageMapper.findAll(search);
    }


}
