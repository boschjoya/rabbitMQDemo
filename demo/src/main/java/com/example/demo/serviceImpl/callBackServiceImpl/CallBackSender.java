package com.example.demo.serviceImpl.callBackServiceImpl;

import java.util.UUID;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** 
* @author Joya 
* @version Mar 15, 2019 9:18:46 AM 
* @describe  
*/

@Component
public class CallBackSender implements  RabbitTemplate.ConfirmCallback{
	@Autowired
    private RabbitTemplate rabbitTemplatenew;
    public void send(String s) {
        
        rabbitTemplatenew.setConfirmCallback(this);
        String msg="callbackSender : i am callback sender" + s;
        System.out.println(msg );
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());  
        System.out.println("callbackSender UUID: " + correlationData.getId());  
        this.rabbitTemplatenew.convertAndSend("exchange", "topic.messages", msg, correlationData);  
    }
 
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // TODO Auto-generated method stub
        System.out.println("callbakck confirm: " + correlationData.getId() + "####" + cause + "####" + ack);
    }
}
