package com.example.demo;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo"})
public class DemoApplication {
	
	@RequestMapping("/")
    public String hello(){
    return"Hello SpringBoot!";
    }
	
	public final static String queueName = "hello";
	
	public final static String queueNameSend = "hi";
	
	public final static String userQueueName = "user";
	 
    @Bean
    public Queue helloQueue() {
        return new Queue(DemoApplication.queueName);
    }
    
    @Bean
    public Queue queueNameSend() {
        return new Queue(DemoApplication.queueNameSend);
    }
    
    @Bean
    public Queue userQueue() {
        return new Queue(DemoApplication.userQueueName);
    }
    
    //===============以下是验证topic Exchange的队列==========
    @Bean
    public Queue queueMessage() {
        return new Queue("topic.message");
    }
 
    @Bean
    public Queue queueMessagessss() {
        return new Queue("topic.messagessss");
    }
  //===============以上是验证topic Exchange的队列==========
    
    
    //===============以下是验证Fanout Exchange的队列==========
    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }
 
    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }
 
    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C");
    }
    //===============以上是验证Fanout Exchange的队列==========
    
 
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }
 
    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }
 
    /**
     * 将队列topic.messagessss与exchange绑定，binding_key为topic.#,模糊匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessagessss, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessagessss).to(exchange).with("topic.#");
    }
    
    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }
 
    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }
 
    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
    


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
