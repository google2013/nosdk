package com.sdk.common;

import org.json.JSONObject;

import cn.uc.gamesdk.open.OrderInfo;
import cn.uc.gamesdk.unity3d.UCGameSdk;

import android.app.Activity;
import android.util.Log;

public class SDKManager 
{
	private static SDKManager instance = null;

	final String TAG = "Sample";
	final String CHANNEL = "Default";
	final String CALLBACK_URL = "";
	final int GAME_ID = 0;
	final int ORIENTATION = 0;
	final boolean ENABLE_PAY_HISTORY = false;
	final boolean ENABLE_USER_CHANGE = false;
	
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
		UCGameSdk.initSDK(currentActivity, 
				false, 
				GAME_ID, 
				ENABLE_PAY_HISTORY, ENABLE_USER_CHANGE, 
				ORIENTATION);
	}

	public void login(String param)
	{
		UCGameSdk.login();
	}

	public void switchAccount(String param)
	{
		UCGameSdk.login();
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
		UCGameSdk.logout();
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

	String getSignFromServer()
	{
		//UC充值前需要先从服务器上获取sign
		return "";
	}
	
	public void pay(String param)
	{
		String sign = getSignFromServer();
		UCGameSdk.pay("", "", "0.01", CALLBACK_URL, CALLBACK_URL, "MD5", sign);
	}

	public void onPayResult(PayResultType result, OrderInfo orderInfo)
	{
        try {
            JSONObject json = new JSONObject();
            json.put("result", result.ordinal());
            if (result == PayResultType.Success)
            {
                json.put("orderMount", orderInfo.getOrderAmount());
                json.put("orderId", orderInfo.getOrderId());
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
			UCGameSdk.exitSDK();
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
		UCGameSdk.submitRoleData("", "", "", "", 0, 0);
	}
}
