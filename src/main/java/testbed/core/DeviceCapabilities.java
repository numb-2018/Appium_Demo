package testbed.core;

import org.apache.commons.logging.Log;
import org.openqa.selenium.remote.DesiredCapabilities;
import utilities.LogUtil;

public class DeviceCapabilities {

	private static final Log logger = LogUtil.getLog(DeviceCapabilities.class);
	private DesiredCapabilities capabilities = new DesiredCapabilities();


	public DesiredCapabilities setMobileDeviceCapabilities()
			throws Exception {
		logger.info("Setting Desired capabilities:::");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "OPPO A54");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.application.appiumtutorial");
		capabilities.setCapability("appActivity", "com.application.appiumtutorial.MainActivity");
		capabilities.setCapability("udid", "PZPVSC95GMKNGUBQ");
		capabilities.setCapability("noReset", false);
		capabilities.setCapability("automationName", "uiautomator2");
		logger.info("Capabilities for " + capabilities.toString());
		return capabilities;
	}
}




















	/*
	 * setMobileDeviceCapabilities :- Sets device capabilities for Android mobile
	 * device
	 */
//	public DesiredCapabilities setMobileDeviceCapabilities(TestBedConfig testBedConfig, String testBedName)
/*	public DesiredCapabilities setMobileDeviceCapabilities(String testBedName)
			throws Exception {
		logger.info("Setting Desired capabilities:::");
//		if (DeviceInfo.isDeviceTypeAndroid()) {
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, testBedName);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//			if (DeviceInfo.isDeviceTypeEmulator()) {
//				capabilities.setCapability("avd", testBedName);
//				capabilities.setCapability("avdLaunchTimeout", 60 * 1000);
//				capabilities.setCapability("avdReadyTimeout", 120 * 1000);
//			}
//			capabilities.setCapability("appPackage", testBedConfig.appPackage);
			capabilities.setCapability("appPackage", Constants.ANDROID_PREMISE_PACKAGENAME);
//			capabilities.setCapability("appActivity", testBedConfig.appActivity);
			capabilities.setCapability("appActivity", Constants.ANDROID_PREMISE_ACTIVITYNAME);
//			capabilities.setCapability(MobileCapabilityType.UDID, testBedConfig.getTestBedMap().get(testBedName));
			capabilities.setCapability(MobileCapabilityType.UDID, TestBaseClass.getcurrentDeviceUdid());
			//OS version is 
//			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, testBedConfig.osVersion);
			capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
			capabilities.setCapability("unlockType", "pin");
			capabilities.setCapability("unlockKey", "9999");
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60 * 5);
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
			capabilities.setCapability("uiautomator2ServerInstallTimeout", 60 * 1000);
			capabilities.setCapability("uiautomator2ServerLaunchTimeout", 60 * 1000);
*//*		} else if (DeviceInfo.isDeviceTypeiOS()) {
			capabilities.setCapability("automationName", "XCUITest");
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("udid", testBedConfig.getTestBedMap().get(testBedName));
			capabilities.setCapability("deviceName", testBedName);
			capabilities.setCapability("bundleId", testBedConfig.getBundleId());
			capabilities.setCapability("wdaLocalPort", Integer.parseInt(testBedConfig.getIPhoneWDAPort()));
			capabilities.setCapability("platformVersion", testBedConfig.getIPhoneOS());
			capabilities.setCapability("newCommandTimeout", 300);
		} else
			throw new Exception("Couldn't find selected device type: " + testBedConfig.getDeviceType());*//*
		logger.info("Capabilities for " + testBedName + capabilities.toString());
		return capabilities;
	}

}*/
