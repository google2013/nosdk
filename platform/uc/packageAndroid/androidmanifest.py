<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
      package="com.sdk.sample"
      android:versionCode="4304"
      android:versionName="4.3.4">

    <uses-sdk android:minSdkVersion="8"/>
    <uses-feature android:glEsVersion="0x00020000" />

    <application 
        android:name="com.sdk.sample.GameApplication"
        android:allowBackup="true"
        android:label="@string/app_name"
        android:icon="@drawable/icon">

        <activity android:name="com.sdk.sample.MainActivity"
                  android:label="@string/app_name"
                  android:screenOrientation="landscape"
                  android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
                  android:configChanges="orientation">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
	<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
	<uses-permission android:name="android.permission.INTERNET"></uses-permission>
	<uses-permission android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>
	 <!-- uses-permission android:name="android.permission.WRITE_APN_SETTINGS" /-->	 
	 <!-- 加入在SDCard中创建与删除文件的权限 -->
	<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
	<!-- 加入在SDCard中写入数据的权限 -->
	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />	
	<uses-permission android:name="android.permission.WAKE_LOCK" />
	<supports-screens
        android:anyDensity="true"
        android:resizeable="true"
        android:largeScreens="true"
        android:normalScreens="true"
   	    android:xlargeScreens="true"
        android:smallScreens="true" />  
</manifest> 