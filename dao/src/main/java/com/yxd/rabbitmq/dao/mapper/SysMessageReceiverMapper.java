package com.yxd.rabbitmq.dao.mapper;

import com.yxd.rabbitmq.model.entity.SysMessageReceiver;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Service Interface:Users
 *
 * @author 李晓阳
 * @date 2018-6-11
 */
@Component
@Mapper
public interface SysMessageReceiverMapper {
    /**
     * 新增SysMessageReceiver
     *
     * @param entity
     * @return void    返回类型
     * @Title: saveSysMessageReceiver
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    void insert(SysMessageReceiver entity);

    /**
     * 批量新增SysMessageReceiver
     *
     * @param list
     * @return void    返回类型
     * @Title: batchSaveSysMessageReceiver
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    void batchInsert(List<SysMessageReceiver> list);

    /**
     * 编辑SysMessageReceiver
     *
     * @param entity
     * @return void    返回类型
     * @Title: updateSysMessageReceiver
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    void update(SysMessageReceiver entity);

    /**
     * 根据id删除SysMessageReceiver
     *
     * @param id
     * @param id
     * @return void    返回类型
     * @Title: deleteById
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    void deleteById(String id);

    /**
     * 根据id批量删除SysMessageReceiver
     *
     * @param ids
     * @param ids
     * @return void    返回类型
     * @Title: deleteSysMessageReceiverByIds
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    void deleteByIds(String[] ids);

    /**
     * 根据id查询
     *
     * @param id
     * @param id
     * @return void    返回类型
     * @Title: findSysMessageReceiverById
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    SysMessageReceiver findById(String id);

    /**
     * 查询全部
     *
     * @param search
     * @return List<SysMessageReceiver>    返回类型
     * @Title: findSysMessageReceiverAll
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    List<SysMessageReceiver> findAll(SysMessageReceiver search);

    /**
     * 根据角色id，组织Id更新消息状态
     *
     * @param messageId
     * @param roleIdArr
     * @param receiveOrgId
     */
    void updateReadFlag(@Param("messageId") String messageId, @Param("roleIdArr") String[] roleIdArr, @Param("receiveOrgId") String receiveOrgId);
}