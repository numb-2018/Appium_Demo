package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileMethod {

	public static final Properties prop = new Properties();

	public static void loadPropertyFile(File file) {
		FileInputStream fileInput = null;

		try {
			fileInput = new FileInputStream(file);

		} catch (FileNotFoundException e) {
			// File not found
			e.printStackTrace();
		}

		try {
			prop.load(fileInput);
		} catch (IOException e) {
			// File not loaded
			e.printStackTrace();
		}

	}
	

}
