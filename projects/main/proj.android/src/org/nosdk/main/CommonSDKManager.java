package org.nosdk.main;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.nosdk.common.ILoginCallback;
import com.nosdk.common.SDKManager;

public class CommonSDKManager extends Object{
	private final int MSG_LOGIN = 0;
	
	private static CommonSDKManager instance = null;
//	private static SDKManager sdkManager = null;
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			switch (msg.what) {
				case MSG_LOGIN: {
					getSDKManager().goSDKLogin( loginCallback );
//					SDKManager.getInstance().goSDKLogin(callback)Login( loginCallback );
					break;
				}
			}
			super.handleMessage(msg);
		}
	};
	
	private String payJson = "";
	private ILoginCallback loginCallback = new ILoginCallback()
	{

		@Override
		public void onLoginFished(String json) {
			// TODO Auto-generated method stub
			login( json );
		}

		@Override
		public void onLoginFailed(String Result) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onLoginCancel(String Result) {
			// TODO Auto-generated method stub
			
		}
	};
	
	public static CommonSDKManager getInstance()
	{
		if( instance == null )
		{
			instance = new CommonSDKManager();
		}
		return instance;
	}

	public static void testJNI()
	{
		Log.d("TTT" , "TTTTTTT");
	}
	
	public CommonSDKManager()
	{

	}
	
	public SDKManager getSDKManager()
	{
		return SDKManager.getInstance();
	}
	public native void login( String json );
	public native void logout();
	
	public void goCommonLogin()
	{
		try {
			if( getSDKManager().isLogined() ){
				return;
			}
			mHandler.sendEmptyMessage(MSG_LOGIN);

		} catch (Exception e) {

			Log.e("error", e.getMessage());
		}
	}
}
