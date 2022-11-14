package common;

import driver.CreateDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import page.Appium_Tutorial_HomePage;
import testbed.core.AppiumLauncher;
import testbed.core.DeviceCapabilities;
import utilities.LogUtil;

import java.lang.reflect.Method;
import java.net.MalformedURLException;


public class TestBaseClass extends PageBaseClass{
    public static AndroidDriver<?> driver;
    private static Log log = LogUtil.getLog(TestBaseClass.class);
    public static Boolean captureScreenShoot  = false;


    // Page objects
    public Appium_Tutorial_HomePage appiumHomePage;


    @BeforeClass
    public void prepareBeforeTest(ITestContext configParameters) throws MalformedURLException {
        driverInit();
        reporterInitialization(configParameters.getName());
    }
    public void initializePageObject() {
        appiumHomePage = new Appium_Tutorial_HomePage(getDriver());
    }

    /**
     * Initialize driver
     */
    private void driverInit() {
        String deviceType = "Android";
        log.info("Initializing Appium driver for testBedName:::" +  " for device Type::" + deviceType);
        try {
            AppiumLauncher.startAppiumSession();
            log.info("Initializing local driver" );
           // driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            driver= CreateDriver.getDriver(
                    new DeviceCapabilities().setMobileDeviceCapabilities());
            log.info(deviceType + " Driver created!! Driver details :::" +driver);
        } catch (Exception e) {
            log.error("Unable to initialize driver :::" + e.getMessage());
            e.printStackTrace();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void StartTest(Method method) {
        initializePageObject();
        reporterStartTestExecution(method.getName());

    }





    /**
     * Method to check Screen Capture is ON/Off
     */
    public static Boolean checkCaptureScreenShot() {
        return captureScreenShoot;
    }











    /**
     * Method to unlock device
     */
    protected void unlockDevice() {
        try {
            ((AndroidDriver<?>)getDriver()).unlockDevice();
        } catch (Exception e) {
            log.error(" Exception encountered in unlockDevice::" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method to lock device
     */
    protected void lockDevice() {
        try {
            ((AndroidDriver<?>) getDriver()).lockDevice();
        } catch (Exception e) {
            log.error(" Exception encountered in lockDevice::" + e.getMessage());
            e.printStackTrace();
        }
    }



    protected void clickOnElement(WebElement locator) {
        waitForVisibilityOfElement(locator);
        log.info("Clicking on Element having locator:::" + locator);
        (locator).click();
        log.info("Clicked on Element having locator:::" + locator);
    }


    protected void waitForVisibilityOfElement(WebElement locator) {
        try {
            WebDriverWait wait=new WebDriverWait(getDriver(), 5);
            log.info("Waiting for Visibility of Element having locator::" + locator);
            wait.until(ExpectedConditions.visibilityOf(locator));

        }
        catch (Exception e) {
            log.error("Element not visible for locator ::" + locator);
            e.printStackTrace();
        }
    }

    /**
     * Scroll until specific starting text is present
     */
    public void scrollToViewBasedOnStartingText(String text) {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                + ".scrollIntoView(new UiSelector().textStartsWith(\""+text+"\").instance(0))"));
    }
    @AfterMethod(alwaysRun = true)
    public void endTest(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP) {
            stepSkip("Skipping Test Case : " + result.getSkipCausedBy());
        }
        reportGeneration();

    }


    @AfterClass
    public void close_test(){
        getDriver().quit();
       // closeAppiumSession();
    }
}