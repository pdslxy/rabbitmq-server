package com.yxd.rabbitmq.web.controller;


import com.yxd.rabbitmq.constant.MessageConstant;
import com.yxd.rabbitmq.model.vo.MessageVoForSave;
import com.yxd.rabbitmq.model.vo.MessageVoForWebSocket;
import com.yxd.rabbitmq.sender.service.Sender;
import com.yxd.rabbitmq.service.SysMessageReceiverService;
import com.yxd.rabbitmq.service.SysMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author 李晓阳
 * @company 国药国华网络科技有限公司
 * @create 2018-04-17 14:41
 * @desc raibbitmq
 **/
@Api(tags = {"RabbitMq生产者接口"})
@RestController
@Slf4j
@RequestMapping("/message")
public class SysMessageController {

    @Autowired
    private Sender sender;


    @Autowired
    private SysMessageService sysMessageService;

    @Autowired
    private SysMessageReceiverService sysMessageReceiverService;


    /**
     * 发送消息
     *
     * @param messageVoForSave
     * @return
     */
    @ApiOperation("发送消息")
    @GetMapping(value = "/send")
    public void send(MessageVoForSave messageVoForSave) {
        Predicate<List> pred = list -> list != null && list.size() > 0;
        Asserts.notEmpty(messageVoForSave.getRoutingName(), "routingName");
        Asserts.notEmpty(messageVoForSave.getMessageContent(), "messageContent");
        Asserts.notEmpty(messageVoForSave.getSendUserId(), "sendUserId");
        Asserts.notEmpty(messageVoForSave.getSendUserName(), "sendUserName");
        Asserts.notEmpty(messageVoForSave.getMsgTypeCode(), "msgTypeCode");
        Asserts.check(pred.test(messageVoForSave.getReceiveOrgIdList()), "receiveOrgIdList 不能为空");
        MessageVoForWebSocket messageVoForWebSocket = sysMessageService.insertSysMessage(messageVoForSave);
        //公示不发送消息队列
        if (!messageVoForSave.getMsgTypeCode().equalsIgnoreCase(MessageConstant.TYPE_BULLETIN)) {
            sender.send(messageVoForWebSocket, messageVoForSave.getRoutingName());
        }
    }


    /**
     * 更新消息/公告为已读状态
     *
     * @param messageId
     * @param roleIds
     * @param receiveOrgId
     */
    @ApiOperation("更新消息/公告为已读状态")
    @GetMapping(value = "/updateReadFlag")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "messageId", value = "条件:消息ID", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "roleIds", value = "条件:接受角色ID(多个用,隔开)", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "receiveOrgId", value = "条件:接受组织ID", dataType = "String", paramType = "query", required = true),
    })
    public void updateReadFlag(@RequestParam("messageId") String messageId, @RequestParam("roleIds") String roleIds, @RequestParam("receiveOrgId") String receiveOrgId) {
        try {
            sysMessageReceiverService.updateReadFlagByMessageIdAndRoleIdsAndReceiveOrgId(messageId, roleIds, receiveOrgId);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("更新消息/公告为已读状态:" + e.getMessage());
        }
    }

    @ApiOperation("获取ip地址与端口号")
    @GetMapping(value = "/getIpAndPort")
    public String getIpAndPort(HttpServletRequest request) {
        String ipAddr = "";
        String port = "";
        try {
            ipAddr = getLocalIP();
            port = String.valueOf(request.getLocalPort());
        } catch (Exception e) {
            log.error("获取ip地址与端口号出错：", e);
        }
        return ipAddr + ":" + port;
    }

    /**
     * 获取本地IP地址
     *
     * @throws SocketException
     */
    public static String getLocalIP() throws UnknownHostException, SocketException {
        if (isWindowsOS()) {
            return InetAddress.getLocalHost().getHostAddress();
        } else {
            return getLinuxLocalIp();
        }
    }

    /**
     * 判断操作系统是否是Windows
     *
     * @return
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf(MessageConstant.SYS_WINDOWS) > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取Linux下的IP地址
     *
     * @return IP地址
     * @throws SocketException
     */
    private static String getLinuxLocalIp() throws SocketException {
        String ip = "127.0.0.1";
        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
            NetworkInterface intf = en.nextElement();
            String name = intf.getName();
            if (!name.contains("docker") && !name.contains("lo")) {
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ipaddress = inetAddress.getHostAddress().toString();
                        if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                            ip = ipaddress;
                        }
                    }
                }
            }
        }
        return ip;
    }
}
