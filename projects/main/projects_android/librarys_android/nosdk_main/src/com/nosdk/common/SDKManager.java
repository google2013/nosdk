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

public class SDKManager implements ISDKManager {
	private float amount;
	private String payinfo;
	private Application application;
	private Activity activity;
	private ILoginCallback loginCallback;
    private static SDKManager instance;
	private SDKTYPE m_nSDKType = SDKTYPE.TYPE_NORMAL;
	private boolean isLogined;
	
    public static synchronized SDKManager getInstance() {  
        if (instance == null) {  
            instance = new SDKManager();  
        }  
        return instance;  
    }
    
	public SDKManager(  )
	{
//		m_nSDKType = SDKTYPE.TYPE_MI;
	}

	@Override
	public void goLogin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goSDKLogin(ILoginCallback callback) {
		// TODO Auto-generated method stub
		isLogined = true;
		loginCallback = callback;
		loginCallback.onLoginFished("");
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
	public void destroySDK(Activity activity) {
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
		return "000000000";
	}

	@Override
	public void initActivity(Activity act) {
		// TODO Auto-generated method stub
		activity = act;
	}

	@Override
	public void initApplication(Application app) {
		// TODO Auto-generated method stub
		application = app;
		
//		DemoUtils.intiTestImg( app );
	}
}
