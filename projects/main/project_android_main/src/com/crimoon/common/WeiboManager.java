package com.crimoon.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;

import com.tencent.weibo.api.TAPI;
import com.tencent.weibo.constants.OAuthConstants;
import com.tencent.weibo.oauthv2.OAuthV2;
import com.tencent.weibo.webview.OAuthV2AuthorizeWebView;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.AsyncWeiboRunner;
import com.weibo.sdk.android.net.HttpManager;
import com.weibo.sdk.android.net.RequestListener;

@SuppressLint("HandlerLeak")
public class WeiboManager {

	private Handler msgHandler = null;
	//应用的key 请到官方申请正式的appkey替换APP_KEY
	public  String APP_KEY="1269506574";
	public  String appSeret= "44befb2909785a39b927c789e59c4be9";
	public  String REDIRECT_URL ="http://bbs.18183.com/forum-bazhusanguo-1.html";
	public  String sinaName  = "";
	//新支持scope 支持传入多个scope权限，用逗号分隔
	public static final String SCOPE = "direct_messages_write," +
			"friendships_groups_write," +
				"follow_app_official_microblog";
	
	
	public    String tckey = "1269506574";
	public    String tcst="00ba0884609e344f4f75422c2da07b55";
	public    String tcurl ="http://bbs.18183.com/forum-bazhusanguo-1.html";
	public    String tcName = "";

	private static WeiboManager shareManager = null;

	public WeiboManager() {

		APP_KEY = sinaAppKey();
		REDIRECT_URL = sinaURL();
		appSeret = sinaAppSeceret();
		sinaName = sinaName();
		tckey = tcAppKey();
		tcst = tcAppSeceret();
		tcName = tcName();
		tcurl = tcURL();
		
		
		
	
		msgHandler = new Handler() {

			public void handleMessage(Message msg) {

				switch (msg.what) {

				case 0: // 登录微博
				
					break;
				case 1:// 登录成功
					WeiboManager.instance().loginSucess();
					break;
				case 2:// 登录失败
					WeiboManager.instance().loginFail();
					break;
				case 3:// 注销
					WeiboManager.instance().onLogOutSuccess();
					break;
				case 5:// tc登录
					loginTencent();
					break;
				case 6://lockSceen
				{
					
					((ProjectTK) ProjectTK.getContext()). getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON,
							WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
				}
				break;
				case 7://unLockSreen;
					{

						((ProjectTK) ProjectTK.getContext()). getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
								WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				
					}
					break;
				}
				super.handleMessage(msg);
			}
		};

		

	}
	
	public void loginTencent() {
	
	}

	public Handler getHandler() {

		return msgHandler;
	}

	public static WeiboManager instance() {

		if (shareManager == null) {

			shareManager = new WeiboManager();
		}
		return shareManager;
	}
	public native String sinaAppKey();
	public native String sinaAppSeceret();
	public native String sinaURL();
	public native String sinaName();
	public native String tcAppKey();
	public native String tcAppSeceret();
	public native String tcURL();
	public native String tcName();
	public native void loginSucess();
	public native void loginFail();
	public native void shareSucess();
	public native void shareFail();
	public native void shareFailRepeate();
	public native void onLogOutSuccess();
	public native void enterBackGround();
	public native void enterFront();
	
	public boolean isAuthorizeExpired(){

	
		return true;
	}
	public void lockSceen(int golock){
		
		msgHandler.sendEmptyMessage(6+golock);
	}
	public void login() {

		msgHandler.sendEmptyMessage(5);
		
	}

	public void loginSina() {

		msgHandler.sendEmptyMessage(0);

	}

	public void logout() {

	
		msgHandler.sendEmptyMessage(3);
	}
	
	public void logSinaOut() {

		msgHandler.sendEmptyMessage(3);
	}

	public boolean isSinaAuthorExpired() {

		return  false;
	}

	public void postSMS(String msg) {

		Uri uri = Uri.parse("smsto:");
		Intent it = new Intent(Intent.ACTION_SENDTO, uri);
		it.putExtra("sms_body", msg);
		ProjectTK.getContext().startActivity(it);
	}
	public void sendMessage(String string) {

	
	}

	public void sendSinaMessage(String string) {

		
	}

}

class AuthDialogListener implements WeiboAuthListener {

	@Override
	public void onComplete(Bundle values) {

	
	}

	@Override
	public void onCancel() {

		Log.e("sinalog", "cancle");
		WeiboManager.instance().getHandler().sendEmptyMessage(2);
	}

	@Override
	public void onError(WeiboDialogError error) {

		Log.e("sinalog", error.toString());
		WeiboManager.instance().getHandler().sendEmptyMessage(2);
	}

	@Override
	public void onWeiboException(WeiboException exp) {

		Log.e("sinalog", exp.toString());
		WeiboManager.instance().getHandler().sendEmptyMessage(2);
	}

}
