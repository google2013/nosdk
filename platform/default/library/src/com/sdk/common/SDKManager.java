package com.sdk.common;

import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;

public class SDKManager 
{
	private static SDKManager instance = null;

	final String TAG = "Sample";
	final String CHANNEL = "Default";
	
	Activity currentActivity = null;
	boolean channelHasExit = true;
	
	public static SDKManager getInstance()
	{
		if( instance == null )
		{
			instance = new SDKManager();
		}
		return instance;
	}
	
	native void onInit(String result);
	native void onLoginResult(String result);
	native void onPayResult(String result);
	native void onLogoutResult(String result);
	native void onExitResult(String result);
	
	public void init(Activity activity)
	{
		onInit("");
		currentActivity = activity;
	}

	public void login(String param)
	{

	}

	public void switchAccount(String param)
	{
	}

	public void onLoginResult(LoginResultType result, String param)
	{
        try {
            JSONObject json = new JSONObject();
            json.put("result", result.ordinal());
            if (result == LoginResultType.Success)
            {
            	json.put("token", param);
            }
            else
            {
            	json.put("description", param);
            }
            onLoginResult(json.toString());
        } catch (Throwable e) {
            e.printStackTrace();
        }
	}
	
	public void logout(String param)
	{
	}

	public void onLogoutResult(LogoutResultType result, String param)
	{
        try {
            JSONObject json = new JSONObject();
            json.put("result", result.ordinal());
            if (result != LogoutResultType.Success)
            {
            	json.put("description", param);
            }
            onLoginResult(json.toString());
        } catch (Throwable e) {
            e.printStackTrace();
        }
	}
	
	public String getChannel()
	{
		return CHANNEL;
	}
	
	public void pay(String param)
	{

	}

	public void onPayResult(PayResultType result)
	{
        try {
            JSONObject json = new JSONObject();
            json.put("result", result.ordinal());
            if (result == PayResultType.Success)
            {
            }
            onLoginResult(json.toString());
        } catch (Throwable e) {
            e.printStackTrace();
        }
	}
	
	public void exit(String param)
	{
		if (channelHasExit)
		{
			//平台有退出界面，先调用平台退出接口
		}
		else
		{
			//平台没有退出界面，使用游戏退出界面
			onExitResult(ExitResultType.GameExit, "");
		}
	}

	public void onExitResult(ExitResultType result, String param)
	{
        try {
            JSONObject json = new JSONObject();
            json.put("result", result.ordinal());
            if (result != ExitResultType.Success)
            {
            	json.put("description", param);
            }
            onLoginResult(json.toString());
        } catch (Throwable e) {
            e.printStackTrace();
        }
	}
	
	public void sendExtraData(String param)
	{
		Log.d(TAG, "sendExtraData");
	}
}
