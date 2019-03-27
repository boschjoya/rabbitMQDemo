package com.example.demo.dao;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="演示对象类",description="接口请求入参对象" )
public class User implements Serializable{
	@ApiModelProperty(example = "joya",required = true)
	private String name;
    @ApiModelProperty(example = "123456789", required = true)
	private String pass;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [name=");
		builder.append(name);
		builder.append(", pass=");
		builder.append(pass);
		builder.append("]");
		return builder.toString();
	}
    
	

}
