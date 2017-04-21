package com.sdk.common;

public class SDKManager 
{
	private static SDKManager instance = null;
	
	public static SDKManager getInstance()
	{
		if( instance == null )
		{
			instance = new SDKManager();
		}
		return instance;
	}
	
	public SDKManager()
	{

	}
	
	public native void onInit(String result);
	public native void onLoginResult(String result);
	public native void onPayResult(String result);
	public native void onLogout(String result);
	
	public void init(String param)
	{

	}

	public void login(String param)
	{

	}

	public void switchAccount(String param)
	{

	}

	public void logout(String param)
	{

	}

	public String getChannel()
	{
		return "test";
	}

	public void pay(String param)
	{

	}

	public void exit(String param)
	{

	}

	public void sendExtraData(String param)
	{

	}
}
