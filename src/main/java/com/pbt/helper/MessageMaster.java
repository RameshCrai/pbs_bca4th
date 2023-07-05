package com.pbt.helper;

public class MessageMaster {
	
	private String SuccessMessage = "";
	private String FailedMessage = "";
	
	public MessageMaster() {
		super();
	}

	public MessageMaster(String suc) {
		
		this.SuccessMessage = suc;
		
		switch(suc){
		case "success":
			System.out.println("Hellow world");
			
		case "failed":			
			
		case "update":
			
		case "delete":	
			
			
	     }
		
	
	}
	
	public  String ShowMaster() {

		String ms = "";
		
		
		return ms;
	}
	
	

}
