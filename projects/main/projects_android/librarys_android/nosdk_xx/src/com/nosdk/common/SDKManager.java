package com.nosdk.common;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.flamingo.sdk.access.GPApiFactory;
import com.flamingo.sdk.access.GPPayResult;
import com.flamingo.sdk.access.GPSDKGamePayment;
import com.flamingo.sdk.access.GPSDKInitResult;
import com.flamingo.sdk.access.GPUserResult;
import com.flamingo.sdk.access.IGPPayObsv;
import com.flamingo.sdk.access.IGPSDKInitObsv;
import com.flamingo.sdk.access.IGPUserObsv;
import com.nosdk.common.ILoginCallback;
import com.nosdk.common.ISDKManager;
import com.nosdk.common.ISDKManager.SDKTYPE;

public class SDKManager implements ISDKManager {
	private float amount;
	private String payinfo;
	private Activity activity;
	private Application application;
	private ILoginCallback loginCallback;
    private static SDKManager instance;
	private SDKTYPE m_nSDKType = SDKTYPE.TYPE_NORMAL;
	private String APP_ID = "101138";
	private String APP_KEY = "TKSEIHILN2WMGP8S";
	private IGPUserObsv mUserObsv = new IGPUserObsv() {
		@Override
		public void onFinish(final GPUserResult result) {
        	JSONObject json_object = new JSONObject();
        	try {
			    String postinfo = "login_xxard?uid=";
			    postinfo += GPApiFactory.getGPApi().getLoginUin();
			    postinfo += "&sessionID=";
			    postinfo += GPApiFactory.getGPApi().getLoginToken();
			    json_object.put("arg1", postinfo );
				loginCallback.onLoginFished( json_object.toString() );
				showFloatWindow();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	};
	
	private IGPPayObsv obsv = new IGPPayObsv() {
		@Override
		public void onPayFinish(GPPayResult payResult) {

			if (payResult == null) {
				return;
			}
		}
	};
	
    public static synchronized SDKManager getInstance() {  
        if (instance == null) {  
            instance = new SDKManager();  
        }  
        return instance;  
    }
    
	public SDKManager(  )
	{
		m_nSDKType = SDKTYPE.TYPE_XXARD;
	}

	public void initActivity( Activity act )
	{
		activity = act;
		// TODO 每个商家的appid和appkey，必须请求成功，才能用其他的功能。
		// 如果失败，就不能调用登陆和支付等接口。
		GPApiFactory.getGPApi().initSdk(activity, APP_ID, APP_KEY,
				new IGPSDKInitObsv() {
					@Override
					public void onInitFinish(GPSDKInitResult initResult) {
						
					}
				});	
	}
	
	public void initApplication( Application app )
	{
		application = app;
	}
	
	@Override
	public void goLogin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goSDKLogin(ILoginCallback callback) {
		// TODO Auto-generated method stub
		loginCallback = callback;
		GPApiFactory.getGPApi().login( activity , mUserObsv);
	}

	@Override
	public void goLogout() {
		// TODO Auto-generated method stub
		GPApiFactory.getGPApi().logout();
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
//			payinfo = String.format("%d.%d.%d", serverID , roleID , System.currentTimeMillis() );

			GPSDKGamePayment payParam = new GPSDKGamePayment();
			payParam.mItemName = amount + "元宝";
			payParam.mPaymentDes = amount + "元宝";
			payParam.mItemPrice = amount;
			payParam.mCurrentActivity = activity;
			payParam.mSerialNumber = "" + System.currentTimeMillis();
			payParam.mItemId = "" + System.currentTimeMillis();
			payParam.mReserved = payinfo;

			GPApiFactory.getGPApi().buy(payParam, obsv);
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
		return GPApiFactory.getGPApi().isLogin();
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
		return APP_ID;
	}
}
