package com.crimoon.common;

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.nosdk.common.ILoginCallback;
import com.nosdk.common.SDKManager;
import com.nosdk.common.ISDKManager.SDKTYPE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

//import com.unionpay.mpay.views.r;
//import com.zz.sdk.SDKManager;

public class CommonSdkManager {
	
//	private Handler mHandler = null;
	private final int MSG_LOGIN = 0;
	private final int MSG_PAY = 1;
	private final int MSG_TO_LOGIN = 2;
	private final int MSG_TO_LOGOUT = 3;
	private final int MSG_TO_CENTER = 4;
	private static CommonSdkManager mSharedManager = null;
	protected String roleName ;
	private String payJson;
	boolean isLoginClicked = false;
	boolean isLogined = false;
	public ILoginCallback loginCallback = new ILoginCallback(){

		@Override
		public void onLoginFished(String json) {
			// TODO Auto-generated method stub
			isLogined = true;
			Log.d("TTT", "json is " + json );
			login( json );
		}

		@Override
		public void onLoginFailed(String Result) {
			// TODO Auto-generated method stub
			isLoginClicked = false;
		}

		@Override
		public void onLoginCancel(String Result) {
			// TODO Auto-generated method stub
			isLoginClicked = false;
		}
		
	};
	
	private Handler mHandler = null;
	
//	public CommonSdkManager getInstance()
//	{
//		if(mSharedManager == null )
//		{
//			mSharedManager = new CommonSdkManager();
//		}
//		return mSharedManager;
//	}
	
	public CommonSdkManager(){
		
		roleName ="00:00:00:00:00:00";
		mHandler = new Handler() {

			public void handleMessage(Message msg) {

				switch (msg.what) {

				case MSG_TO_LOGIN: {
					SDKManager.getInstance().goSDKLogin( loginCallback );
					break;
				}
					
				case MSG_TO_LOGOUT: {
					isLogined = false;
					SDKManager.getInstance().goLogout();
					break;
				}
					
				case MSG_PAY:{
					SDKManager.getInstance().setPay(payJson);
					break;
					
				}

				case MSG_TO_CENTER:{
					SDKManager.getInstance().showCenter();
				}
				break;
				}
				super.handleMessage(msg);
			}
		};
	}
	
	public static CommonSdkManager instance() {

		if (mSharedManager == null) {

			mSharedManager = new CommonSdkManager();
		}
		return mSharedManager;
	}
	
	void gologin()
	{
		try {
			if( isLoginClicked ){
				
				return;
			}
			isLoginClicked = true;
			mHandler.sendEmptyMessage(MSG_TO_LOGIN);

		} catch (Exception e) {

			Log.e("error", e.getMessage());
		}
	}
	
	void goLogout()
	{
		isLoginClicked = false;
		isLogined = false;
		SDKManager.getInstance().goLogout();
	}
	
	void showFloatWindow()
	{
		SDKManager.getInstance().showFloatWindow();
	}
	
	void hideFloatWindow()
	{
		SDKManager.getInstance().hideFloatWindow();
	}
	
	public native void login( String json );
	public native void logout();
	
