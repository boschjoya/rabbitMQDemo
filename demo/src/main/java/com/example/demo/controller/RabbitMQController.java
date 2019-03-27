package com.example.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.User;
import com.example.demo.entity.FFResponseModel;
import com.example.demo.serviceImpl.HelloSender1;
import com.example.demo.serviceImpl.topicServiceImpl.HelloSender2;
import com.example.demo.serviceImpl.UserSender;
import com.example.demo.serviceImpl.callBackServiceImpl.CallBackSender;
import com.example.demo.serviceImpl.fanoutServiceImpl.MessageSender1;
import com.example.demo.serviceImpl.toMany.Sender1;
import com.example.demo.serviceImpl.toMany.Sender2;

import io.swagger.annotations.ApiParam;

@RestController(value = "RabbitMQ测试用的接口")
@RequestMapping(value = "/rabbitqqqq")
public class RabbitMQController {
	private static Logger logger = Logger.getLogger(RabbitMQController.class);
	
	@Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender2 helloSender2;
    @Autowired
    private UserSender userSender;
    @Autowired
    private CallBackSender callbackSender;
    @Autowired
    private MessageSender1 messageSender;
    @Autowired
    private Sender1 sender1;
    @Autowired
    private Sender2 sender2;
    
    
    /**
     * @param name
     * @return
     */
    @RequestMapping(value = "/oneDirect",name = "简单队列模式",method = RequestMethod.POST)
    public FFResponseModel<String> hello(@RequestParam(value = "anyWords", defaultValue = "joya") String name) {
        helloSender1.send(name);
        return new FFResponseModel<String>("200","ok","给消息加上" + name);
    }
    
    /**
     * @param user
     * @return
     */
    @RequestMapping(value = "/sendeUser", name = "试验发送一个对象消息", method = RequestMethod.POST)
    public FFResponseModel<String> helloUser(@RequestBody  @ApiParam(name="senderObject",value="传入json格式",required=true) User user) {
        userSender.sender(user);
        return new FFResponseModel<String>("200","ok","传入对象" + user.toString());
    }
    
    /**
     * @param name
     * @param num
     * @return
     */
    @RequestMapping(value = "/oneToMany",name = "工作队列模式--一对多",method = RequestMethod.POST)
    public FFResponseModel<String> helloToMany(@RequestParam(value = "anyWords", defaultValue = "joya") String name,
    		@RequestParam(value = "numbers", defaultValue = "10") int num) {
    	for(int i=0;i<num;i++){
    		sender1.send(name + i);
        }
        return new FFResponseModel<String>("200","ok","给消息加上" + name + ",发送了" + num + "次");
    }
    
    /**
     * @param name1
     * @param name2
     * @return
     */
    @RequestMapping(value = "/manyToMany",name = "工作队列模式--多对多",method = RequestMethod.POST)
    public FFResponseModel<String> helloManyToMany(@RequestParam(value = "anyWords1", defaultValue = "joya") String name1,
    		@RequestParam(value = "anyWords2", defaultValue = "yoyo") String name2) {
    	for(int i=0;i<10;i++){
    		sender1.send(name1 + i);
    		sender2.send(name2 + i);
        }
        return new FFResponseModel<String>("200","ok","多对多负载均衡");
    }
    
    /**
     * @param word1
     * @param word2
     * @return
     */
    @RequestMapping(value = "/Topic", name = "以Topic模式发送消息", method = RequestMethod.POST)
    public FFResponseModel<String> topicUser(@RequestParam(value = "anyWords1", defaultValue = "World1") String word1,
    		@RequestParam(value = "anyWords2", defaultValue = "World2") String word2) {
    	helloSender2.send(word1,word2);
        return new FFResponseModel<String>("200","ok","Topic模式,给消息加上" + word1 + "&" + word2);
    }
    
    /**
     * @param worldString
     * @return
     */
    @RequestMapping(value = "/fanoutModel", name = "以fanout模式发送消息", method = RequestMethod.POST)
    public FFResponseModel<String> fanoutSender(@RequestParam(value = "anyWords", defaultValue = "World")String worldString){
    	messageSender.send(worldString);
    	return new FFResponseModel<String>("200","ok","fanout模式" + worldString);
    }
    
    /**
     * @param worldString
     * @return
     */
    @RequestMapping(value = "/callbackModel", name = "带消息返回ACK模式的消息发送", method = RequestMethod.POST)
    public FFResponseModel<String> addCallBackModel(@RequestParam(value = "anyWords", defaultValue = "World")String worldString){
    	callbackSender.send(worldString);
    	return new FFResponseModel<String>("200","ok","带消息返回ACK模式的消息发送" + worldString);
    }
    
}
