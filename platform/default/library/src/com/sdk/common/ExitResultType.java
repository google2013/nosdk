package com.sdk.common;

public enum ExitResultType {
	Success(0),
	Failed(1),
	Exit(2),
	GameExit(3);
	
	private int resultType;
	private ExitResultType(int result)
	{
		this.resultType = result;
	}
}
