package com.yxd.rabbitmq.service.impl;

import com.yxd.rabbitmq.dao.mapper.SysMessageReceiverMapper;
import com.yxd.rabbitmq.model.entity.SysMessageReceiver;
import com.yxd.rabbitmq.service.SysMessageReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Service Implementation:SysMessageReceiver
 *
 * @author 李晓阳
 * @date 2018-6-11
 */
@Service
@Slf4j
public class SysMessageReceiverServiceImpl implements SysMessageReceiverService {

    @Autowired
    private SysMessageReceiverMapper sysMessageReceiverMapper;

    /**
     * 新增SysMessageReceiver
     *
     * @param entity
     * @return void    返回类型
     * @Title: saveSysMessageReceiver
     * @date 2018-6-11
     * @user by 李晓阳
     */
    @Override
    public void insertSysMessageReceiver(SysMessageReceiver entity) {
        sysMessageReceiverMapper.insert(entity);
    }

    ;

    /**
     * 批量新增SysMessageReceiver
     *
     * @param entity
     * @return void    返回类型
     * @Title: batchSaveSysMessageReceiver
     * @date 2018-6-11
     * @user by 李晓阳
     */
    @Override
    public void batchInsertSysMessageReceiver(List<SysMessageReceiver> entity) {
        sysMessageReceiverMapper.batchInsert(entity);
    }

    ;

    /**
     * 编辑SysMessageReceiver
     *
     * @param entity
     * @return void    返回类型
     * @Title: updateSysMessageReceiver
     * @date 2018-6-11
     * @user by 李晓阳
     */
    @Override
    public void updateSysMessageReceiver(SysMessageReceiver entity) {
        sysMessageReceiverMapper.update(entity);
    }

    ;

    /**
     * 根据id删除SysMessageReceiver
     *
     * @param id
     * @return void    返回类型
     * @Title: deleteSysMessageReceiverById
     * @date 2018-6-11
     * @user by 李晓阳
     */
    @Override
    public void deleteSysMessageReceiverById(String id) {
        sysMessageReceiverMapper.deleteById(id);
    }

    ;

    /**
     * 根据id批量删除SysMessageReceiver
     *
     * @param ids
     * @return void    返回类型
     * @Title: deleteSysMessageReceiverByIds
     * @date 2018-6-11
     * @user by 李晓阳
     */
    @Override
    public void deleteSysMessageReceiverByIds(String ids) {
        if (ids != null && !("").equals(ids)) {
            String[] idsArr = ids.split(",");
            sysMessageReceiverMapper.deleteByIds(idsArr);
        }
    }

    ;

    /**
     * 根据id查询
     *
     * @param id
     * @return void    返回类型
     * @Title: findSysMessageReceiverById
     * @date 2018-6-11
     * @user by 李晓阳
     */
    @Override
    public SysMessageReceiver findSysMessageReceiverById(String id) {
        return sysMessageReceiverMapper.findById(id);
    }

    ;

    /**
     * 查询全部
     *
     * @return List<SysMessageReceiver>    返回类型
     * @Title: findSysMessageReceiverAll
     * @date 2018-6-11
     * @user by 李晓阳
     */
    @Override
    public List<SysMessageReceiver> findSysMessageReceiverAll(SysMessageReceiver search) {
        return sysMessageReceiverMapper.findAll(search);
    }

    @Override
    public void updateReadFlagByMessageIdAndRoleIdsAndReceiveOrgId(String messageId, String roleIds, String receiveOrgId) {
        String[] roleIdArr = roleIds.split(",");
        sysMessageReceiverMapper.updateReadFlag(messageId, roleIdArr, receiveOrgId);
    }

    ;

}
