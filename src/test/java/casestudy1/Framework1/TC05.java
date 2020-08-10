package casestudy1.Framework1;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import casestudy1.Framework1.Capabilities;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class TC05 extends Capabilities{

	
	@Test
	public void hybridTest() throws IOException, InterruptedException
	{
		 
	AndroidDriver<AndroidElement> driver = Capability(appActivity,appPackage, deviceName, chromeDriver); 
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	driver.findElement(MobileBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Prabha");
	//This is to hide the keyBoard in the emulaotr the device
	//driver.hideKeyboard();
	//driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
	driver.findElement(MobileBy.xpath("//*[@text='Female']")).click();
	
	//Scroll
	driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
    driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Aruba\"))").click();
	
    //Not working//driver.findElementByAndroidUIAutomator("text(\"Let's Shop\")").click();
    driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	
    int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
    System.out.println(count);
    for(int i=0;i<count;i++) {
    	String ProductName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
    	System.out.println(ProductName);
    	driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
   
    }
    
    driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
    Thread.sleep(3000);
    String amount1 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();
    		//SubSttring is just to eliminate the first string
    amount1 = amount1.substring(1);
    System.out.println(amount1);
    String amount2 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();
    amount2 = amount2.substring(1);
    System.out.println(amount2);
    
    double total = Double.parseDouble(amount1) + Double.parseDouble(amount2);
    System.out.println(total);
    
    String totalamount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
    totalamount = totalamount.substring(1);
    System.out.println(totalamount);
    double Finaltotalamount = Double.parseDouble(totalamount);
   		
    Assert.assertEquals(Finaltotalamount, total);
    
    /*for(int j=0;j<count;j++) {
    	String ProductPrice = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(j).getText();
    	System.out.println(ProductPrice);
    	
   
    }*/
    
    //driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
    //driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click(); // Index will be same bcoz once u added th efirst product the add to cart text will bve changed to added to cart, so the index will gain be 0

    WebElement checkbox= driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Send me e-mails on discounts related to selected products in future\")");
    TouchAction t = new TouchAction(driver);
	t.tap(tapOptions().withElement(element(checkbox))).perform();
	
	WebElement terms= driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Please read our terms of conditions\")");
	t.longPress(longPressOptions().withElement(element(terms)).withDuration(ofSeconds(5))).release().perform();
	
	driver.findElementByAndroidUIAutomator("new UiSelector().text(\"CLOSE\")").click();
	driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
	
	Thread.sleep(5000);
	
	//This is to switch b/w Native and Webapp
	//You will get at appium.io//http://appium.io/docs/en/writing-running-appium/web/hybrid/
	Set<String> contextNames = driver.getContextHandles(); //Identify how many context are there e.g Native/Web
	for (String contextName : contextNames) {
	    System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
	}
	
	driver.context("WEBVIEW_com.androidsample.generalstore");
	driver.findElement(By.xpath("//*[@name='q']")).sendKeys("Selenium");
	driver.findElement(By.xpath("//*[@name='q']")).sendKeys(Keys.ENTER);
	
	Thread.sleep(3000);
	driver.pressKey(new KeyEvent(AndroidKey.BACK));
	driver.context("NATIVE_APP");
	
	
	}
}



/*
Test Case:
6) Switching to WebApp and navigating back to native app
*/