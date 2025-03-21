package MyRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "./src/main/java/Feature/Login.feature",  // Path to feature files
	    glue = { "StepDef", "Hook" },  
	    tags = "@LoginFeature",  
	    plugin = {
	        "pretty", 
	        "html:target/cucumber-reports.html"
	    },
	    monochrome = true 
	)

public class TestRunner  extends AbstractTestNGCucumberTests {

}
