package com.crimoon.common;

import com.tencent.weibo.oauthv2.OAuthV2;
import com.weibo.sdk.android.Oauth2AccessToken;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class AccessTokenKeeper {
	private static final String PREFERENCES_NAME = "com_weibo_sdk_android";
	
	public static void keepAccessToken(Context context, Oauth2AccessToken token) {
		SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
		Editor editor = pref.edit();
		editor.putString("token", token.getToken());
		editor.putLong("expiresTime", token.getExpiresTime());
		editor.commit();
	}
	public static void clear(Context context){
	    SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
	    Editor editor = pref.edit();
	    editor.clear();
	    editor.commit();
	}
	public static void clearTC(Context context){
	    SharedPreferences pref = context.getSharedPreferences("tenxun", Context.MODE_APPEND);
	    Editor editor = pref.edit();
	    editor.clear();
	    editor.commit();
	}

	public static Oauth2AccessToken readAccessToken(Context context){
		
		Oauth2AccessToken token = new Oauth2AccessToken();
		SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
		token.setToken(pref.getString("token", ""));
		token.setExpiresTime(pref.getLong("expiresTime", 0));
		return token;
	}
	
	public static void saveDate(Context context, OAuthV2 token) { //保存第一次授权后，获取的OAuthV2，注意红色部分，必须保存
		
		
		SharedPreferences preferences = context
		.getSharedPreferences("tenxun", Context.MODE_APPEND);
		Editor editor = preferences.edit();
		editor.putString("token", token.getAccessToken());
		editor.putString("expiresTime", token.getExpiresIn());
		editor.putString("openid", token.getOpenid()); 
		editor.putString("opkey", token.getOpenkey());
		Log.e("tencent", "tencent "+ token.getExpiresIn());
		editor.commit();
	}
	public static OAuthV2 getDate(Context context)    //获取保存的oAuthV2，注意红色部分
	{
		OAuthV2 oAuthV2=new OAuthV2();
		SharedPreferences  preferences = context
		.getSharedPreferences("tenxun", Context.MODE_APPEND);
		oAuthV2.setAccessToken(preferences.getString("token",""));
		oAuthV2.setExpiresIn(preferences.getString("expiresTime",null));
		oAuthV2.setOpenid(preferences.getString("openid", null));
		oAuthV2.setOpenkey(preferences.getString("opkey", null));
		return oAuthV2;
	}

}
