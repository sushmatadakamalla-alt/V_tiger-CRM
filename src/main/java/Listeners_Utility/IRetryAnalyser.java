package Listeners_Utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class IRetryAnalyser implements IRetryAnalyzer {

	
	int max=5;
	int count=0;
	@Override
	public boolean retry(ITestResult result) {
		if(count<max) {
			count++;
			return true;
		}
		return false;
	}

}
