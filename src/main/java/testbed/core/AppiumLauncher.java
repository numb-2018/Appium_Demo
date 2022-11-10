package testbed.core;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.logging.Log;
import utilities.LogUtil;
import utilities.MachineInfo;
import utilities.TerminalExecutor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AppiumLauncher {

	private static Log log = LogUtil.getLog(AppiumLauncher.class);
	public static ThreadLocal<AppiumDriverLocalService> appiumDriverLocalService = new ThreadLocal<>();
	private static String OS = MachineInfo.OS;




	/**
	 * Method to start appium server on Windows OS using AppiumDriverLocalService
	 */
	public static void startAppiumSession( ) {
		try {
			log.info("*************** Starting Appium Server for Android Device:::" +  " on OS :::"
					+ OS.toUpperCase());

			String userDir = System.getProperty("user.dir");
			if (!userDir.contains("AppiumTutorialAppAutomation"))
				userDir = userDir + "/" + "AppiumTutorialAppAutomation";
			String logFile = userDir + "/logs/appiumLogs/" +  "/" +  "_"
					+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + "__d.txt";



			AppiumDriverLocalService appiumDriverLocalService;
			AppiumServiceBuilder builder = new AppiumServiceBuilder()
					.withAppiumJS(new File(getAPPIUMJSPATH()))
					.usingDriverExecutable(new File(getNODEJSPATH()))
					.withArgument(GeneralServerFlag.LOG_LEVEL, "info")
					.withLogFile(new File(logFile))
					.withIPAddress("127.0.0.1")
					.withArgument(AndroidServerFlag.SUPPRESS_ADB_KILL_SERVER)
					.usingPort(4723);
					//.usingAnyFreePort();
			appiumDriverLocalService = builder.build();
			appiumDriverLocalService.start();
			int appiumTryStartCount = 0;
			while (appiumTryStartCount < 3) {
				if (appiumDriverLocalService.isRunning()) {
					log.info("Appium Server started successfully on " + OS + " for:::" + "Android");
					break;
				}
				log.info("Retrying Appium server to start on " + OS + " " + appiumTryStartCount);
				appiumTryStartCount++;
			}
			setServer(appiumDriverLocalService);
		} catch (Exception e) {
			log.error("Unable to start appium server on " + OS + e.getMessage());
			e.printStackTrace();
		}
	}

	/***
	 * Method to get AppiumJS path from either system variable or config properties
	 ** @return APPIUMJSPATH*/
	public static String getAPPIUMJSPATH() {
		return System.getenv("APPIUMJSPATH");
	}


	/*** Method to get NodeJS path from either system variable or config properties*
	 **@return
	 **/
	public static String getNODEJSPATH(){
		return System.getenv("NODEJSPATH");
	}


	private static AppiumDriverLocalService getServer() {
		return appiumDriverLocalService.get();
	}

	private static void setServer(AppiumDriverLocalService server) {
		appiumDriverLocalService.set(server);
	}

	/*
	 * closeAppiumSession :- Close all open appium session
	 */
	public static boolean closeAppiumSession() {
		try {
			if (MachineInfo.isWindowsOSMachine()) {
				log.info("Executing closeAppiumSession command on OS:::"+OS.toUpperCase()+" for port number:::");
				TerminalExecutor.executeCommand("cmd /c taskkill /f /im node.exe");
				TerminalExecutor.executeCommand("cmd /c taskkill /f /im adb.exe");
			} else if (MachineInfo.isMacOSMachine()) {
				log.info("Executing closeAppiumSession command on OS:::"+OS.toUpperCase()+" for port number:::");
				TerminalExecutor.executeCommand("sh " + AppiumLauncher.class.getClassLoader().getResource("killport_mac.sh").getFile()+ " ");
			}
		} catch (Exception ex) {
			log.error("Exception occurred on closeAppiumSession method :::" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
		return true;
	}



	
/*	*//*
	 * launchAppiumSession :- Launches appium session
	 *//*
	public static boolean launchAppiumSession(TestBedConfig testBedConfig, String testBedName) {
		try {
			logger.info("***************Starting Appium Server for Device Name::" + testBedName+ " on OS :::"+OS.toUpperCase());
			CommandLine command = null;
			if (MachineInfo.isWindowsOSMachine()) {
				logger.info("Preparing command for Windows:::TestBed:::" + testBedName);
				command = commandForWindows(testBedConfig, testBedName);
			} else if (MachineInfo.isMacOSMachine()) {
				logger.info("Preparing command for Mac");
				command = commandForMac(testBedConfig, testBedName);
			}
			if (command != null) {
				TerminalExecutor.executeCommand(command);
				logger.info("Preparing : Appium server to start>>>>>");
				String server_url = String.format("http://127.0.0.1:%d/wd/hub",
						Integer.parseInt(testBedConfig.getCurrentPortMap().get(testBedName)));
				if (!waitUntilAppiumStarts(server_url)) {
					logger.info("Failed to start Appium servers");
				}
			}else{
				logger.error("CommandLine cmd is null in launchAppiumSession");
			}
		} catch (Exception ex) {
			logger.error("Exception occurred on launchAppiumSession method :::" + ex.getMessage());
			ex.printStackTrace();
		}
		return true;
	}

	*//**
	 * Method to start appium server on Windows OS using AppiumDriverLocalService
	 *//*
		public static void startAppiumSession(Properties prop, String testBedName, int portNo) {
		try {
			logger.info("***************Starting Appium Server for Android Device:::" + testBedName + " on OS :::"
					+ OS.toUpperCase());
			
			String userDir = System.getProperty("user.dir");
			if (!userDir.contains("PremiseAppAutomation"))
				userDir = userDir + "/" + "PremiseAppAutomation";
			String logFile = userDir + "/logs/appiumLogs/" + testBedName + "/" + testBedName + "_"
					+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + "__d.txt";

			AppiumDriverLocalService appiumDriverLocalService;
			AppiumServiceBuilder builder = new AppiumServiceBuilder()
				//	.withAppiumJS(new File(testBedConfig.getAppiumPath()))
					.withAppiumJS(new File(getAPPIUMJSPATH(prop)))
				//	.usingDriverExecutable(new File(testBedConfig.getNodePath()))
					.usingDriverExecutable(new File(getNODEJSPATH(prop)))
					.withArgument(GeneralServerFlag.LOG_LEVEL, "info")
					//.withArgument("--command-timeout", "500")
					.withLogFile(new File(logFile))
					.withIPAddress("127.0.0.1")
					.withArgument(AndroidServerFlag.SUPPRESS_ADB_KILL_SERVER)
					//.usingPort(Integer.parseInt(
					//getPortStatus(testBedConfig.currentPortMap.get(testBedName), testBedName, testBedConfig)));
				//	.usingAnyFreePort()
					.usingPort(portNo);
			appiumDriverLocalService = builder.build();
			appiumDriverLocalService.start();
			int appiumTryStartCount = 0;
			while (appiumTryStartCount < 3) {
				if (appiumDriverLocalService.isRunning()) {
					logger.info("Appium Server started successfully on " + OS + " for:::" + "Android");
		//			DeviceInfo.getDeviceType());
					break;
				}
				logger.info("Retrying Appium server to start on " + OS + " " + appiumTryStartCount);
				appiumTryStartCount++;
			}
			setServer(appiumDriverLocalService);
		} catch (Exception e) {
			logger.error("Unable to start appium server on " + OS + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void startAppiumServer(Properties prop, String testBedName,int portNo) throws Exception {
		try{
		logger.info("***************Starting Appium Server for Android Device:::" + testBedName+ " on OS :::"+OS.toUpperCase());
		AppiumDriverLocalService appiumDriverLocalService;

		AppiumServiceBuilder builder = new AppiumServiceBuilder().withAppiumJS(new File(prop.getProperty("APPIUMJSPATH")))
				.withArgument(GeneralServerFlag.LOG_LEVEL, "info").withLogFile(new File(System.getProperty("user.dir")
						+ "/target/" + testBedName + "_"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + "__d.txt"))

				.withIPAddress("127.0.0.1").withArgument(AndroidServerFlag.SUPPRESS_ADB_KILL_SERVER)

		//		.usingPort(Integer.parseInt(getPortStatus(testBedConfig.currentPortMap.get(testBedName),testBedName,testBedConfig)));
				.usingPort(portNo);
		appiumDriverLocalService = builder.build();
		appiumDriverLocalService.start();
		setServer(appiumDriverLocalService);
		}
		catch(Exception e){
			logger.error("Unable to start appium for Mac OS:::"+e.getMessage());
			e.printStackTrace();
		}

	}

	private static AppiumDriverLocalService getServer() {
		return appiumDriverLocalService.get();
	}

	private static void setServer(AppiumDriverLocalService server) {
		appiumDriverLocalService.set(server);
	}

	*//*
	 * closeAppiumSession :- Close all open appium session
	 *//*
	public static boolean closeAppiumSession(String portNumber) {
		try {
			if (MachineInfo.isWindowsOSMachine()) {
				logger.info("Executing closeAppiumSession command on OS:::"+OS.toUpperCase()+" for port number:::"+portNumber);
				TerminalExecutor.executeCommand("cmd /c taskkill /f /im node.exe");
				TerminalExecutor.executeCommand("cmd /c taskkill /f /im adb.exe");
			} else if (MachineInfo.isMacOSMachine()) {
				logger.info("Executing closeAppiumSession command on OS:::"+OS.toUpperCase()+" for port number:::"+portNumber);
				TerminalExecutor.executeCommand("sh " + AppiumLauncher.class.getClassLoader().getResource("killport_mac.sh").getFile()+ " " + portNumber);
			}
		} catch (Exception ex) {
			logger.error("Exception occurred on closeAppiumSession method :::" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	*//**
	 * commandForWindows :- Execute commands on Windows
	 *//*
	private static CommandLine commandForWindows(TestBedConfig testBedConfig, String testBedName) {
		CommandLine command = new CommandLine("cmd");
		command.addArgument("/c");
		command.addArgument("\"" + testBedConfig.getNodePath() + "\"");
		command.addArgument("\"" + testBedConfig.getAppiumJSPath() + "\"");
		command.addArgument("--address", false);
		command.addArgument("127.0.0.1");
		command.addArgument("--port", false);
		command.addArgument(getPortStatus(testBedConfig.currentPortMap.get(testBedName),testBedName, testBedConfig));
		command.addArgument("-bp", false);
		command.addArgument(getBootstrapPort(getPortStatus(testBedConfig.currentPortMap.get(testBedName),testBedName,testBedConfig)));
		command.addArgument("-U", false);
		command.addArgument(testBedConfig.currentTestBedMap.get(testBedName));
		command.addArgument("--no-reset", false);
		return command;
	}

	*//*
	 * commandForMac :- Execute commands on Mac
	 *//*
	private static CommandLine commandForMac(TestBedConfig testBedConfig, String testBedName) {
		CommandLine command = new CommandLine(testBedConfig.getNodePath());
		command.addArgument(testBedConfig.getAppiumJSPath());
		command.addArgument("--address", false);
		command.addArgument("127.0.0.1");
		command.addArgument("--port", false);
		command.addArgument(getPortStatus(testBedConfig.currentPortMap.get(testBedName),testBedName,testBedConfig));
		command.addArgument("-bp", false);
		command.addArgument(getBootstrapPort(getPortStatus(testBedConfig.currentPortMap.get(testBedName),testBedName,testBedConfig)));
		command.addArgument("-U", false);
		command.addArgument(testBedConfig.currentTestBedMap.get(testBedName));
		command.addArgument("--no-reset", false);
		return command;
	}

	*//*
	 * getBootstrapPort :- Get Bootstrap port number
	 *//*
	private static String getBootstrapPort(String port) {
		logger.info("Port number "+port);
		Integer bpPort = Integer.valueOf(port) + 100;
		return bpPort.toString();
	}

	*//*
	 * waitUntilAppiumStarts :- Returns True if Appium server starts and false
	 * otherwise
	 *//*
	private static boolean waitUntilAppiumStarts(String server_url) throws Exception {
		long timeOut = 20000;
		final URL status = new URL(server_url + "/sessions");
		try {
			logger.info("Waiting for Appium server to start>>>>");
			new UrlChecker().waitUntilAvailable(timeOut, TimeUnit.MILLISECONDS, status);
			logger.info("Appium Server started successfully");
			return true;
		} catch (UrlChecker.TimeoutException e) {
			logger.error("Exception in waitUntilAppiumStarts method:::" + e.getMessage());
			return false;
		} catch (Exception e) {
			logger.error("Exception in waitUntilAppiumStarts method:::" + e.getMessage());
			return false;
		}
	}
	
	public static void taskKill() {
		TerminalExecutor.executeCommand("taskkill /f /im node.exe");
		TerminalExecutor.executeCommand("taskkill /f /im adb.exe");
	}
	
	*//*
	 * executeCommand :- 
	 * *//*
	public static String getPortStatus(String port, String testBedName, TestBedConfig testBedConfig) {
		boolean flag = true;
		int portNo = Integer.parseInt(port);
		try {
			for (int i = portNo; i < 5000; i++) {
				Process p = executeCommandForPort("netstat -an");
				logger.info("Getting Port Status");
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String inputLine;
				flag = true;
				logger.info("Checking for port " + portNo);
				while ((inputLine = stdInput.readLine()) != null) {
					if (inputLine.contains("127.0.0.1:" + portNo)) {
						flag = false;
						logger.info(portNo + " Is currently in use");
					}
				}
				if (flag) {
					break;
				}
				portNo++;
				stdInput.close();
			}
			if (flag) {
				logger.info(port);
			}

		} catch (IOException e) {
			logger.error("getPortStatus Unable to find available port due to::"+e.getMessage());
			e.printStackTrace();
		}
		testBedConfig.setCurrentPortMap(Integer.toString(portNo), testBedName);
		return Integer.toString(portNo);
	}

	*//*
	 * executeCommand :- Find JSON value from JSON object
	 *//*
	public static Process executeCommandForPort(String command) {
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			logger.error("executeCommandForPort IO Exception encountered due to::"+e.getMessage());
			e.printStackTrace();
		}
		return p;
	}
	
	*//**
	 * Method to get AppiumJS path from either system variable or config properties
	 * @return APPIUMJSPATH
	 *//*
	public static String getAPPIUMJSPATH(Properties prop) {
		logger.info("AppiumJS path set in Environemnet variable:" + System.getenv("APPIUMJSPATH"));
		return System.getenv("APPIUMJSPATH")==null ? prop.getProperty("APPIUMJSPATH"): System.getenv("APPIUMJSPATH"); 
	}
	*//**
	 * Method to get NodeJS path from either system variable or config properties
	 * @return
	 *//*
	public static String getNODEJSPATH(Properties prop) {
		logger.info("NodeJS path set in Environemnet variable:" + System.getenv("NODEJSPATH"));
		return System.getenv("NODEJSPATH")==null ? prop.getProperty("NODEJSPATH"):System.getenv("NODEJSPATH"); 
	}*/
}
