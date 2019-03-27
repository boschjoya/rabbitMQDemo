package com.example.demo.controller;


import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.DemoDto;
import com.example.demo.entity.DemoOutputDto;
import com.example.demo.entity.FFResponseModel;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController(value = "swagger和log4j测试用的接口")
@RequestMapping(value = "/test")
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	
	private static Logger logger = Logger.getLogger(GreetingController.class);
	

    //@RequestMapping(value = "/swagger")
    //@RequestMapping("/swagger")
    @RequestMapping(value = "/swagger",name = "填一个词组合",method = RequestMethod.POST)
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format(template, name);
    }
    
    @RequestMapping(value = "/demo",name = "invokePost说明",method = RequestMethod.POST)
    public FFResponseModel<DemoOutputDto> invokePost(@RequestBody  @ApiParam(name="传入对象",value="传入json格式",required=true) DemoDto input) {
    	logger.info("/testPost is called. input=" + input.toString());
        return new FFResponseModel("200","ok",input.toString());
    }

}
