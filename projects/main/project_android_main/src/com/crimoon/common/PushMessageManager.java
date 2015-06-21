package com.crimoon.common;

import java.io.Serializable;


import android.content.Intent;
import android.util.Log;

public class PushMessageManager{


	private static PushMessageManager mShareManager = null;

	
	public  PushMessageManager(){
		
		
		
	}

	public static  PushMessageManager instance(){
		
		if(mShareManager == null){
			
			mShareManager = new PushMessageManager();
		}
		return mShareManager;
	}
	public void pushMessage(String message,int time,String messageName){

//		Log.e("MSG", time +"MSG ADD" +messageName);
//		PushMessage meg = new PushMessage();
//		meg.setMessage(message);	
//		meg.setTime(time);
//		meg.setMessageName(messageName);
//	    Intent intent = new Intent();  
//	    intent.putExtra("MegId", 1);
//        intent.putExtra("Meg",meg  );
//        //设置发送广播的类型，可以随便写一个  
//        intent.setAction(PushService.action);  
//        ProjectTK.getContext().sendBroadcast(intent);  
	}
	public void cancelMessage(String messageName){
	
//		Log.e("MSG", "MSG remove" +messageName);
//		  Intent intent = new Intent();  
//		  intent.putExtra("MegId", 2);
//	      intent.putExtra("Meg",messageName);
//	      //设置发送广播的类型，可以随便写一个  
//	      intent.setAction(PushService.action);  
//	      ProjectTK.getContext().sendBroadcast(intent);  
		
	}
	public void removeAllMessage(){
//		
//		  Intent intent = new Intent();  
//		  intent.putExtra("MegId", 3);
//	      intent.setAction(PushService.action);  
//	      ProjectTK.getContext().sendBroadcast(intent);  
//	  	  Log.e("MSG", "MSG remove");
	}
	
} 

class PushMessage implements  Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String messageName;
	private int  time;
	private int  passtime;
	public PushMessage(){
		
		 passtime = 0;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageName() {
		return messageName;
	}
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}
	public float getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getPasstime() {
		return passtime;
	}
	public void setPasstime(int passtime) {
		this.passtime = passtime;
	}


	
}