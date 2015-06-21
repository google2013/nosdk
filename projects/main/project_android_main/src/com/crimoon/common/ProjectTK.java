/****************************************************************************
Copyright (c) 2010-2012 cocos2d-x.org

http://www.cocos2d-x.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 ****************************************************************************/
package com.crimoon.common;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
import org.cocos2dx.lib.Cocos2dxHelper;


import com.crimoon.common.VideoView.OnFinishListener;
import com.tencent.weibo.oauthv2.OAuthV2;
import com.tencent.weibo.webview.OAuthV2AuthorizeWebView;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;

import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.WindowManager;


public class ProjectTK extends Cocos2dxActivity  implements OnFinishListener {

	private int newVerCode = -1;// 新版本号
	private int updatetype = 0;// 0。不更新,1 强制 2更新缓冲
	private ProgressDialog pd = null;
	private String downUrl = "";
	String UPDATE_SERVERAPK = "";
	public String projectID = "123456";
	public CommonSdkManager sdkManager = null;
	public static  Handler mHander;
	private Thread updateThead;
	private final int MSG_TO_DOWN = 0;
	private final int MSG_TO_DOWNOVER = 1;
	private final int MSG_TO_SETFILESIZE = 2;
	private final int MSG_TO_DOWNSIZE = 3;
	private final int MSG_TO_OPENURL = 5;
	private final int MSG_TO_PLAYVIDEO = 7;
	public static final int MSG_TO_WEIXINSUCCESS = 4;
	public static final int MSG_TO_FAIL = 6;
	private long fileSize = 0;
	private long downSize = 0;
	private ViewGroup group;
	private VideoView videoView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		group = (ViewGroup)getWindow().getDecorView();//视频
		sdkManager = new CommonSdkManager();
		
		WeiboManager.instance();
		PushMessageManager.instance();
		CommonSdkManager.instance().initSDK(this);
		WeChatSDkManager.getInstance();
		videoView = null;
		mHander = new Handler() {

			public void handleMessage(Message msg) {

				switch (msg.what) {

				case MSG_TO_DOWN: {
				
					((ProjectTK) ProjectTK.getContext()).onPause();
					((ProjectTK) ProjectTK.getContext()).getWindow().setFlags(
							WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
							WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

					pd = new ProgressDialog(ProjectTK.this);
					pd.setTitle("正在下载");
					pd.setMessage("请稍后。。。");
					pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					pd.setProgress(0);
					downFile(downUrl);
				}
					break;
				case MSG_TO_DOWNOVER: {

					pd.cancel();
					update();
					pd = null;
				}
					break;
				case MSG_TO_SETFILESIZE: {

					pd.setMax((int) fileSize);

				}
					break;
				case MSG_TO_DOWNSIZE: {

					pd.setProgress((int) downSize);

				}
				break;
				case  MSG_TO_OPENURL: {

				    Intent intent = new Intent();        
			        intent.setAction("android.intent.action.VIEW");    
			        Uri content_url = Uri.parse(downUrl);   
			        intent.setData(content_url);  
			        startActivity(intent);

			}break;
				case MSG_TO_WEIXINSUCCESS:{
					
					weiXinSuccess();
				}
					break;
				case MSG_TO_FAIL:{
					
					weiXinFail();
				}break;
				
				case MSG_TO_PLAYVIDEO:{
					
					try
					{
						videoView = new VideoView((ProjectTK)ProjectTK.getContext());
						videoView.setOnFinishListener((ProjectTK)ProjectTK.getContext());
						AssetFileDescriptor afd = getAssets().openFd("video/startvideo.mp4");
					    videoView.setVideo(afd);
						group.addView(videoView);
						videoView.setZOrderMediaOverlay(true);
					}
					catch(Exception e)
					{
						 e.printStackTrace();
						palyover();
					}
				}
				break;

				}
			}
		};
		
		
		Intent intent = new Intent(this, PushService.class);
		intent.putExtra("deviceid", JavaToCppHelper.getDevID());
		startService(intent);
	
	}
	static {

		System.loadLibrary("cocos2dcpp");
	}

