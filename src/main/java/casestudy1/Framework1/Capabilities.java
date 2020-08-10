package casestudy1.Framework1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class Capabilities {

	protected static String appPackage;
	protected static String appActivity;
	protected static String deviceName;
	protected static String chromeDriver;
	
	public static AndroidDriver<AndroidElement> Capability(String appActivity,String appPackage, String deviceName, String chromeDriver) throws IOException {
		
		FileReader input = new FileReader(System.getProperty("user.dir")+ "\\src\\main\\java\\global.properties");
		//FileReader input = new FileReader("C:\\Users\\SaradaPrusty\\eclipse-workspace\\MobileTestingBySunil\\Framework1\\src\\main\\java\\global.properties");
		Properties property = new Properties();
		property.load(input);
		appPackage = property.getProperty("appPackage");
		appActivity = property.getProperty("appActivity");
		deviceName = property.getProperty("deviceName");
		chromeDriver = property.getProperty("chromeDriver");
		
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
		
		//The below capability for Web inside native app
		//Take the version of chromedriver from the mobile cherome app
		cap.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, chromeDriver);
		
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),cap);
		  return driver;

		
		
		
		
		
	}

}
