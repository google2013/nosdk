package com.nosdk.common;

public interface ILoginCallback {
	public void onLoginFished(String json);
	public void onLoginFailed(String Result );
	public void onLoginCancel(String Result );
}
