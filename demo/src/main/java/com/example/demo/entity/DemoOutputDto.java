package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="接口请求出参实际数据对象",description="接口请求出参实际数据对象")
public class DemoOutputDto implements Serializable{
	 private String res;

	    @NotNull
	    @ApiModelProperty(value = "defaultOutputStr",example="mockOutputStrValue")
	    private String outputStrDemo;

	    @NotNull
	    @ApiModelProperty(example="6666666",required = true)
	    private Long outputLongNum;

	    @NotNull
	    @ApiModelProperty(example="88888.888")
	    private Double outputDoubleNum;

	    @NotNull
	    @ApiModelProperty(example="2018-12-12T11:11:11.111Z")
	    private Date outputDate;

		public String getRes() {
			return res;
		}

		public void setRes(String res) {
			this.res = res;
		}

		public String getOutputStrDemo() {
			return outputStrDemo;
		}

		public void setOutputStrDemo(String outputStrDemo) {
			this.outputStrDemo = outputStrDemo;
		}

		public Long getOutputLongNum() {
			return outputLongNum;
		}

		public void setOutputLongNum(Long outputLongNum) {
			this.outputLongNum = outputLongNum;
		}

		public Double getOutputDoubleNum() {
			return outputDoubleNum;
		}

		public void setOutputDoubleNum(Double outputDoubleNum) {
			this.outputDoubleNum = outputDoubleNum;
		}

		public Date getOutputDate() {
			return outputDate;
		}

		public void setOutputDate(Date outputDate) {
			this.outputDate = outputDate;
		}

		public DemoOutputDto(String res, @NotNull String outputStrDemo, @NotNull Long outputLongNum,
				@NotNull Double outputDoubleNum, @NotNull Date outputDate) {
			super();
			this.res = res;
			this.outputStrDemo = outputStrDemo;
			this.outputLongNum = outputLongNum;
			this.outputDoubleNum = outputDoubleNum;
			this.outputDate = outputDate;
		}
	    
	    
}
