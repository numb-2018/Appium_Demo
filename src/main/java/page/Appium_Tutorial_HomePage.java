package page;

import common.TestBaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.LogUtil;


public class Appium_Tutorial_HomePage extends TestBaseClass {
    public AndroidDriver<?> driver;
    private static Log log = LogUtil.getLog(Appium_Tutorial_HomePage.class);

    public Appium_Tutorial_HomePage(AppiumDriver<?> tempDriver) {
        this.driver = (AndroidDriver<?>) tempDriver;
        PageFactory.initElements(tempDriver, this);
    }

    @FindBy(id = "com.application.appiumtutorial:id/btn_appium_intro")
    WebElement IntroToAppiumButton;



    public void clickOnIntroToAppiumButton() {
        try {

            clickOnElement(IntroToAppiumButton);
            stepInfo("Clicked on Introduction To Appium Button");

        } catch (Exception e) {
            stepFail("Unable to click on Introduction To Appium Button");
            e.printStackTrace();
        }

    }





}
