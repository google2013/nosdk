package com.sdk.common;

public enum LoginResultType {
	Success(0),
	Failed(1),
	Exit(2);
	
	private int resultType;
	private LoginResultType(int value)
	{
		this.resultType = value;
	}
}
