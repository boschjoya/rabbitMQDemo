package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="演示类",description="接口请求入参对象" )
public class DemoDto implements Serializable{
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DemoDto [strDemo=");
		builder.append(strDemo);
		builder.append(", longNum=");
		builder.append(longNum);
		builder.append(", doubleNum=");
		builder.append(doubleNum);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}

	private static final long serialVersionUID = 1L;

    @NotNull
    @ApiModelProperty(value = "defaultStr",example="mockStrValue")
    private String strDemo;

    @NotNull
    @ApiModelProperty(example="1234343523",required = true)
    private Long longNum;

    @NotNull
    @ApiModelProperty(example="111111.111")
    private Double doubleNum;

    @NotNull
    @ApiModelProperty(example="2018-12-04T13:46:56.711Z")
    private Date date;

	public String getStrDemo() {
		return strDemo;
	}

	public void setStrDemo(String strDemo) {
		this.strDemo = strDemo;
	}

	public Long getLongNum() {
		return longNum;
	}

	public void setLongNum(Long longNum) {
		this.longNum = longNum;
	}

	public Double getDoubleNum() {
		return doubleNum;
	}

	public void setDoubleNum(Double doubleNum) {
		this.doubleNum = doubleNum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
    
    
}
