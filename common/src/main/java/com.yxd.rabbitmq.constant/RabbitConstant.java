package com.yxd.rabbitmq.constant;

import com.yxd.bjcomprehensive.enums.EnumBusSys;

/**
 * @author 李晓阳
 * @company 国药国华网络科技有限公司
 * @create 2018-05-03 13:22
 * @desc 消息常量
 **/
public class RabbitConstant {
    /**
     * 交换机名称
     */
    public final static String EXCHANGE = "yxd_exchange";
    /**
     * 交易队列
     */
    public final static String QUEUE_TRANSACTION = "queue_transaction";
    /**
     * 合同队列
     */
    public final static String QUEUE_CONTRACT = "queue_contract";
    /**
     * 资审队列
     */
    public final static String QUEUE_QUALIFICATION = "queue_qualification";
    /**
     * 交易路由key
     */
    public final static String RK_TRANSACTION = EnumBusSys.TRADE.name();
    /**
     * 合同路由key
     */
    public final static String RK_CONTRACT = EnumBusSys.CONTRACT.name();
    /**
     * 资审路由key
     */
    public final static String RK_QUALIFICATION = EnumBusSys.AUDIT.name();

}
