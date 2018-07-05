package com.yxd.rabbitmq.web.websocket;

import com.alibaba.fastjson.JSONObject;
import com.yxd.rabbitmq.constant.MessageConstant;
import com.yxd.rabbitmq.model.entity.SysMessage;
import com.yxd.rabbitmq.model.vo.MessageVoForWebSocket;
import com.yxd.rabbitmq.service.SysMessageService;
import com.yxd.rabbitmq.web.config.ApplicationContextRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 李晓阳
 * @company 国药国华网络科技有限公司
 * @create 2018-05-15 9:19
 * @desc websocket
 **/
@ServerEndpoint("/ws/yxd/{orgId}/{roleIds}")
@Component
@Slf4j
public class WebSocketServerEndpoint {

    /**
     * ServerEndpoint
     * <p>
     * 使用springboot的唯一区别是要@Component声明下，而使用独立容器是由容器自己管理websocket的，但在springboot中连容器都是spring管理的。
     * <p>
     * 虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。
     */
    /**
     * 存活的session集合（使用线程安全的map保存）
     */
    private static Map<String, Session> livingSessions = new ConcurrentHashMap<>();


    /**
     * 建立连接的回调方法
     *
     * @param session
     * @param orgId
     * @param roleIds
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("orgId") String orgId, @PathParam("roleIds") String roleIds) {
        livingSessions.put(session.getId(), session);
        log.info("session连接：" + session.getId());
        ApplicationContext act = ApplicationContextRegister.getApplicationContext();
        SysMessageService sysMessageService = act.getBean(SysMessageService.class);
        List<SysMessage> sysMessageList = new ArrayList<>();
        try {
            sysMessageList = sysMessageService.findMessageByOrgIdAndRoleIdsAndReadFlag(orgId, roleIds, MessageConstant.UNREAD);
            String message = JSONObject.toJSON(sysMessageList).toString();
            sendMessage(session, message);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("建立连接出错:" + e.getMessage());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info(session.getId() + " : " + message);
    }


    @OnError
    public void onError(Session session, Throwable error) {
        log.info(session.getId() + "发生错误:", error.getMessage());
    }


    @OnClose
    public void onClose(Session session) {
        livingSessions.remove(session.getId());
        log.info("session关闭：" + session.getId());
    }

    /**
     * 单独发送消息
     *
     * @param session
     * @param message
     */
    public void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("推送消息到前台失败:" + e.getMessage());
        }
    }

    /**
     * 群发消息
     *
     * @param message
     */
    public void sendMessageToAll(String message) {
        MessageVoForWebSocket messageVoForWebSocket = JSONObject.parseObject(message, MessageVoForWebSocket.class);
        List<MessageVoForWebSocket> list = Arrays.asList(messageVoForWebSocket);
        String messages = JSONObject.toJSON(list).toString();
        livingSessions.forEach((sessionId, session) -> {
            //组织Id匹配
            String loginOrgId = String.valueOf(session.getPathParameters().get("orgId"));
            List<Map<String, Object>> orgIdAndRoleIdMapList = messageVoForWebSocket.getOrgIdAndRoleIdMapList();
            if (orgIdAndRoleIdMapList != null && orgIdAndRoleIdMapList.size() > 0) {
                for (Map<String, Object> map : orgIdAndRoleIdMapList) {
                    String messageOrgId = String.valueOf(map.get("orgId"));
                    if (messageOrgId.equalsIgnoreCase(loginOrgId)) {
                        //匹配角色Id,如果有一样的，则发送消息
                        String roleIds = String.valueOf(session.getPathParameters().get("roleIds"));
                        String[] loginRoleIdArr = roleIds.split(",");
                        List<String> loginRoleIdList = new ArrayList<>();
                        for (String loginRoleId : loginRoleIdArr) {
                            loginRoleIdList.add(loginRoleId);
                        }
                        List<String> messageRoleIdList = (List<String>) map.get("roleIdList");
                        loginRoleIdList.retainAll(messageRoleIdList);
                        if (loginRoleIdList.size() > 0) {
                            sendMessage(session, messages);
                            //当对当前连接发送消息后，直接break到下一个连接，以免重复发送消息
                            break;
                        }
                    }
                }
            }
        });
    }

}