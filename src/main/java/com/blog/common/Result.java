package com.blog.common;

public class Result<T> {
	private boolean success;//是否成功
	private T data;//成功时的返回数据
	private String message;	//返回信息
	
	public Result(){	}
	
	public Result(boolean success,T data,String message){
		this.success = success;
		this.data = data;
		this.message = message;
	}
	
	public String toString(){
		return "Result:{" +
				"success:" +success +
				"data:" + data +
				"message:" +message
				+ "}";
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}