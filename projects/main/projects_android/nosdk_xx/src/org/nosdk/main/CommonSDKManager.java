package org.nosdk.main;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.nosdk.common.ICommonSDKManager;
import com.nosdk.common.ILoginCallback;
import com.nosdk.common.ISDKManager;
import com.nosdk.common.SDKManager;

public class CommonSDKManager extends Object implements ICommonSDKManager{
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
	
	public CommonSDKManager()
	{

	}
	
	public SDKManager getSDKManager()
	{
		return SDKManager.getInstance();
	}
	public native void login( String json );
	public native void logout();
	
	@Override
	public void goCommonLogin()
	{
		getSDKManager().goSDKLogin(loginCallback);
	}

	@Override
	public void goDirectLogin( String json )
	{
//		getSDKManager().goSDKLogin(loginCallback);
		login( json );
	}
	
	@Override
	public void goCommonLogout() {
		// TODO Auto-generated method stub
		logout();
	}
}
