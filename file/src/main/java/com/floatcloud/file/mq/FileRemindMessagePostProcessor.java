package com.floatcloud.file.mq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * 设置消息的属性
 * @author floatcloud
 */
public class FileRemindMessagePostProcessor  implements MessagePostProcessor {

    private Long timeOut;

    public FileRemindMessagePostProcessor(Long timeOut){
        this.timeOut = timeOut;
    }

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().setExpiration(this.timeOut.toString());
        return message;
    }
}
