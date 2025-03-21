package Hook;
import Base.BaseClass;
import Utility.ExtentReportManager;

import java.io.IOException;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseClass {
    ExtentTest test;

    @Before
    public void setup(Scenario scenario) {
        System.out.println("Starting Scenario: " + scenario.getName());
        invokeBrowser(); 
        ExtentReportManager.initReport(); 
        test = ExtentReportManager.createTest(scenario.getName()); 
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            System.out.println("Scenario Failed: " + scenario.getName());
            screenshot();
            test.fail("Scenario Failed: " + scenario.getName()); 
        } else {
            System.out.println("Scenario Passed: " + scenario.getName());
            test.pass("Scenario Passed: " + scenario.getName()); 
        }
        closeBrowser();
        ExtentReportManager.flushReport(); //Save and close report
    }
}
