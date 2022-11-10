package utilities;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.logging.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class TerminalExecutor {
	private static Log log = LogUtil.getLog(TerminalExecutor.class);

	/*
	 * executeCommand :- execute commandLine with given CommandLine command)
	 */
	public static void executeCommand(CommandLine command) {
		try {
			log.info("CommandLine ::" + command);
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			DefaultExecutor executor = new DefaultExecutor();
			executor.setExitValue(1);
			executor.execute(command, resultHandler);
			Thread.sleep(2000);
		} catch (Exception e) {
			log.error("Exception encountered in executeCommand CommandLine::: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * executeCommand :- execute commandLine with given String command
	 */
	public static void executeCommand(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			// Do not print logs for BuildInstaller.executeCurlToDownloadAPK(.)
			if (!command.contains("apk"))
				log.info("***executeCommand****************" + command);
			getNormalOutputTerminal(process);
		} catch (Exception e) {
			log.error("Exception encountered in executeCommand String::: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * common method to get the terminal output
	 */
	public static String getNormalOutputTerminal(Process process) throws IOException {
		String s = "";
		String value = "";
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		while ((s = stdInput.readLine()) != null) {
			value = value.concat(s);
			log.info("Normal O/P::"+s);
		}
		while ((s = stdError.readLine()) != null) {
			log.error("ERROR O/P::"+s);
		}
		return value;
	}

	/*
	 * executeCommand :- execute terminal and return the normal output
	 */
	public static String getExecuteCommandResult(String command) {
		String value = "";
		try {
			Process process = Runtime.getRuntime().exec(command);
			log.info("***getExecuteCommandResult cmd****************" + command);
			value = getNormalOutputTerminal(process);
		} catch (Exception e) {
			log.error("Exception encountered in getExecuteCommandResult::: " + e.getMessage());
			e.printStackTrace();
		}
		return value;
	}

	/*
	 * execute terminal command and get all console output (normal and error)
	 */
	public static String getExecuteCommandResultAllOutput(String command) {
		String s = "";
		String value = "";
		try {
			Process p = Runtime.getRuntime().exec(command);
			log.info("***getExecuteCommandResultAllOutput cmd****************" + command);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((s = stdInput.readLine()) != null) {
				value = value.concat(s);
				log.info("Normal O/P::"+s);
			}
			while ((s = stdError.readLine()) != null) {
				value = value.concat(s);
				log.error("ERROR O/P::"+s);
			}
			log.info("All terminal Output::" + value);
		} catch (Exception e) {
			log.error("Exception encountered in getExecuteCommandResultAllOutput::: " + e.getMessage());
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * Method to execute terminal commands for grep
	 */
	public static String getExecuteCommandResultGrep(String command) {
		String value = "";
		try {
			String[] cmd = { "/bin/sh", "-c", command };
			Process process = Runtime.getRuntime().exec(cmd);
			log.info("***getExecuteCommandResultGrep****************" + Arrays.deepToString(cmd));
			value = getNormalOutputTerminal(process);
		} catch (Exception e) {
			log.error("Exception encountered in getExecuteCommandResultGrep::: " + e.getMessage());
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * Method to execute Mac OS terminal command
	 */
	public static void executeMacTerminalCommand(String command) {
		try {
			String[] cmd = { "/bin/sh", "-c", command };
			Process process = Runtime.getRuntime().exec(cmd);
			// Do not print logs for BuildInstaller.executeCurlToDownloadAPK(.)
			if (!command.contains("apk"))
				log.info("***executeMacTerminalCommand****************" + Arrays.deepToString(cmd));
			getNormalOutputTerminal(process);
		} catch (Exception e) {
			log.error("Exception encountered in executeMacTerminalCommand::: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Method to execute special terminal commands
	 */
	public static void executeTerminalCommand(String command) {
		if (MachineInfo.isWindowsOSMachine())
			executeCommand("cmd /c " + command);
		else
			executeMacTerminalCommand(command);
	}

	/**
	 * Method to get the expected terminal line output
	 */
	public static String getExecuteCommandResult(String command, String expectedText) {
		String s = "";
		String value = "";
		try {
			Process p = Runtime.getRuntime().exec(command);
			log.info("*** getExecuteCommandResult..****************" + command);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((s = stdInput.readLine()) != null) {
				log.info("Normal O/P::"+s);
				if (s.contains(expectedText)) {
					log.info("Expected text matched :::");
					value = s;
					break;
				}
			}
			while ((s = stdError.readLine()) != null) {
				log.error("ERROR O/P::"+s);
			}
		} catch (Exception e) {
			log.error("Exception encountered in getExecuteCommandResult::: " + e.getMessage());
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * Method to execute terminal command via Process builder
	 */
	public static void executeCommandProcessBuilder(String[] command, long timeout) {
		try {
			log.info("***executeCommandProcessBuilder****************" + Arrays.deepToString(command));
			Process process = new ProcessBuilder(command).start();
			process.waitFor(timeout, TimeUnit.SECONDS);
		} catch (Exception ex) {
			log.error("Exception encountered in executeCommandProcessBuilder:::" + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	/**
	 * Main method for unit testing
	 */
	public static void main(String[] args) {
//		executeTerminalCommand("java -version");
//		getExecuteCommandResultAllOutput("java -version");
	}
}
