package com.nosdk.common;

import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.baidu.gamesdk.BDGameSDK;
import com.baidu.gamesdk.BDGameSDKSetting;
import com.baidu.gamesdk.BDGameSDKSetting.Domain;
import com.baidu.gamesdk.BDGameSDKSetting.Orientation;
import com.baidu.gamesdk.IResponse;
import com.baidu.gamesdk.ResultCode;
import com.baidu.gamesdk.BDGameSDK;
import com.baidu.gamesdk.IResponse;
import com.baidu.gamesdk.ResultCode;
import com.baidu.platformsdk.PayOrderInfo;
import com.baidu.gamesdk.ActivityAdPage;
import com.baidu.gamesdk.ActivityAdPage.Listener;
import com.baidu.gamesdk.ActivityAnalytics;
import com.baidu.gamesdk.BDGameSDK;

import com.nosdk.common.ILoginCallback;
import com.nosdk.common.ISDKManager;
import com.nosdk.common.ISDKManager.SDKTYPE;

public class SDKManager implements ISDKManager {
	private ICommonSDKManager commonManager;
	private float amount;
	private String payinfo;
	private Application application;
	private Activity activity;
	private ILoginCallback loginCallback;
    private static SDKManager instance;
	private SDKTYPE m_nSDKType = SDKTYPE.TYPE_NORMAL;
	private boolean isLogined;
	private ActivityAdPage mActivityAdPage;
	private ActivityAnalytics mActivityAnalytics;
	
    public static synchronized SDKManager getInstance() {  
        if (instance == null) {  
            instance = new SDKManager();  
        }  
        return instance;  
    }
    
