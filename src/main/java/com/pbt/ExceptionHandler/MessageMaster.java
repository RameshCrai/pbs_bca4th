package com.pbt.ExceptionHandler;

public class MessageMaster{
	
	private String message;
	private String typeofMessage;
	
	public MessageMaster(String mess, String typeofMes) {
		this.message = mess;
		this.typeofMessage = typeofMes;
		
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String mess) {
		this.message = mess;
	}

	public String getTypeofMessage() {
		return typeofMessage;
	}

	public void setTypeofMessage(String typeofMessage) {
		this.typeofMessage = typeofMessage;
	}
	
	
	

}
