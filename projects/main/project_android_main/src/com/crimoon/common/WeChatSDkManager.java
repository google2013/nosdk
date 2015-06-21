package com.crimoon.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXImageObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXTextObject;

public class WeChatSDkManager {
	private static final int TIMELINE_SUPPORTED_VERSION = 0x22010003;
	private static WeChatSDkManager mSharedSDk = null;
	private IWXAPI api;
	String APP_ID = "wx5cfdad55c16a8ac0";
	private final int THUMB_SIZE = 50;
	String path;
	int type;
	final int MSG_TO_SEND = 0;
	final int MSG_TO_SEND_TEXT = 1;
	final int MSG_TO_CREATE = 2;
	Handler mHander;
	public static boolean isSend;
	public WeChatSDkManager() {
		
		isSend = false;
		APP_ID  = getAppIdNative();
		Log.e("appid",APP_ID+" appid");
		api = WXAPIFactory.createWXAPI(ProjectTK.getContext(), APP_ID, false);
		api.registerApp(APP_ID);
		mHander = new Handler() {

			public void handleMessage(Message msg) {

				switch (msg.what) {
				
				case MSG_TO_CREATE:{
					
					
					
				}
					break;
				case MSG_TO_SEND_TEXT:{

					if (api.isWXAppInstalled() == false) {

						Toast.makeText(ProjectTK.getContext(), "没有安装微信", 3).show();
						return;
					}
					if (api.isWXAppSupportAPI() == false) {

						Toast.makeText(ProjectTK.getContext(), "微信版本过低", 3).show();
						return;
					}
					try{
						
						WXTextObject textObj = new WXTextObject();
	                    textObj.text = path;
	                    WXMediaMessage msgx = new WXMediaMessage();
                        msgx.mediaObject = textObj;
                        msgx.description = path;    
						SendMessageToWX.Req req = new SendMessageToWX.Req();
						req.transaction = buildTransaction("text");
						req.message = msgx;
						req.scene = type == 0? SendMessageToWX.Req.WXSceneSession :SendMessageToWX.Req.WXSceneTimeline ;
						api.sendReq(req);
						if (api.getWXAppSupportAPI() >= TIMELINE_SUPPORTED_VERSION) {
							ProjectTK.mHander.sendEmptyMessage(ProjectTK.MSG_TO_WEIXINSUCCESS);
						}
						
					}catch(Exception e){
						
						e.printStackTrace();
						return;
					}
				}
					break;
				case MSG_TO_SEND:{

					if (api.isWXAppInstalled() == false) {

						Toast.makeText(ProjectTK.getContext(), "没有安装微信", 3).show();
						return;
					}
					if (api.isWXAppSupportAPI() == false) {

						Toast.makeText(ProjectTK.getContext(), "微信版本过低", 3).show();
						return;
					}

					Bitmap bmp = BitmapFactory.decodeFile(path);
					bmp = compressImage(bmp);

					try{
				
						WXImageObject imgObj = new WXImageObject(bmp);
						WXMediaMessage msgx = new WXMediaMessage();
						msgx.mediaObject = imgObj;
						Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp,
								THUMB_SIZE, THUMB_SIZE, true);
						bmp.recycle();
						msgx.thumbData = Util.bmpToByteArray(thumbBmp, true);
						
						msgx.mediaObject = imgObj;
						msgx.description = "test";
						
						SendMessageToWX.Req req = new SendMessageToWX.Req();
						req.transaction = buildTransaction("img");
						req.message = msgx;
						req.scene = type == 0? SendMessageToWX.Req.WXSceneSession :SendMessageToWX.Req.WXSceneTimeline ;
						api.sendReq(req);
						if (api.getWXAppSupportAPI() >= TIMELINE_SUPPORTED_VERSION) {
							ProjectTK.mHander.sendEmptyMessage(ProjectTK.MSG_TO_WEIXINSUCCESS);
						}
						
					}catch(Exception e){
						
						e.printStackTrace();
						return;
					}
				}
				break;
				}
			}
		};
	}

	public static WeChatSDkManager getInstance() {

		if (mSharedSDk == null) {

			mSharedSDk = new WeChatSDkManager();
		}
		return mSharedSDk;
	}

	public void sendImage(int type, String path) {
		this.type = type;
		this.path = path;
		mHander.sendEmptyMessage(MSG_TO_SEND);

	}
	public void sendText(int type, String path) {
		this.type = type;
		this.path = path;
		mHander.sendEmptyMessage(MSG_TO_SEND_TEXT);

	}
	public native String getAppIdNative();

	public String getAppId() {

		return APP_ID;
	}
	boolean isInstall(){
		
		return  api.isWXAppInstalled();
		
	}
	boolean isSuppotApi(){
		
		return api.isWXAppSupportAPI() ;
		
	}

	private String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis())
				: type + System.currentTimeMillis();
	}

	private Bitmap compressImage(Bitmap image) {
		if( image != null )
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
			int options = 100;
			while (baos.toByteArray().length / 1024 > 500) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
				baos.reset();// 重置baos即清空baos
				image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
				options -= 10;// 每次都减少10
			}
			ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
			Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
			return bitmap;
		}
		return null;

	}
	public IWXAPI getApi(){
		
		return api;
	}
	public void setAppIdAndSecret(String appid,String appkey){
		
		APP_ID = appid;
		mHander.sendEmptyMessage( MSG_TO_CREATE);
		
	}
}

