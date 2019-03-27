package com.example.demo.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="基础返回类",description="接口请求出参公共类")
public class FFResponseModel<T> implements Serializable{
	private static final long serialVersionUID = -2215304260629038881L;
    // 状态码
    @ApiModelProperty(example="00000")
    private String code;
    // 业务提示语
    @ApiModelProperty(example="成功")
    private String msg;
    // 数据对象
    private T data;
    
    

	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}



	public T getData() {
		return data;
	}



	public void setData(T data) {
		this.data = data;
	}



	public FFResponseModel(String code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
    
}
