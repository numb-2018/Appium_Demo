package utilities;

import org.apache.commons.logging.Log;

public class MachineInfo {
	public static String OS = System.getProperty("os.name").toLowerCase();
	private static Log log = LogUtil.getLog(MachineInfo.class);

	/*
	 * Returns True for Windows OS machine
	 */
	public static boolean isWindowsOSMachine() {
		return OS.indexOf("win") >= 0;
	}

	/*
	 * Returns True for Mac OS machine
	 */
	public static boolean isMacOSMachine() {
		return OS.indexOf("mac") >= 0;
	}

	/**
	 * Main method for unit testing
	 */
	public static void main(String[] args) {
//		System.out.println(OS);
//		System.out.println(isWindowsOSMachine());
	}
}
