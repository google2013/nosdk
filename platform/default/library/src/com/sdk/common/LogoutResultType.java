package com.sdk.common;

public enum LogoutResultType {
	Success(0),
	Failed(1),
	Exit(2);
	
	private int resultType;
	private LogoutResultType(int result)
	{
		this.resultType = result;
	}
}
