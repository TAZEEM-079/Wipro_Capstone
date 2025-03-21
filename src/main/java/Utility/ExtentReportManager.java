package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	 private static ExtentReports extent;
	 private static ExtentTest test;
	// Initialize Extent Report
	    public static void initReport() {
	        if (extent == null) {
	            ExtentSparkReporter spark = new ExtentSparkReporter("./target/ExtentReports-DemoBlaze.html");
	            extent = new ExtentReports();
	            extent.attachReporter(spark);
	        }
	    }

	    // Create a new test
	    public static ExtentTest createTest(String testName) {
	        test = extent.createTest(testName);
	        return test;
	    }

	    // Get the existing Extent Reports instance
	    public static ExtentReports getExtent() {
	        return extent;
	    }

	    // Flush the report
	    public static void flushReport() {
	        if (extent != null) {
	            extent.flush();
	        }
	    }

}
