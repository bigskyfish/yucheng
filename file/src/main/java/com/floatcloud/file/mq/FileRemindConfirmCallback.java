package com.floatcloud.file.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


/**
 * 发送端，发送到Exchange后消息确认
 * @author floatcloud
 */
@Slf4j
public class FileRemindConfirmCallback implements RabbitTemplate.ConfirmCallback {


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            log.warn("发送ack确认成功");
        } else {
            // TODO 消息发送确认失败; 补发还是后续处理
            log.error(cause);
        }
    }
}
