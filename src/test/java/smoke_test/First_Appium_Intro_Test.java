package smoke_test;

import common.TestBaseClass;
import org.testng.annotations.Test;

public class First_Appium_Intro_Test extends TestBaseClass {
    @Test
    void Appium_Tutorial_Page() {
        testFunctionality("1st Flow");
        appiumHomePage.clickOnIntroToAppiumButton();
        appiumHomePage.scrollToViewBasedOnStartingText("Desired Capabilities");

    }
}
