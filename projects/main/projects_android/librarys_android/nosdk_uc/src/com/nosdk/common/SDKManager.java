package com.nosdk.common;

import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;

import cn.uc.gamesdk.UCCallbackListener;
import cn.uc.gamesdk.UCCallbackListenerNullException;
import cn.uc.gamesdk.UCFloatButtonCreateException;
import cn.uc.gamesdk.UCGameSDK;
import cn.uc.gamesdk.UCGameSDKStatusCode;
import cn.uc.gamesdk.UCLogLevel;
import cn.uc.gamesdk.UCOrientation;
import cn.uc.gamesdk.info.ExInfo;
import cn.uc.gamesdk.info.FeatureSwitch;
import cn.uc.gamesdk.info.GameParamInfo;
import cn.uc.gamesdk.info.OrderInfo;
import cn.uc.gamesdk.info.PaymentInfo;

import com.nosdk.common.ILoginCallback;
import com.nosdk.common.ISDKManager;
import com.nosdk.common.ISDKManager.SDKTYPE;

public class SDKManager implements ISDKManager {
	private String TAG = "TTT";
	private float amount;
	private String payinfo;
	private Application application;
	private Activity activity;
	private ILoginCallback loginCallback;
    private static SDKManager instance;
	private SDKTYPE m_nSDKType = SDKTYPE.TYPE_NORMAL;
	private boolean isLogined;
	private ICommonSDKManager commonManager;
	
    public static synchronized SDKManager getInstance() {  
        if (instance == null) {  
            instance = new SDKManager();  
        }  
        return instance;  
    }
    
	public SDKManager(  )
	{
		m_nSDKType = SDKTYPE.TYPE_UC;
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
        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                try {
            		try {
            			UCGameSDK.defaultSDK().login( activity , new UCCallbackListener<String>(){

            				@Override
            				public void callback(int code, String msg) {
            					// TODO Auto-generated method stub
            			        switch (code) {
            			        case UCGameSDKStatusCode.SUCCESS:
            			        {
            			        	JSONObject json_object = new JSONObject();
            			        	try {
            						    String postinfo = "login_xxard?uid=";
            						    postinfo += UCGameSDK.defaultSDK().getSid();
            						    json_object.put("arg1", postinfo );
            						    isLogined = true;
            							loginCallback.onLoginFished( json_object.toString() );
            							showFloatWindow();
            						} catch (JSONException e) {
            							// TODO Auto-generated catch block
            							e.printStackTrace();
            						}
            			            //String sid = UCGameSDK.defaultSDK().getSid();
            			            break;
            			        }
            			        case UCGameSDKStatusCode.NO_INIT:
            			        	loginCallback.onLoginFailed("");
            			            break;
            			        case UCGameSDKStatusCode.LOGIN_EXIT:
            			        	loginCallback.onLoginCancel("");
            			            break;
            			        default:
            			            break;

            			        }
            				}
            				
            			});
            		} catch (UCCallbackListenerNullException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        });

	}

	@Override
	public void goLogout() {
		// TODO Auto-generated method stub
		isLogined = false;
		try {
			UCGameSDK.defaultSDK().logout();
		} catch (UCCallbackListenerNullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		MiCommplatform.getInstance().mi
//		hideFloatWindow();
	}

	@Override
	public void showFloatWindow() {
		// TODO Auto-generated method stub
        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                try {
        			UCGameSDK.defaultSDK().createFloatButton( activity , new UCCallbackListener<String>()
        					{

        						@Override
        						public void callback(int code, String msg) {
        							// TODO Auto-generated method stub
        							Log.d(TAG, "callback float menu notification to C++, code=" + code + ", msg=" + msg);
        						}
        				
        					});
        			UCGameSDK.defaultSDK().showFloatButton( activity , 30 , 30 , true );
                } catch (UCCallbackListenerNullException e) {
                    Log.e(TAG, e.getMessage(), e);
                } catch (UCFloatButtonCreateException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        });
	}

