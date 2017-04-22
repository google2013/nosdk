package com.sdk.sample;

import com.sdk.common.SDKManager;

import android.os.Bundle;

public class Activity extends android.app.Activity {

    protected Activity() {
        System.loadLibrary("sample");
        SDKManager.getInstance().onInit("");
        construct();
    }
    private native void construct();

    // Overriden methods.
    public native void onCreate(Bundle savedInstanceState);

    // Required by JNIpp.
    protected native void finalize();
    private int nativeInstance;
}