	public SDKManager(  )
	{
		m_nSDKType = SDKTYPE.TYPE_DK;
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
//		loginCallback.onLoginFished("");
		BDGameSDK.login(new IResponse<Void>() {
			
			@Override
			public void onResponse(int resultCode, String resultDesc, Void extraData) { 
				String hint = "";
				switch(resultCode){
				case ResultCode.LOGIN_SUCCESS:
		        	JSONObject json_object = new JSONObject();
		        	try {
		    		    String postinfo = "login_miard?uid=";
		    		    postinfo += BDGameSDK.getLoginUid();
		    		    postinfo += "&SessionId=";
		    		    postinfo += BDGameSDK.getLoginAccessToken();
		    		    json_object.put("arg1", postinfo );
		    		    isLogined = true;
//		    		    commonManager.goCommonLogin( json_object.toString() );
		    		    loginCallback.onLoginFished(json_object.toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					loginCallback.onLoginFished("");
//                    finish();
					break; 
				case ResultCode.LOGIN_CANCEL:
//					loginBtn.setEnabled(true);
					hint = "取消登录";
					loginCallback.onLoginCancel("");
					break;
				case ResultCode.LOGIN_FAIL:
				default:	
//					loginBtn.setEnabled(true);
					hint = "登录失败";	
					loginCallback.onLoginFailed("");
				} 
//				Toast.makeText(getApplicationContext(), hint, Toast.LENGTH_LONG).show(); 
			}
		});
	}

	@Override
	public void goLogout() {
		// TODO Auto-generated method stub
		isLogined = false;
//		MiCommplatform.getInstance().mi
		BDGameSDK.logout();
		hideFloatWindow();
	}

	@Override
	public void showFloatWindow() {
		// TODO Auto-generated method stub
		BDGameSDK.showFloatView( activity );
	}

	@Override
	public void hideFloatWindow() {
		// TODO Auto-generated method stub
		
		BDGameSDK.closeFloatView(activity);
	}

	@Override
	public void setPay(String json) {
		// TODO Auto-generated method stub
		try {
			JSONObject jsonObject=new JSONObject(json);
			amount = jsonObject.getInt("mount");
			int serverID = 0;
			int roleID = 2331;
			payinfo = jsonObject.getString("payinfo");
			
			PayOrderInfo payOrderInfo = new PayOrderInfo(); 
			String cpOrderId = UUID.randomUUID().toString();
			payOrderInfo.setCooperatorOrderSerial(cpOrderId); //CP订单号 
			payOrderInfo.setProductName("金币"); //商品名称 
			payOrderInfo.setTotalPriceCent( (long) amount );//非定额支付这里填0 
			payOrderInfo.setRatio(10); //兑换比例 
			payOrderInfo.setExtInfo( payinfo );//该字段在支付通知中原样返回,不超过500个字符
			
			BDGameSDK.pay(payOrderInfo, null, 
					new IResponse<PayOrderInfo>(){
				  
						@Override
						public void onResponse(int resultCode, String resultDesc,
								PayOrderInfo extraData) {
//							alertDialog.cancel();
							String resultStr = "";
							switch(resultCode){
							case ResultCode.PAY_SUCCESS://支付成功
								resultStr = "支付成功:" + resultDesc;
								break;
							case ResultCode.PAY_CANCEL://订单支付取消
								resultStr = "取消支付";
								break;	
							case ResultCode.PAY_FAIL://订单支付失败
								resultStr = "支付失败：" + resultDesc;
								break;	
							case ResultCode.PAY_SUBMIT_ORDER://订单已经提交，支付结果未知（比如：已经请求了，但是查询超时）
								resultStr = "订单已经提交，支付结果未知";
								break;	
							}
//							Toast.makeText(getApplicationContext(), resultStr, Toast.LENGTH_LONG).show();
							 
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
		return BDGameSDK.isLogined();
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		mActivityAdPage.onResume(); 
		mActivityAnalytics.onResume();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		mActivityAdPage.onPause();
		mActivityAnalytics.onPause();
	}

	@Override
	public void destroySDK(Activity activity) {
		// TODO Auto-generated method stub
		BDGameSDK.destroy();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		mActivityAdPage.onStop();
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
		return "3067515";
	}

	@Override
	public void initActivity(Activity act , ICommonSDKManager instance ) {
		// TODO Auto-generated method stub
		activity = act;
		commonManager = instance;
		
		BDGameSDK.setSuspendWindowChangeAccountListener(new IResponse<Void>(){

			@Override
			public void onResponse(int resultCode, String resultDesc,
					Void extraData) { 
				 switch(resultCode){
				 case ResultCode.LOGIN_SUCCESS:
					 commonManager.goCommonLogout();
//					 goLogout();
			        	JSONObject json_object = new JSONObject();
			        	try {
			    		    String postinfo = "login_miard?uid=";
			    		    postinfo += BDGameSDK.getLoginUid();
			    		    postinfo += "&SessionId=";
			    		    postinfo += BDGameSDK.getLoginAccessToken();
			    		    json_object.put("arg1", postinfo );
			    		    isLogined = true;
			    		    commonManager.goCommonLogin( json_object.toString() );
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					 
					 //TODO 登录成功，不管之前是什么登录状态，游戏内部都要切换成新的用户
					 break;
				 case ResultCode.LOGIN_FAIL:
					//TODO 登录失败，游戏内部之前如果是已经登录的，要清楚自己记录的登录状态，设置成未登录。如果之前未登录，不用处理。
					 break;
				 case ResultCode.LOGIN_CANCEL:					 
					//TODO 取消，操作前后的登录状态没变化
					 break; 
					 
				 } 
			}
			
		});
		
		mActivityAnalytics = new ActivityAnalytics( act );
		
		mActivityAdPage = new ActivityAdPage( act , new Listener(){

			@Override
			public void onClose() {
				// TODO 关闭暂停页, CP可以让玩家继续游戏
			}
			
		}); 
	}

	@Override
	public void initApplication(Application app) {
		// TODO Auto-generated method stub
		application = app;
		
//		DemoUtils.intiTestImg( app );
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		mActivityAdPage.onDestroy();
		destroySDK( activity );
	}
}
