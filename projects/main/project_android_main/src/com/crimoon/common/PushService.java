package com.crimoon.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONObject;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.WindowManager;

public class PushService extends Service {

	public static String action = "com.crimoon.projecttk.PushService";

	private NotificationManager mManager;
	public static ArrayList<PushMessage> array = new ArrayList();
	MyReceiver receiver;
    WakeLock mWakeLock;
	final int MSG_TO_SHOW_MSG = 0;
	final int RESULT_SUC = 0;
	final int RESULT_FAL = 1;
	final int RESULT_EMP = 2;
	String deviceid;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void onCreate() {

		super.onCreate();
		mManager = (NotificationManager) getSystemService(ProjectTK.NOTIFICATION_SERVICE);
		receiver = new MyReceiver();
		PackageManager manager = this.getPackageManager();
	    // acquireWakeLock();
		IntentFilter filter = new IntentFilter();
		filter.addAction(action);
		registerReceiver(receiver, filter);

	}
	 private void acquireWakeLock()  
	    {  
	 
			if (null == mWakeLock)  
	        {  
	            PowerManager pm = (PowerManager)this.getSystemService(Context.POWER_SERVICE);  
	            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK|PowerManager.ON_AFTER_RELEASE,"");  
	            if (null != mWakeLock)  
	            {  
	                mWakeLock.acquire();  
	            }  
	        }  
	    }  
	  
	    //释放设备电源锁  
	    private void releaseWakeLock()  
	    {  
	        if (null != mWakeLock)  
	        {  
	            mWakeLock.release();  
	            mWakeLock = null;  
	        }  
	    }  
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		// doTask();
		try{
			
			deviceid = intent.getExtras().getString("deviceid");
			if(	deviceid == null ){
				
				Log.e("","deviceid null");
			}
			doNetPushTask();
			
		}catch(Exception e){
			
					
		}
		return super.onStartCommand(intent, flags, startId);
	}

	private void doNetPushTask() {

		new Thread() {
			@Override
			public void run() {
				
				Log.e("PushService", "doNetPushTask started!!");
				long pushid = 0;
				while (true) {

					Log.e("PushService", "try it again !!!!!!!!!!!!");
					try {

//						String checkUrl = "http://192.168.1.130:28081/getpush?devid="+ deviceid;
						String checkUrl = "http://tkzzpush.crimoon.net:28081/getpush?devid="+ deviceid;

						URL url = new URL(checkUrl);
						HttpURLConnection httpConnection = (HttpURLConnection) url
								.openConnection();
						httpConnection.setRequestProperty("accept", "*/*");
						httpConnection.setRequestProperty("connection",
								"Keep-Alive");
						httpConnection
								.setRequestProperty("user-agent",
										"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
						// 建立实际的连接
						httpConnection.connect();
						InputStreamReader reader = new InputStreamReader(
								httpConnection.getInputStream());
						BufferedReader bReader = new BufferedReader(reader);
						String json = bReader.readLine();
						Log.e("msg","msg error "+json);
						JSONObject jsonObj = new JSONObject(json);
					
						int result = Integer.parseInt(jsonObj
								.getString("result"));
						
						if (result == RESULT_SUC) {
						
							String  msglist = jsonObj.getString("msglist");
							String strArray[] = msglist.split("\\|");
							for (int i = 0; i < strArray.length; i++) {
									
									String str =  strArray[i];
									Log.e("messgage","msg get"+str);
									str = str.substring(1,str.length()-1);
									String strLA[] = str.split(",");
									String realMsg = new String();
									for(int j = 0 ; j < strLA.length; j++){
									
										char  word = (char) Integer.parseInt(strLA[j]);
										realMsg  = realMsg + word;
									}  
									Log.e("messgage","msg getr "+ realMsg);
								
								    Notification notification =  new Notification();
							        notification.icon = PushService.this.getApplicationInfo().icon;
							        notification.tickerText =PushService.this.getApplicationInfo().name;
							        notification.defaults = Notification.DEFAULT_ALL; 
							    	notification.audioStreamType = android.media.AudioManager.ADJUST_LOWER;			  
							        notification.flags = Notification.FLAG_AUTO_CANCEL ;
									notification.when = System.currentTimeMillis();
							        
							        Intent intent = new Intent(PushService.this,
											ProjectTK.class);
							    	PendingIntent pendingIntent = PendingIntent
											.getActivity(PushService.this, 0,
													intent,
													PendingIntent.FLAG_ONE_SHOT);
							        notification.setLatestEventInfo(PushService.this,PushService.this.getApplicationInfo().name, realMsg ,pendingIntent) ;			        
							    	mManager.notify((int) pushid, notification) ;		
							    	pushid ++;

							}

						} else if (result == RESULT_FAL) {

							Log.e("request","request fail");
							
						} else if (result == RESULT_EMP) {

							Log.e("request","request empty");
						}
						Thread.sleep(300000);

					} catch (Exception e) {
						
						Log.e("request","request error");
						e.printStackTrace();
						try {
							
							Thread.sleep(60000);
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
						
					}

				}
			}
		}.start();

	}

	private void doTask() {

		new Thread() {
			@Override
			public void run() {
				try {

					while (true) {

						for (int i = 0; i < array.size(); i++) {

							PushMessage message = array.get(i);
							message.setPasstime(message.getPasstime() + 1);
							if (message.getPasstime() > message.getTime()) {
								Log.e("pushservice", "showMessage");
								Notification notification = new Notification();
								// 添加声音提示
								notification.defaults = Notification.DEFAULT_SOUND;
								// audioStreamType的值必须AudioManager中的值，代表着响铃的模式
								notification.audioStreamType = android.media.AudioManager.ADJUST_LOWER;
								notification.when = System.currentTimeMillis();
								notification.flags = Notification.FLAG_AUTO_CANCEL;
								Intent intent = new Intent(PushService.this,
										ProjectTK.class);
								PendingIntent pendingIntent = PendingIntent
										.getActivity(PushService.this, 0,
												intent,
												PendingIntent.FLAG_ONE_SHOT);
								notification.setLatestEventInfo(
										PushService.this, "战报",
										message.getMessage(), pendingIntent);
								mManager.notify(message.getMessageName(), i,
										notification);
								array.remove(i);
								i++;
							}
						}
						Thread.sleep(1000);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		releaseWakeLock();
	}

}

class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		try {

			if (arg1.getAction().equals(PushService.action)) {

				switch (arg1.getExtras().getInt("MegId")) {

				case 1: {

					PushMessage msg = (PushMessage) arg1.getExtras()
							.getSerializable("Meg");
					PushService.array.add(msg);
					Log.e("pushservice", "addMessage");
				}
					break;
				case 2: {

					String messageName = arg1.getExtras().getString("Meg");
					for (int i = 0; i < PushService.array.size(); i++) {
						PushMessage message = PushService.array.get(i);
						if (message.getMessageName().equals(messageName)) {

							PushService.array.remove(i);
							break;
						}
					}
				}
					break;
				case 3: {

					PushService.array.removeAll(PushService.array);
					Log.e("pushservice", "removeMessage");
				}
					break;

				}

			}
		} catch (Exception e) {
			Log.e("error", "broad error " + e.getMessage());
		}

	}

};