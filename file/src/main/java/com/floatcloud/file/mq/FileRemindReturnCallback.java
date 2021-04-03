package com.floatcloud.file.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 消息发送确认
 * @author floatcloud
 */
@Slf4j
public class FileRemindReturnCallback implements RabbitTemplate.ReturnsCallback {

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        int replyCode = returned.getReplyCode();
        log.warn("发送Return确认成功");
        if (replyCode > 0){

        } else {

        }
    }
}
