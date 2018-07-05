package com.yxd.rabbitmq.service;

import com.yxd.rabbitmq.model.entity.SysMessageReceiver;

import java.util.List;

/**
 * Service Interface:SysMessageReceiver
 *
 * @author 李晓阳
 * @date 2018-6-11
 */
public interface SysMessageReceiverService {

    /**
     * 新增SysMessageReceiver
     *
     * @param entity
     * @return void    返回类型
     * @Title: saveSysMessageReceiver
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    void insertSysMessageReceiver(SysMessageReceiver entity);

    /**
     * 批量新增SysMessageReceiver
     *
     * @param list
     * @return void    返回类型
     * @Title: batchSaveSysMessageReceiver
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    void batchInsertSysMessageReceiver(List<SysMessageReceiver> list);

    /**
     * 编辑SysMessageReceiver
     *
     * @param entity
     * @return void    返回类型
     * @Title: updateSysMessageReceiver
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    void updateSysMessageReceiver(SysMessageReceiver entity);

    /**
     * 根据id删除SysMessageReceiver
     *
     * @param id
     * @return void    返回类型
     * @Title: deleteSysMessageReceiverById
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    void deleteSysMessageReceiverById(String id);

    /**
     * 根据id批量删除SysMessageReceiver
     *
     * @param ids
     * @return void    返回类型
     * @Title: deleteSysMessageReceiverByIds
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    void deleteSysMessageReceiverByIds(String ids);

    /**
     * 根据id查询
     *
     * @param id
     * @return void    返回类型
     * @Title: findSysMessageReceiverById
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    SysMessageReceiver findSysMessageReceiverById(String id);

    /**
     * 查询全部
     *
     * @param search
     * @return List<SysMessageReceiver>    返回类型
     * @Title: findSysMessageReceiverAll
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    List<SysMessageReceiver> findSysMessageReceiverAll(SysMessageReceiver search);

    /**
     * 根据角色id，组织Id更新消息状态
     *
     * @param messageId
     * @param roleIds
     * @param receiveOrgId
     */
    void updateReadFlagByMessageIdAndRoleIdsAndReceiveOrgId(String messageId, String roleIds, String receiveOrgId);
}