	 public Cocos2dxGLSurfaceView onCreateView() {
	    	Cocos2dxGLSurfaceView glSurfaceView = new Cocos2dxGLSurfaceView(this){
	    		
	    		@Override
	    		public boolean onKeyDown(final int pKeyCode, final KeyEvent pKeyEvent) {
	    			switch (pKeyCode) {
	    				case KeyEvent.KEYCODE_BACK:
	    					((ProjectTK)ProjectTK.getContext()).sdkManager.onExit();
	    					return true;
	    				default:
	    					return super.onKeyDown(pKeyCode, pKeyEvent);
	    			}
	    		}

	    	};
	    	// ProjectTK should create stencil buffer
	    	glSurfaceView.setEGLConfigChooser(5, 6, 5, 0, 16, 8);
	    	
	    	return glSurfaceView;
	    }
	
	@Override
	protected void onResume() {

		// WeiboManager.instance().enterFront();
		super.onResume();
		if (pd != null) {

			onPause();
		}
		if(sdkManager != null){
		
			sdkManager.onResume();
		}
		if(videoView!=null)
			videoView.resume();
	}

	@Override
	protected void onPause() {

		// WeiboManager.instance().enterBackGround();
		super.onPause();
		sdkManager.onPause();
		if(videoView!=null)videoView.pause();
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		sdkManager.destroySDK(this);
		
	}
	@Override
	protected void onStop(){

		super.onStop();
		sdkManager.onStop();
		if(videoView!=null)videoView.stop();
		
	}

	public native void weiXinSuccess();
	public native void weiXinFail();
	@Override
	protected void onStart(){

		super.onStart();
		sdkManager.onStart();
		
	}
	public void toUpdate(String url){
		
		downUrl = url;
		mHander.sendEmptyMessage(MSG_TO_DOWN);
	}
	public void toOpenUrl(String url){
		
		downUrl = url;
		mHander.sendEmptyMessage( MSG_TO_OPENURL);
	}
		
	/**
	 * 下载apk
	 */
	public void downFile(final String url) {

		pd.show();
		pd.setCanceledOnTouchOutside(false);
		pd.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
		pd.setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface arg0, int keyCode,
					KeyEvent arg2) {

				switch (keyCode) {
				case KeyEvent.KEYCODE_BACK:

					return true;
				}
				return false;

			}
		});
		new Thread() {

			public void run() {

				UPDATE_SERVERAPK= ProjectTK.getContext().getPackageName()
						+ newVerCode + ".apk";
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(url);
				HttpResponse response;
				try {

					response = client.execute(get);
					HttpEntity entity = response.getEntity();
					fileSize = entity.getContentLength();
					InputStream is = entity.getContent();
					mHander.sendEmptyMessage(MSG_TO_SETFILESIZE);
					FileOutputStream fileOutputStream = null;
					if (is != null) {

						File newFile = new File(
								Cocos2dxHelper.getCocos2dxWritablePath());
						try {

							if (newFile.exists() == false) {

								newFile.mkdirs();
							}

						} catch (Exception e) {

							Log.e("createFile", e.getMessage());

						}
						String filePath = Cocos2dxHelper
								.getCocos2dxWritablePath()
								+ "/"
								+ UPDATE_SERVERAPK;
						Log.e("createFile", filePath);
						File file = new File(filePath);
						file.setExecutable(true,false);
						file.setReadable(true, false);
						file.setWritable(true, false);
						fileOutputStream = new FileOutputStream(file);
						byte[] b = new byte[64];
						int charb = -1;
						downSize = 0;
						while ((charb = is.read(b)) != -1) {

							fileOutputStream.write(b, 0, charb);
							downSize += charb;
							mHander.sendEmptyMessage(MSG_TO_DOWNSIZE);
						}
					}
					fileOutputStream.flush();
					if (fileOutputStream != null) {

						fileOutputStream.close();
					}
					mHander.sendEmptyMessage(MSG_TO_DOWNOVER);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					((ProjectTK) ProjectTK.getContext()).onResume();
				}
			}
		}.start();
	}

	/**
	 * 安装应用
	 */
	public void update() {

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(
				Uri.fromFile(new File(Cocos2dxHelper.getCocos2dxWritablePath()
						+ "/" + UPDATE_SERVERAPK)),
				"application/vnd.android.package-archive");
		startActivity(intent);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		
	}

	@Override
	public void onVideoFinish() {
		// TODO Auto-generated method stub
		group.removeView(videoView);
		videoView = null;
		palyover();
	}
	public static native void palyover();
	public void goPlay(){
		
		mHander.sendEmptyMessage(MSG_TO_PLAYVIDEO);
	}

}
