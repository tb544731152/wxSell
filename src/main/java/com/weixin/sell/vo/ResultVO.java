package com.weixin.sell.vo;


import java.io.Serializable;

/**
 * http请求返回的最外层对象 Created by 廖师兄 2017-05-12 14:13
 */
public class ResultVO<T> implements Serializable {

	/** 错误码. */
	private Integer code;

	/** 提示信息. */
	private String msg;

	/** 具体内容. */
	private T data;

	public ResultVO() {
	}

	public ResultVO(Integer code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
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

}
