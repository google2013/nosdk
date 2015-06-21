package com.nosdk.common;

import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.ConstantsAPI;
import com.tencent.mm.sdk.openapi.ShowMessageFromWX;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXAppExtendObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
	

	public static final int MSG_TO_WEIXINSUCCESS = 4;
	public static final int MSG_TO_FAIL = 6;
	String APP_ID = "wx5cfdad55c16a8ac0";
    private IWXAPI api;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        APP_ID = getAppIdNative();
       	api = WXAPIFactory.createWXAPI(this,  APP_ID , false);
        api.handleIntent(getIntent(), this);
    }
    public native String getAppIdNative();
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	
	}

	@Override
	public void onResp(BaseResp resp) {
/*
		switch (resp.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			activity.mHander.sendEmptyMessage(MSG_TO_WEIXINSUCCESS);
			break;
		case BaseResp.ErrCode.ERR_USER_CANCEL:
			break;
		case BaseResp.ErrCode.ERR_AUTH_DENIED:
			activity.mHander.sendEmptyMessage(MSG_TO_FAIL);
			break;
		default:
			activity.mHander.sendEmptyMessage(MSG_TO_FAIL);
			break;
		}
*/
//		this.finish();
	}
	
	
}