package com.floatcloud.file.constant;

/**
 * 存储 MQ 的常量
 * @author floatcloud
 */
public class MqConstant {

    /**
     * 文件提醒交换机
     */
    public static final String FILE_REMIND_EXCHANGE = "file_remind_exchange";

    /**
     * 文件提醒死信交换机
     */
    public static final String FILE_REMIND_EXCHANGE_DXL = "file_remind_exchange_dxl";

    /**
     * 队列长度
     */
    public static final int QUEUE_MAX_LENGTH = 100;

    /**
     * 消息过期时间
     */
    public static final String FILE_REMIND_MESSAGE_LIVE_TIME = "20000";

    /**
     * 文件提醒队列
     */
    public static final String FILE_REMIND_QUEUE = "file_remind_queue";

    /**
     * 文件提醒死信队列
     */
    public static final String FILE_REMIND_QUEUE_DXL = "file_remind_queue_dxl";

    /**
     * 文件提醒路由键
     */
    public static final String FILE_REMIND_ROUTING_KEY = "file.remind.rout.key";

    /**
     * 文件提醒死信路由键
     */
    public static final String FILE_REMIND_DXL_ROUTING_KEY = "file.remind.dxl.rout.key";

}
