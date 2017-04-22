package com.sdk.common;

import android.util.Log;

public class SDKManager 
{
	private static SDKManager instance = null;

	final String TAG = "Sample";
	final String CHANNEL = "Default";
	
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
		Log.d(TAG, "init");
	}

	public void login(String param)
	{
		Log.d(TAG, "login");
		onLoginResult("");
	}

	public void switchAccount(String param)
	{
		Log.d(TAG, "switchAccount");
		onLoginResult("");
	}

	public void logout(String param)
	{
		Log.d(TAG, "logout");
		onLogout("");
	}

	public String getChannel()
	{
		return CHANNEL;
	}

	public void pay(String param)
	{
		Log.d(TAG, "pay");
		onPayResult("");
	}

	public void exit(String param)
	{
		Log.d(TAG, "exit");
	}

	public void sendExtraData(String param)
	{
		Log.d(TAG, "sendExtraData");
	}
}
