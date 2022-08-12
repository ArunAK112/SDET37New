package com.lexnod.genericLib;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerImp implements IRetryAnalyzer{
	
	int count=0;
	int retryLimit=3;
	
	public boolean retry(ITestResult result) {
		if(count<retryLimit)
		{
			count++;
			return true;
		}
		return false;
	}
	
	
	
	

}
