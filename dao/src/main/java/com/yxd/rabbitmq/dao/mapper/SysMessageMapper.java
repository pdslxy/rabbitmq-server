package com.yxd.rabbitmq.dao.mapper;

import com.yxd.rabbitmq.model.entity.SysMessage;
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
public interface SysMessageMapper {
    /**
     * 新增SysMessage
     *
     * @param entity
     * @return void    返回类型
     * @Title: saveSysMessage
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    void insert(SysMessage entity);

    /**
     * 编辑SysMessage
     *
     * @param entity
     * @return void    返回类型
     * @Title: updateSysMessage
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    void update(SysMessage entity);

    /**
     * 根据id删除SysMessage
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
     * 根据id批量删除SysMessage
     *
     * @param ids
     * @param ids
     * @return void    返回类型
     * @Title: deleteSysMessageByIds
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
     * @Title: findSysMessageById
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    SysMessage findById(String id);

    /**
     * 查询全部
     *
     * @param search
     * @return List<SysMessage>    返回类型
     * @Title: findSysMessageAll
     * @date 2018-6-11
     * @user by lixiaoyang
     */
    List<SysMessage> findAll(SysMessage search);

    /**
     * 查询订阅的角色ids
     *
     * @param msgTypeCode
     * @param receiveOrgId
     * @return
     */
    List<String> findRoleIds(@Param("msgTypeCode") String msgTypeCode, @Param("receiveOrgId") String receiveOrgId);

    /**
     * 根据组织ID、角色ID与已读状态查询消息
     *
     * @param orgId
     * @param roleIdArr
     * @param readFlag
     * @return
     */
    List<SysMessage> findMessageByOrgIdAndRoleIdsAndReadFlag(@Param("orgId") String orgId, @Param("roleIdArr") String[] roleIdArr, @Param("readFlag") String readFlag);
}