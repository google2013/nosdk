package com.sdk.common;

public enum PayResultType {
	Success(0),
	Failed(1),
	Exit(2);
	
	private int resultType;
	private PayResultType(int result)
	{
		this.resultType = result;
	}
}

