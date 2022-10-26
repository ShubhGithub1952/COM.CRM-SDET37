package com.crm_SDET37.GenericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyImplementation implements IRetryAnalyzer {

	int count=1;
	int retryLimit=3;
	public boolean retry(ITestResult result) {
		if (count<retryLimit) {
			count++;
			return true;
		}
		return false;
	}

}
