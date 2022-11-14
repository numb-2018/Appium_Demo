package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.logging.Log;
import org.openqa.selenium.remote.DesiredCapabilities;
import utilities.Constants;
import utilities.LogUtil;

import java.net.URL;
import java.util.concurrent.TimeUnit;
//import io.appium.java_client.ios.IOSDriver;

public class CreateDriver {

	private static Log logger = LogUtil.getLog(CreateDriver.class);
//	private ThreadLocal<AppiumDriver> mobileDriver = new ThreadLocal<>();
	private static AppiumDriver<?> driver = null;
	private static URL serverUrl = null;
	
	/**
	 * Method to create local driver
	 */

	public static void createLocalDriver(DesiredCapabilities capabilities) {
		logger.info("*****Start createLocalDriver method*******");
		try {
			serverUrl = new URL(
					"http://" + "127.0.0.1" + ":" + 4723 + "/wd/hub");
			logger.info("createLocalDriver ::: serverUrl:::" + serverUrl);
			logger.info("Driver Capabilities createLocalDriver:::" + capabilities);
		} catch (Exception e) {
			logger.error("createLocalDriver serverUrl Exception::" + e.getMessage());
			e.printStackTrace();
		}
		try {
			driver = new AndroidDriver(serverUrl, capabilities);
			driver.manage().timeouts().implicitlyWait(Constants.ANDROID_IMPLICIT_WAIT_TIME_SECONDS, TimeUnit.SECONDS);
			logger.info("AppiumDriver successfully created:::createLocalDriver Asim"+driver.toString());
		} catch (Throwable e) {
			logger.error("Throwable while createLocalDriver:::" + e.getMessage());
			e.printStackTrace();
		}
	}






/*
	public static void createLocalDriver(DesiredCapabilities capabilities, String testBedName, String portNo) {
//		AppiumDriver driver = null;
		logger.info("*****Start createLocalDriver method*******");
		try {
		//	if (MachineInfo.isMacOSMachine()) {
				
				serverUrl = new URL(
						"http://" + "127.0.0.1" + ":" + portNo + "/wd/hub");
				logger.info("createLocalDriver ::: serverUrl:::" + serverUrl);
				logger.info("Driver Capabilities createLocalDriver:::" + capabilities);
	//		}
		} catch (Exception e) {
			logger.error("createLocalDriver serverUrl Exception::" + e.getMessage());
			e.printStackTrace();
		}
		try {
//			if (DeviceInfo.isDeviceTypeAndroid())
//				driver = new AndroidDriver(AppiumLauncher.appiumDriverLocalService.get().getUrl(), capabilities);
			driver = new AndroidDriver(serverUrl, capabilities);
//			else
//				driver = new IOSDriver(serverUrl, capabilities);
				
				*//*
				 * driver.manage().timeouts().implicitlyWait(
				 * Long.parseLong(testBedConfig.config.getValue("IMPLICIT_WAIT_TIME_SECONDS")),
				 * TimeUnit.SECONDS);
				 *//*
				
				driver.manage().timeouts().implicitlyWait(Constants.ANDROID_IMPLICIT_WAIT_TIME_SECONDS, TimeUnit.SECONDS);
				//driver is set as global
				//			setDriver(driver);
				
			logger.info("AppiumDriver successfully created:::createLocalDriver");
		} catch (Throwable e) {
			logger.error("Throwable while createLocalDriver:::" + e.getMessage());
			e.printStackTrace();
		}
	}*/
	
	/**
	 * Method to get driver
	 */
		public static AndroidDriver<?> getDriver(DesiredCapabilities capabilities) {
		if (driver == null){
			createLocalDriver(capabilities);
		}
		return (AndroidDriver<?>) driver;

		}
	
	/**
	 * Method to set driver
	 */
	/*
	 * public void setDriver(AppiumDriver localDriver) {
	 * mobileDriver.set(localDriver); }
	 */

	/**
	 * Method to get driver
	 */
	public static AppiumDriver<?> getDriver() {
//		return mobileDriver.get();
		return driver;
	}
	
}
