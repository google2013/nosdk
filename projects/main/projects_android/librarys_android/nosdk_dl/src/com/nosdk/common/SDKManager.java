package com.nosdk.common;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Handler;

import com.downjoy.Downjoy;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.downjoy.CallbackListener;
import com.downjoy.DownjoyError;
import com.nosdk.common.ILoginCallback;
import com.nosdk.common.ISDKManager;
import com.nosdk.common.ISDKManager.SDKTYPE;

public class SDKManager implements ISDKManager {
	
	private Handler mHandler = null;
	private final int MSG_LOGIN = 0;
	private final int MSG_PAY = 1;
	private final int MSG_TO_LOGIN = 2;
	private final int MSG_TO_LOGOUT = 3;
	private final int MSG_TO_DLCENTER = 4;
	private SDKTYPE m_nSDKType = SDKTYPE.TYPE_NORMAL;
	private final String app_id = "817";
	private final String merchantId="388";
	private final String serverSeqNum ="1";
	private final String appKey = "jaqnKcLF";
	private Downjoy downjoy;
	private String tocken = null;
	private String roleName;
	boolean isLogin = false;
	String payinfo;
	int payamout;

	private float amount;
	public Activity activity;
	private ILoginCallback loginCallback;
    private static SDKManager instance;

    public static synchronized SDKManager getInstance() {  
        if (instance == null) {  
            instance = new SDKManager();  
        }  
        return instance;  
    }
    
	public SDKManager(  )
	{
		m_nSDKType = SDKTYPE.TYPE_DL;
	}

	public void init( Activity act )
	{
		activity = act;
		// TODO 每个商家的appid和appkey，必须请求成功，才能用其他的功能。
		// 如果失败，就不能调用登陆和支付等接口。
		 downjoy = Downjoy.getInstance( activity, merchantId, app_id,
	                serverSeqNum, appKey);

	        // 设置登录成功后属否显示当乐游戏中心的悬浮按钮
	        // 注意：
	        // 此处应在调用登录接口之前设置，默认值是true，即登录成功后自动显示当乐游戏中心的悬浮按钮。
	        // 如果此处设置为false，登录成功后，不显示当乐游戏中心的悬浮按钮。
	        downjoy.showDownjoyIconAfterLogined(true);
	       
	        downjoy.setInitLocation(Downjoy.LOCATION_CENTER_HORIZONTAL_BOTTOM);

	}
	
	@Override
	public void goLogin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goSDKLogin(ILoginCallback callback) {
		// TODO Auto-generated method stub
		loginCallback = callback;
		try {
			downjoy.openLoginDialog(activity, new CallbackListener(){
				
				/**
				 * 
				 */
//				private static final long serialVersionUID = 1L;

				@Override
                public void onLoginSuccess(Bundle bundle) {
					
                    String memberId = bundle
                            .getString(Downjoy.DJ_PREFIX_STR + "mid");
                    String username = bundle
                            .getString(Downjoy.DJ_PREFIX_STR + "username");
     
                    tocken = bundle.getString(Downjoy.DJ_PREFIX_STR
                            + "token");

                   roleName = memberId;
                   
                   JSONObject address = new JSONObject(); 
                   try{
//      		        	address.put("arg1",username);
//      		        	address.put("arg2",memberId);
//      		        	address.put("arg3",tocken);
      		        	String postinfo = "logindl?uin="+  memberId  +"&sessionID="+ tocken;
      		        	address.put("arg1",postinfo);
      		        	loginCallback.onLoginFished( address.toString() );
      		        }
      		        catch (JSONException e) {
   					// TODO Auto-generated catch block
      		        	e.printStackTrace();
      			 	}
                      
                   //login(username,memberId,tocken);
                }

                @Override
                public void onLoginError(DownjoyError error) {
                   
                	int errorCode = error.getMErrorCode();
                    String errorMsg = error.getMErrorMessage();
                    Log.e("loginerror1" ,errorMsg);
                    isLogin = false;
                    
                }

                @Override
                public void onError(Error error) {
                    String errorMessage = error.getMessage();
                    Log.e("loginerror1" ,errorMessage);
                    isLogin = false;

                }

				
			});
		}catch (Exception e) {

			Log.e("error", e.getMessage());
		}
	
	}
	@Override
	public void goLogout() {
		// TODO Auto-generated method stub
		isLogin = false;
		downjoy.logout(activity, new CallbackListener(){
		
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public void onLogoutSuccess(){
				
				
			}
			@Override 
			public void onLogoutError(DownjoyError error){
				
				
			}
			 @Override
			  public void onError(Error error){
				  
				  
			  }
		});
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
		int amount;
		try {
			JSONObject jsonObject=new JSONObject(json);
//			amount = jsonObject.getInt("amount");
//			payorder = jsonObject.getString("payorder");
			amount = jsonObject.getInt("amount");
			int serverID = jsonObject.getInt("serverid");
//			String serverName = jsonObject.getString("servername");
			int roleID = jsonObject.getInt("roleID");
			payinfo = String.format("%d.%d", serverID , roleID );
			payamout = amount;
//			payinfo = payorder;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		 downjoy.openPaymentDialog(activity, payamout,(payamout*10)+"元宝", payinfo, new  CallbackListener(){
			  
			  /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			/**
			 * 
			 */
			@Override
			  public void onPaymentSuccess(String orderNo){
				  
				  
			  }
			  @Override
			  public void onPaymentError(DownjoyError error,String orderNo){
				  
				  
			  }
			  @Override
			  public void onError(Error error){
				  
				  
			  }
		  });

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
		return isLogin;
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
	public boolean hasExit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNormalSDK() {
		// TODO Auto-generated method stub
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
	public String getSceneBackground() {
		// TODO Auto-generated method stub
		return "ccbfiles/gui_login_main.ccbi";
	}

	@Override
	public String getNodeSetting() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public boolean isHasShare() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHasGuestAccount() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHasLogoutAccess() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHasNodeSetting() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isHasFAQAccess() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHasAudoUpdate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHasSkipAccess() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasDealLoginAccess() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLogout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAddFriend(String json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelFriend(String json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLevelUp(String json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createRole(String json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendRoleInfo(String json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getProjectID() {
		// TODO Auto-generated method stub
		return app_id;
	}

//	@Override
//	public boolean hasExit() {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
