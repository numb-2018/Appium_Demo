package utilities;

public class Constants {
	
	/****************
	 * Android (Package name for non-Premise or Premise app screen) or other test constants
	 **************/
	public  static final String
			ANDROID_PREMISE_PACKAGENAME = "com.premise.android.qa.debug",
			ANDROID_PREMISE_ACTIVITYNAME = "com.premise.android.activity.launch.LaunchActivity",
			ANDROID_PREMISE_PREFIX_PACKAGENAME = "com.premise.android",
			ANDROID_SETTINGS = "com.android.settings",
			ANDROID_OS10_11_PERMISSIONCONTROLLER_DIALOG= "com.google.android.permissioncontroller",
	        ANDROID_APPNOTIFICATION_SCREEN = "com.android.settings/com.android.settings.Settings$AppNotificationSettingsActivity",
			ANDROID_APPNOTIFICATION_SCREEN2 = "com.android.settings/com.android.settings.SubSettings",

			ANDROID_NETWORKINTERNET_SCREEN = "com.android.settings/com.android.settings.Settings$NetworkDashboardActivity",
			ANDROID_NETWORKINTERNET_SCREEN2 = "com.android.settings/com.android.settings.Settings$WirelessSettingsActivity",
			ANDROIDOS10_NETWORKINTERNET_SCREEN = "com.android.settings/com.android.settings.Settings$ConnectionsSettingsActivity",

			ANDROID_LOCATION_SCREEN = "com.android.settings/com.android.settings.Settings$LocationSettingsActivity",
			ANDROID_LOCATION_SCREEN2 = "com.android.settings/.Settings$LocationSettingsActivity",
					
			ANDROID_APPINFO_SCREEN  = "com.android.settings/com.android.settings.applications.InstalledAppDetails",
			
			ANDROID_FAKE_GPS_APP = "com.lexa.fakegps",
			ANDROID_MOTOROLA_OS_UPDATE_APP = "com.motorola.ccc.ota",

			ANDROID_UIAUTOMATOR2_SERVER_APP = "io.appium.uiautomator2.server",
			ANDROID_APPIUM_SETTINGS_APP = "io.appium.settings",
			ANDROID_UIAUTOMATOR2_SERVER_TEST_APP = "io.appium.uiautomator2.server.test",
			ANDROID_APPIUM_NL_SERVICE = "io.appium.settings/io.appium.settings.NLService",

			ANDROID_LEAKCANARY_APP = "com.premise.android.qa.debug/leakcanary.internal.activity.LeakLauncherActivity",
	
			ANDROID_APP_CRASH_TITLE = "id=android:id/alertTitle", // Premise has stopped
			ANDROID_APP_CRASH_CLOSE_BUTTON = "id=android:id/aerr_close",
			ANDROID_APP_CRASH_CLOSE_BUTTON2 = "id=android:id/aerr_restart",
			ANDROID_PREMISE_ANR_DIALOG = "Application Not Responding: com.premise.android.qa.debug",
	
			ANDROID_PREMISE_WELCOME_SCREEN = " com.premise.android.qa.debug/com.premise.android.onboarding.welcome.WelcomeActivity",
			ANDROID_PREMISE_SIGNUP_SCREEN = "com.premise.android.qa.debug/com.premise.android.onboarding.signup.SignUpActivity",
			ANDROID_PREMISE_MARKETHOME_SCREEN = "com.premise.android.qa.debug/com.premise.android.home2.MainActivity",
			ANDROID_PREMISE_SPLASH_SCREEN = "com.premise.android.qa.debug/com.premise.android.activity.launch.LaunchActivity",
			ANDROID_PREMISE_APPCRASH_SCREEN = "com.premise.android.qa.debug/com.willowtreeapps.hyperion.crash.CrashActivity",
			ANDROID_PREMISE_APPCRASH_STACKTRACE_ID = "id=stacktrace",
	
			ENABLE_STATUS = "enable",
			DISABLE_STATUS = "disable",
	
			iOS_APPLICATION="xpath=//XCUIElementTypeApplication[@name=\"Settings\"]",
			
			iOS_SETTINGS_TAB="id=bottom_tab_4",
			iOS_NOFITICATION_SETTINGS="id=settings_notifications_label",
			
			iOS_LOCATION="predicatestring=type=='XCUIElementTypeButton'  AND label contains 'Location'", 
		    iOS_LOCATION_ALWAYS="xpath=//XCUIElementTypeCell[@name=\"Always\"]", 
		    iOS_PREMISEAPP="xpath=//XCUIElementTypeButton[@name=\"breadcrumb\"]", 
		    iOS_LOCATION_NEVER="xpath=//XCUIElementTypeCell[@name=\"Never\"]",   
	        iOS_LOCATION_IPHONE7 = "predicatestring=type=='XCUIElementTypeButton'  AND label contains 'Location'",
			iOS_PRECISELOCATION = "iosclasschain=**/XCUIElementTypeSwitch[`label == \"Precise Location\"`]",
			iOS_PREMISE_FROM_SETTING = "name=Premise",
	        iOS_SETTINGAPP_GENERALOPTION = "name=General",
			iOS_SETTINGAPP_STORAGEOPTION = "name=iPhone Storage";
	
	public  static final int
			ANDROID_IMPLICIT_WAIT_TIME_SECONDS = 10;
}
