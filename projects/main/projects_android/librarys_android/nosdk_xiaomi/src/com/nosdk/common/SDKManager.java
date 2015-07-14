package com.nosdk.common;

import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.nosdk.common.ILoginCallback;
import com.nosdk.common.ISDKManager;
import com.nosdk.common.ISDKManager.SDKTYPE;

import com.xiaomi.gamecenter.sdk.MiCommplatform;
import com.xiaomi.gamecenter.sdk.MiErrorCode;
import com.xiaomi.gamecenter.sdk.OnLoginProcessListener;
import com.xiaomi.gamecenter.sdk.OnPayProcessListener;
import com.xiaomi.gamecenter.sdk.entry.MiAccountInfo;
import com.xiaomi.gamecenter.sdk.entry.MiAppInfo;
import com.xiaomi.gamecenter.sdk.entry.MiBuyInfo;

public class SDKManager implements ISDKManager {
	private float amount;
	private String payinfo;
	private Application application;
	private Activity activity;
	private ILoginCallback loginCallback;
	private ICommonSDKManager commonManager;
    private static SDKManager instance;
	private SDKTYPE m_nSDKType = SDKTYPE.TYPE_NORMAL;
	private boolean isLogined;
	public static MiAppInfo appInfo;
	
	
    public static synchronized SDKManager getInstance() {  
        if (instance == null) {  
            instance = new SDKManager();  
        }  
        return instance;  
    }
    
	public SDKManager(  )
	{
		m_nSDKType = SDKTYPE.TYPE_MI;
	}

	@Override
	public void goLogin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goSDKLogin(ILoginCallback callback) {
		// TODO Auto-generated method stub
		loginCallback = callback;
		MiCommplatform.getInstance().miLogin( activity , new OnLoginProcessListener()
		{

			@Override
			public void finishLoginProcess(int arg0, MiAccountInfo arg1) {
				// TODO Auto-generated method stub
				if ( MiErrorCode.MI_XIAOMI_PAYMENT_SUCCESS == arg0 )
				{
		        	JSONObject json_object = new JSONObject();
		        	try {
		    		    String postinfo = "login_miard?uid=";
		    		    postinfo += arg1.getUid();
		    		    postinfo += "&SessionId=";
		    		    postinfo += arg1.getSessionId();
		    		    postinfo += "&nickname=";
		    		    postinfo += arg1.getNikename();
		    		    json_object.put("arg1", postinfo );
		    		    loginCallback.onLoginFished( json_object.toString() );
		    		    isLogined = true;
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if ( MiErrorCode.MI_XIAOMI_PAYMENT_ERROR_ACTION_EXECUTED == arg0 )
				{
				}
				else
				{
					loginCallback.onLoginFailed("");
				}
			}
			
		});
	}

	@Override
	public void goLogout() {
		// TODO Auto-generated method stub
		isLogined = false;
//		MiCommplatform.getInstance().mi
		hideFloatWindow();
	}

	@Override
	public void showFloatWindow() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hideFloatWindow() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setPay(String json) {
		// TODO Auto-generated method stub
//		roleName = jsonObject.getString("uname");
		Log.d("TTT" , json );
		try {
			JSONObject jsonObject=new JSONObject(json);
			amount = jsonObject.getInt("mount");
			int serverID = 0;
			int roleID = 2331;
			payinfo = jsonObject.getString("payinfo");

			MiBuyInfo miBuyInfo = new MiBuyInfo();
			miBuyInfo.setProductCode( amount + "元宝" );
			miBuyInfo.setCount( (int) amount );
			miBuyInfo.setCpOrderId( UUID.randomUUID().toString() + System.currentTimeMillis() + "EEE" );
			
			MiCommplatform.getInstance().miUniPay( activity , miBuyInfo, new OnPayProcessListener()
			{
				@Override
				public void finishPayProcess(int arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void showBBS() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showCenter() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isGuest() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLogined() {
		return isLogined;
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroySDK( Activity activity ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isNormalSDK() {
		// TODO Auto-generated method stub
		if( m_nSDKType == SDKTYPE.TYPE_NORMAL )
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean isHasLoginAccess() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getSDKType() {
		// TODO Auto-generated method stub
		return m_nSDKType.ordinal();
	}
	
	@Override
	public String getProjectID()
	{
		return "2882303761517239138";
	}

	@Override
	public void initApplication(Application app) {
		// TODO Auto-generated method stub
		application = app;
		/** SDK初始化 */
		appInfo = new MiAppInfo();
		appInfo.setAppId( "2882303761517239138" );
		appInfo.setAppKey( "5691723970138" );
		MiCommplatform.Init( app, appInfo );
		
//		DemoUtils.intiTestImg( app );
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		destroySDK( activity );
	}

	@Override
	public void initActivity(Activity act, ICommonSDKManager instance) {
		// TODO Auto-generated method stub
		activity = act;
		commonManager = instance;
	}
}
