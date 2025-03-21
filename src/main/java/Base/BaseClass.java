package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.google.common.io.Files;

public class BaseClass {
	protected static WebDriver driver;
	public static Properties prop;
	
	//Load properties file
	public BaseClass() {
		try {
			prop=new Properties();
			FileInputStream fis = new FileInputStream("./src/main/java/Data/config.properties");
			prop.load(fis);
		}catch(IOException e) {
			System.out.println("Error loading properties file: " + e.getMessage());
		}
	}
	
	//Browser Initialization
	public static void invokeBrowser() {
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		
		if(browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		else if(browser.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		else {
			throw new RuntimeException("Invalid browser name in properties");
		}
		driver.get(url);
	}
	public static void screenshot() throws IOException
	{
		//Take the screenshot as proof
		File src=null;
		src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(src, new File("./screenshot/"+"page-"+System.currentTimeMillis()+".png"));
	}
	
	
	//Close browser
	public static void closeBrowser() {
			driver.quit();
	}
	

}
