package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import frameWorkClasses.Utilities;

public class Listeners implements ITestListener {
	Utilities uts = new Utilities();



	public void onStart(ITestContext context) {
		
		System.out.println("This is from the listner on start test " + context);
		try {
			uts.takeSnapShot("On test start" + uts.timereturn() + ".png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	//	ITestListener.super.onStart(context);
	}

	
	public void onTestFailure(ITestResult result) {
		
		System.out.println("This is from the listner on fail test " + result.getName());
		try {
			uts.takeSnapShot("On test failure" + uts.timereturn() + ".png");
		} catch (Exception e) {
			e.printStackTrace(); 
		}	
	}
		// TODO Auto-generated method stub
	//	ITestListener.super.onTestFailure(result);
	

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	//	ITestListener.super.onTestSkipped(result);
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	//	ITestListener.super.onTestStart(result);
	}


	public void onTestSuccess(ITestResult result) {
		
	System.out.println("TakeALot tests success " + result.getName());
	try {
		uts.takeSnapShot("On test success" + uts.timereturn() + ".png");
	} catch (Exception e) {
		e.printStackTrace();
		
		// TODO Auto-generated method stub
	//	ITestListener.super.onTestSuccess(result);
	}

	
}
}



