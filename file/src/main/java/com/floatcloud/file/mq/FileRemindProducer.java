package com.floatcloud.file.mq;


import com.floatcloud.file.constant.MqConstant;
import com.floatcloud.file.dao.FileBasic;
import com.floatcloud.file.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author floatcloud
 */
@Service
@Slf4j
public class FileRemindProducer {

    @Resource
    private RabbitTemplate rt;

    /**
     * 向RabbitMQ中延迟队列（TTL+死信队列）发送提醒消息
     * @param file 需要提醒的文件
     * @param timeOut 延迟时间
     * @return 返回数据封装
     */
    public BaseResponse produceFileRemind(FileBasic file, Long timeOut) {
        BaseResponse baseResponse = new BaseResponse();
        rt.setConfirmCallback(new FileRemindConfirmCallback());
        rt.setReturnsCallback(new FileRemindReturnCallback());
        rt.convertAndSend(MqConstant.FILE_REMIND_EXCHANGE,MqConstant.FILE_REMIND_ROUTING_KEY, file.toString().getBytes(),
                new FileRemindMessagePostProcessor(timeOut));
        return baseResponse;
    }
}