	void pay( String json )
	{
		try {
//			JSONObject jsonObject=new JSONObject(json);
//			roleName = jsonObject.getString("uname");
//			mamount = jsonObject.getInt("amount");
//			payinfo = jsonObject.getString("payinfo");	
			//次处原先就是如此，传的是servename，但叫serverId，payInfo中本身已有serveId信息
//			serverID = jsonObject.getString("servername");
			payJson = json;
			mHandler.sendEmptyMessage(MSG_PAY);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getRoleName(){
		
		return roleName;
	}
	public void initSDK(ProjectTK context){
		SDKManager.getInstance().init( context );
	}
	public void destroySDK(ProjectTK context){
		
		SDKManager.getInstance().destroySDK( context );
	}
	public void exit(){
		
	}
	public void onExit(){
		if( !SDKManager.getInstance().hasExit() )
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(
					ProjectTK.getContext());
			builder.setMessage("确认退出游戏吗？");

			builder.setTitle("提示");
			builder.setPositiveButton("确认", new AlertDialog.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					((ProjectTK)ProjectTK.getContext()).sdkManager.exit();
					Intent startMain = new Intent(Intent.ACTION_MAIN);
	                startMain.addCategory(Intent.CATEGORY_HOME);
	                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	                ProjectTK.getContext().startActivity(startMain);
	                 android.os.Process.killProcess(android.os.Process.myPid());
				}

			});

			builder.setNegativeButton("取消", new AlertDialog.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int arg1) {

					dialog.dismiss();

				}
			});

			builder.create().show();	
		}
	}
	
	public void onResume(){
		WeChatSDkManager.getInstance().isSend = false;
		
	}
	public void onPause(){
		SDKManager.getInstance().onPause();
		
	}
	public void onStop(){
		
		SDKManager.getInstance().onStop();
	}
	public void onStart(){
		
		SDKManager.getInstance().onStart();
	}
	
	public boolean hasExit() {
		// TODO Auto-generated method stub
		return SDKManager.getInstance().hasExit();
	}

	
	public boolean isNormalSDK() {
		// TODO Auto-generated method stub
		return SDKManager.getInstance().isNormalSDK();
	}

	public boolean isHasLoginAccess() {
		// TODO Auto-generated method stub
		return SDKManager.getInstance().isHasLoginAccess();
	}

	public int getSDKType() {
		// TODO Auto-generated method stub
		return SDKManager.getInstance().getSDKType();
	}
	
	public String getSceneBackground()
	{
		return SDKManager.getInstance().getSceneBackground();
	}
//	@Override
//	public boolean hasExit() {
//		// TODO Auto-generated method stub
//		return false;
//	}

	public boolean isHasNodeSetting() {
		// TODO Auto-generated method stub
		return SDKManager.getInstance().isHasNodeSetting();
	}
	
	public String getNodeSetting() {
		// TODO Auto-generated method stub
		return SDKManager.getInstance().getNodeSetting();
	}

	public boolean isHasShare() {
		// TODO Auto-generated method stub
		return SDKManager.getInstance().isHasShare();
	}

	public boolean isHasGuestAccount() {
		// TODO Auto-generated method stub
		return SDKManager.getInstance().isHasGuestAccount();
	}

	public boolean isHasLogoutAccess() {
		// TODO Auto-generated method stub
		return SDKManager.getInstance().isHasLogoutAccess();
	}

	public boolean isHasFAQAccess() {
		// TODO Auto-generated method stub
		return SDKManager.getInstance().isHasFAQAccess();
	}

	boolean isHasAudoUpdate() {
		// TODO Auto-generated method stub
		return SDKManager.getInstance().isHasAudoUpdate();
	}

	public boolean isHasSkipAccess() {
		// TODO Auto-generated method stub
		return SDKManager.getInstance().isHasSkipAccess();
	}

	public void onLogout() {
		// TODO Auto-generated method stub
		SDKManager.getInstance().onLogout();
	}

	public void onAddFriend(String json) {
		// TODO Auto-generated method stub
		SDKManager.getInstance().onAddFriend( json );
	}

	public void onDelFriend(String json) {
		// TODO Auto-generated method stub
		SDKManager.getInstance().onDelFriend(json);
	}

	public void onLevelUp( String json ) {
		// TODO Auto-generated method stub
		SDKManager.getInstance().onLevelUp( json );
	}

	public boolean hasDealLoginAccess() {
		// TODO Auto-generated method stub
		return SDKManager.getInstance().hasDealLoginAccess();
	}
	
	public void createRole( String json )
	{
		SDKManager.getInstance().createRole(json);
	}
	
	public void sendRoleInfo( String json )
	{
		SDKManager.getInstance().sendRoleInfo(json);
	}
	
	public String getProjectID()
	{
		return SDKManager.getInstance().getProjectID();
	}
}