	@Override
	public void hideFloatWindow() {
		// TODO Auto-generated method stub
		activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                UCGameSDK.defaultSDK().destoryFloatButton(activity);
            }
        });
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
//			payinfo = String.format("%d.%d.%d", serverID , roleID , System.currentTimeMillis() );

            PaymentInfo payInfo = new PaymentInfo();
            payInfo.setAllowContinuousPay( true );
            payInfo.setAmount(amount);
            payInfo.setServerId(1333);
            payInfo.setRoleId("12124");
            payInfo.setRoleName("test");
            payInfo.setGrade("21");
            payInfo.setCustomInfo(payinfo);
            payInfo.setNotifyUrl("");// 加入支付回调地址
            payInfo.setTransactionNumCP( payinfo );//加入自有交易号

            try {
                UCGameSDK.defaultSDK().pay( activity .getApplicationContext(), payInfo, new UCCallbackListener<OrderInfo>()
                		{

							@Override
							public void callback(int code, OrderInfo orderInfo) {
								// TODO Auto-generated method stub
								String text = "";
						        switch (code) {
						        case UCGameSDKStatusCode.SUCCESS:
						            if (orderInfo != null) {
						                String orderId = orderInfo.getOrderId();
						                float amount = orderInfo.getOrderAmount();
						                int payway = orderInfo.getPayWay();
						                String paywayName = orderInfo.getPayWayName();
						                //
						            } else {
						                Log.e(TAG, "Received empty order result");
						            }

						            break;
						        case UCGameSDKStatusCode.NO_INIT:
						            text = "Paying failed: no init";
						            Log.e(TAG, text);
						            break;
						        case UCGameSDKStatusCode.PAY_USER_EXIT:
						            text = "User exit the paying page, return to game page.";
						            Log.d(TAG, text);
						            break;
						        default:
						            text = "Unknown paying result code: code=" + code;
						            Log.e(TAG, text);
						            break;

						        }
							}
                	
                		});
                //} catch (UCCallbackListenerNullException e) {
                //    e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, e.getMessage(), e);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage(), e);
        }
	}

	@Override
	public void showBBS() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showCenter() {
		// TODO Auto-generated method stub
		try {
			UCGameSDK.defaultSDK().enterUserCenter( activity.getApplicationContext(), new UCCallbackListener<String>(){

				@Override
				public void callback(int code, String data) {
					// TODO Auto-generated method stub
			        switch (code) {
			        case UCGameSDKStatusCode.SUCCESS:
//			            msg = "九游社区正常退出";
			            break;

			        case UCGameSDKStatusCode.NO_INIT:
//			            msg = "调用九游社区失败，没有初始化";
			            break;
			        case UCGameSDKStatusCode.NO_LOGIN:
//			            msg = "调用九游社区失败，没有登录";
			            break;
			        default:
//			            msg = "";
			            break;
			        }
				}
				
			});
		} catch (UCCallbackListenerNullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                try {
                    UCGameSDK.defaultSDK().exitSDK( activity, new UCCallbackListener<String>(){

						@Override
						public void callback(int code, String msg) {
							// TODO Auto-generated method stub
					        switch (code) {
					        case UCGameSDKStatusCode.SDK_EXIT:
					        {
								Intent home = new Intent(Intent.ACTION_MAIN);
								home.addCategory(Intent.CATEGORY_HOME);
								activity.startActivity(home);
								activity.finish();
					            break;
					        }
					        case UCGameSDKStatusCode.SDK_EXIT_CONTINUE:
					            break;
					        default:
					            break;
					        }
						}});
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        });
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
	public void destroySDK(Activity act) {
		// TODO Auto-generated method stub

//		UCGameSDK.defaultSDK().exitSDK( activity , new UCCallbackListener<String>(){
//
//			@Override
//			public void callback(int code, String msg) {
//				// TODO Auto-generated method stub
//		        switch (code) {
//		        case UCGameSDKStatusCode.SDK_EXIT:
//		            break;
//		        case UCGameSDKStatusCode.SDK_EXIT_CONTINUE:
//		            break;
//		        default:
//		            break;
//		        }
//			}
//			
//		});
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
	public void initApplication(Application app) {
		// TODO Auto-generated method stub
		application = app;
		
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
		
		UCGameSDK.defaultSDK().setLogLevel(UCLogLevel.DEBUG);
//		UCOrientation.LANDSCAPE;
        UCGameSDK.defaultSDK().setOrientation(UCOrientation.LANDSCAPE);
        try {
            final GameParamInfo gp = new GameParamInfo();
            gp.setFeatureSwitch(new FeatureSwitch( true , true ));
            gp.setGameId(119474);
            gp.setCpId(20087);
            gp.setServerId(1333);
            gp.setServerName("测试服");

            ExInfo ex = new ExInfo();
            //ex.setCpServiceContact("");
            gp.setExInfo(ex);

            UCLogLevel loglevel = UCLogLevel.DEBUG;
            final UCLogLevel ucloglevel = loglevel;

            UCGameSDK.defaultSDK().setLogoutNotifyListener( new UCCallbackListener<String> (){

				@Override
				public void callback(int arg0, String arg1) {
					// TODO Auto-generated method stub
//					goLogout();
//					commonManager.goCommonLogout();
				}});

            new android.os.Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    // ATTENTION: 初始化调用需要放在主线程中
                    try {
                        UCGameSDK.defaultSDK().initSDK( activity, ucloglevel, true, gp, new UCCallbackListener<String>(){

							@Override
							public void callback(int code, String msg) {
								// TODO Auto-generated method stub
						        switch (code) {
						        case UCGameSDKStatusCode.SUCCESS:
						            // 初始化成功
						            break;
						        case UCGameSDKStatusCode.INIT_FAIL:
						            break;
						        case UCGameSDKStatusCode.LOGIN_EXIT:
						            //addOutputResult("login-exit", String.valueOf(code));
						            break;
						        default:
						            //addOutputResult("login-fail", String.valueOf(code));
						            break;
						        }
							}});
                    } catch (UCCallbackListenerNullException e) {
                        e.printStackTrace();
                    }
                }
            });

            //} catch (UCCallbackListenerNullException e) {
            //    e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage(), e);
        }
	}
}
