package org.nosdk.main;

import com.baidu.gamesdk.BDGameApplication;
import com.baidu.gamesdk.BDGameSDK;
import com.baidu.gamesdk.BDGameSDKSetting;
import com.baidu.gamesdk.IResponse;
import com.baidu.gamesdk.ResultCode;
import com.baidu.gamesdk.BDGameSDKSetting.Domain;
import com.baidu.gamesdk.BDGameSDKSetting.Orientation;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

public class GameApplication extends Application {

	@Override
	public void onCreate()
	{
		super.onCreate();
		
		CommonSDKManager.getInstance().getSDKManager().initApplication( this  );
	}
}
