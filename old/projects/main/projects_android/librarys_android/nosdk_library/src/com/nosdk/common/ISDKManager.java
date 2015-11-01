package com.nosdk.common;

import android.app.Activity;
import android.app.Application;

public interface ISDKManager {
	enum SDKTYPE
	{
		TYPE_NORMAL, // 1
 	    TYPE_91,
	    TYPE_UC,
	    TYPE_DL,
	    TYPE_ZZ,
	    TYPE_360,
	    TYPE_WDJ,
	    TYPE_DK,
	    TYPE_MI,
	    TYPE_AZ,
	    TYPE_PP,
	    TYPE_KY,
	    TYPE_ZR,
	    TYPE_IT,
	    TYPE_TBT,
	    TYPE_HW,
	    TYPE_DJ,
	    TYPE_SINA,
	    TYPE_LENOVO,
	    TYPE_OPPO,
	    TYPE_SOGOU,
	    TYPE_JIFENG,
	    TYPE_YYB,
	    TYPE_YYH,
	    TYPE_VIVO,
	    TYPE_TECENT,
	    TYPE_OW,
	    TYPE_MZ,
	    TYPE_JL , 	//34
	    TYPE_QLA , 	//35
	    TYPE_KW ,	//36
	    TYPE_JRZ ,  //37
	    TYPE_JR , 	//38
	    TYPE_HM ,	//39
	    TYPE_QD ,	//40
	    TYPE_WH ,	//41
	    TYPE_KD ,	//42
	    TYPE_QIDIAN ,	//43
	    TYPE_HUIZHI ,	//44
	    TYPE_PKPK ,		//45
	    TYPE_JODO ,		//46
	    TYPE_PPS ,		//47
	    TYPE_I4 ,		//48
	    TYPE_BF ,		//49
	    TYPE_MM ,		//53
	    TYPE_DX ,		//54
	    TYPE_LT ,		//55
	    TYPE_II ,		//56
	    TYPE_XX ,		//57
	    TYPE_XXARD ,	//58
	}
	
	///暴露给CommonSDKManager的接口
	public void goSDKLogin( ILoginCallback callback );
	public void setPay( String json );	//支付,json用来传递参数
	public void showBBS();				//个人中心和论坛
	public void showCenter();			
	public boolean isGuest();			//账户类型，一般用不到
	public boolean isLogined();			//是否登录
	public void goLogout();				//退出登录
	public void showFloatWindow();		//显示浮窗
	public void hideFloatWindow();		//隐藏浮窗
	public boolean isNormalSDK();		//是否是sdk平台
	public boolean isHasLoginAccess();	//是否有登录接口
	public int getSDKType();			//获取接入的sdk类型
	///
	
	///SDKManager自用接口
	public void goLogin();				//登录
	public void onExit();
	public void onResume();
	public void onPause();
	public void destroySDK(Activity activity);
	public void onStop();
	public void onStart();
	public void onDestroy();
	///

	///other
	public String getProjectID();		//不同平台projectID不同，用来通知服务器
	///
	
	public void initActivity( Activity act , ICommonSDKManager instance);
	public void initApplication( Application app );
}
