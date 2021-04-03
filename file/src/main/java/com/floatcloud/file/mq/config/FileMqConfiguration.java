package com.floatcloud.file.mq.config;


import com.floatcloud.file.constant.MqConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 进行RabbitMq的Bean的注入
 * @author floatcloud
 */
@Configuration
public class FileMqConfiguration {


    /**
     * 文件提醒交换机
     * @return 文件提醒交换机
     */
    @Bean
    public Exchange fileRemindExchange(){
        return ExchangeBuilder.directExchange(MqConstant.FILE_REMIND_EXCHANGE).durable(true).build();
    }

    /**
     * 文件提醒死信交换机
     * @return 文件提醒死信交换机
     */
    @Bean
    public Exchange fileRemindDxlExchange(){
        return ExchangeBuilder.directExchange(MqConstant.FILE_REMIND_EXCHANGE_DXL).durable(true).build();
    }

    /**
     * 该队列绑定了死信队列
     * @return 队列
     */
    @Bean
    public Queue fileRemindQueue(){
        return QueueBuilder.durable(MqConstant.FILE_REMIND_QUEUE).maxLength(MqConstant.QUEUE_MAX_LENGTH)
                .deadLetterExchange(MqConstant.FILE_REMIND_EXCHANGE_DXL)
                .deadLetterRoutingKey(MqConstant.FILE_REMIND_DXL_ROUTING_KEY).build();
    }

    /**
     * 死信队列
     * @return 死信队列
     */
    @Bean
    public Queue fileRemindDxlQueue(){
        return QueueBuilder.durable(MqConstant.FILE_REMIND_QUEUE_DXL).maxLength(MqConstant.QUEUE_MAX_LENGTH).build();
    }


    /**
     * queue与Exchange绑定
     * @return binding
     */
    @Bean
    public Binding bindingFileRemind(){
        return BindingBuilder.bind(fileRemindQueue()).to(fileRemindExchange())
                .with(MqConstant.FILE_REMIND_ROUTING_KEY).noargs();
    }


    /**
     * 死信队列的绑定
     * @return 死信队列的Binding
     */
    @Bean
    public Binding bindingFileRemindDxl(){
        return BindingBuilder.bind(fileRemindDxlQueue()).to(fileRemindDxlExchange())
                .with(MqConstant.FILE_REMIND_DXL_ROUTING_KEY).noargs();
    }


}
