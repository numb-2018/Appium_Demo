package utilities;

import com.adobe.internal.xmp.impl.Base64;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {



//To take screenshot and return screenshot data as Base64 to integrate with extent Report
	public static String getScreenshot(AppiumDriver<?> driver, String status){
		try {
		// below line is just to append the date format with the screenshot name to
		// avoid duplicate names
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/PremiseReport/" + status + "_" + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		// Returns the captured file path
		
		FileInputStream fileInputStream = new FileInputStream(finalDestination);
		byte[] bytes = new byte[(int)finalDestination.length()];
		fileInputStream.read(bytes);
		String encodedBase64 = new String(Base64.encode(bytes));
		fileInputStream.close();
		
//		To delete stored Images		
		FileUtils.deleteQuietly(finalDestination);
		
//		return encoded image;
		return "data:image/png;base64," + encodedBase64;

		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
