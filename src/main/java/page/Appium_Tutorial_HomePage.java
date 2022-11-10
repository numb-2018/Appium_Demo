package page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.logging.Log;
import org.openqa.selenium.support.PageFactory;
import utilities.LogUtil;


public class Appium_Tutorial_HomePage {
    private AndroidDriver<?> driver;
    private static Log log = LogUtil.getLog(Appium_Tutorial_HomePage.class);

    public Appium_Tutorial_HomePage(AppiumDriver<?> tempDriver) {
        this.driver = (AndroidDriver<?>) tempDriver;
        PageFactory.initElements((AndroidDriver<?>) tempDriver, this);
    }


}
