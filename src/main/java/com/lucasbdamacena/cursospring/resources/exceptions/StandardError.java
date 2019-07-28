package com.lucasbdamacena.cursospring.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer code;
	private String msg;
	
	private long timeStamp;
	
	public StandardError(Integer code, String msg, long timeStamp) {
		super();
		this.code = code;
		this.msg = msg;
		this.timeStamp = timeStamp;
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

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}	
	
}
