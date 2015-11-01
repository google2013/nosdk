package org.nosdk.main;

import android.app.Application;

public class GameApplication extends Application {

	@Override
	public void onCreate()
	{
		super.onCreate();
		CommonSDKManager.getInstance().getSDKManager().initApplication( this  );
	}
}
