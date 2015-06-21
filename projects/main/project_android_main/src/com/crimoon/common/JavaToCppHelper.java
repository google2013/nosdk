package com.crimoon.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;


import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetFileDescriptor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;

public class JavaToCppHelper {

	public static String getMacAddress() {

		WifiManager wifi = (WifiManager) ProjectTK.getContext()
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		String string  = info.getMacAddress();
		
		if(string == null ){
			
			string = getDevID();
			
		}else if(string.length() <= 0 ){
			
			string = ((ProjectTK )ProjectTK.getContext()).sdkManager.getRoleName();
		}
		
		if(string == null){
			
			string = "00:00:00:00:00:00";
		}
		return string;
	}

	public static boolean isconnect3G() {

		ConnectivityManager conMan = (ConnectivityManager) ProjectTK
				.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		// mobile 3G Data Network
		State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();
		return mobile == State.CONNECTED;
	}

	public static boolean isconnectWIFI() {

		ConnectivityManager conMan = (ConnectivityManager) ProjectTK
				.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		// mobile 3G Data Network
		State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		return wifi == State.CONNECTED;
	}

	public static boolean createfile(String path) {

		File newFile = new File(path);
		try {

			if(newFile.exists() == false){
				
				newFile.mkdirs();
			}
			return true;
		} catch (Exception e) {

			Log.e("createFile", e.getMessage());

		}
		return false;

	}
	public static void playVideo (){
		
		((ProjectTK) ProjectTK.getContext()).goPlay();
		
	}
	public static int getRam() {

		long mTotal;
		// /proc/meminfo读出的内核信息进行解释
		String path = "/proc/meminfo";
		String content = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path), 8);
			String line;
			if ((line = br.readLine()) != null) {
				content = line;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// beginIndex
		int begin = content.indexOf(':');
		// endIndex
		int end = content.indexOf('k');
		// 截取字符串信息
		content = content.substring(begin + 1, end).trim();
		mTotal = Integer.parseInt(content);
		int mem = (int) (mTotal / 1024.0f);
		Log.e("menmery", content + " " + mem + " M");
		return mem;
	}
	public static String getVersion(){
		
		PackageManager manager = ProjectTK.getContext().getPackageManager();
		try {
			
			PackageInfo info = manager.getPackageInfo(ProjectTK.getContext().getPackageName(), 0);
			Log.e("msg", ""+"version "+info.versionName);
			return info.versionName;
			
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "error";
	}
	public static String getProductName(){
		
		return ProjectTK.getContext().getPackageName();
	}
	public static String getProjectID(){
		
//		return ((ProjectTK)ProjectTK.getContext()).projectID;
		return CommonSdkManager.instance().getProjectID();
	}
	public static void update(String url){
		Log.e("down","down"+url);
		((ProjectTK)ProjectTK.getContext()).toUpdate(url);
	}
	public static void goUpUrl(String url){
		Log.e("down","down"+url);
		((ProjectTK)ProjectTK.getContext()).toOpenUrl(url);
	}
	
	public static String getDevID(){
	
		DeviceUuidFactory  data = new DeviceUuidFactory(ProjectTK.getContext());
		return data.getDeviceUuid().toString();
	}
	

}
class DeviceUuidFactory {
	
    protected static final String PREFS_FILE = "device_id.xml";
    protected static final String PREFS_DEVICE_ID = "device_id";
    protected  UUID uuid;
    public DeviceUuidFactory(Context context) {
    	
    	
        if( uuid ==null ) {
        	
        	
        }
        synchronized (DeviceUuidFactory.class) {
            
        	if( uuid == null) {
        		
        		
                final SharedPreferences prefs = context.getSharedPreferences( PREFS_FILE, 0);
                final String id = prefs.getString(PREFS_DEVICE_ID, null );
                if (id != null) {
                    // Use the ids previously computed and stored in the prefs file
                    uuid = UUID.fromString(id);
                    
                } else {
                	
                	
                    final String androidId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
                    // Use the Android ID unless it's broken, in which case fallback on deviceId,
                    // unless it's not available, then fallback on a random number which we store
                    // to a prefs file
                    try {
                       
                    	if (androidId != null && !"9774d56d682e549c".equals(androidId) ) {
                          
                    		uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                    		
                        } else {
                        	
                            final String deviceId = ((TelephonyManager) context.getSystemService( Context.TELEPHONY_SERVICE )).getDeviceId();
                            if(deviceId!=null){
                            	
                            	 uuid =   UUID.nameUUIDFromBytes(deviceId.getBytes("utf8"));
                            	 
                            }else  {
                            	
                            	String getDevid = getDeviceId(context);
                            	uuid = UUID.nameUUIDFromBytes(getDevid.getBytes("utf8"));
                            }
                        }
                    } catch (UnsupportedEncodingException e) {
                    	
                        throw new RuntimeException(e);
                    }
                    // Write the value out to the prefs file
                    prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString() ).commit();
                }
            }
        }
    }
    
    public  String getDeviceId(Context context) {


    	StringBuilder deviceId = new StringBuilder();
    	// 渠道標志
    	deviceId.append("a");
    	try {

    	//wifi mac地址
    		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    		WifiInfo info = wifi.getConnectionInfo();
    		String wifiMac = info.getMacAddress();
    		if(wifiMac != null){
    		
    			deviceId.append("wifi");
    			deviceId.append(wifiMac);    
    			return deviceId.toString();
    		}

    		//IMEI（imei）
    		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    		String imei = tm.getDeviceId();
    		if(imei!=null){
    		
    			deviceId.append("imei");
    			deviceId.append(imei);
    			return deviceId.toString();
    		}

    		//序列號（sn）
    		String sn = tm.getSimSerialNumber();
    		if(sn!=null){
    			
    			deviceId.append("sn");
    			deviceId.append(sn);
    			return deviceId.toString();
    		}

    		//如果上面都沒有， 則生成一個id：隨機碼
    		String uuidx = getUUIDX(context);
    		if(uuidx != null){
    		
    			deviceId.append("id");
    			deviceId.append(uuidx);
    			return deviceId.toString();
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		deviceId.append("id").append(getUUIDX(context));
    		return deviceId.toString();
    	}
    	return deviceId.toString();
     }
    	       /**
    	* 得到全局唯一UUID
    	*/
    	public  String getUUIDX(Context context){
    		
    		SharedPreferences mShare =  context.getSharedPreferences("sysCacheMap", 0);
        	String uuidx = null;
        	if(mShare != null){
        		
        		uuidx= mShare.getString("uuid", "");
        	}
        	if(uuidx == null){
        
        		uuidx = UUID.randomUUID().toString();
        		mShare.edit().putString("uuid",uuidx ).commit();
        	}
        	return uuidx;
        
        }

    public UUID getDeviceUuid() {
       
    	Log.e("uuid","uuid "+uuid.toString()); 
    	return uuid;
    }
}
       