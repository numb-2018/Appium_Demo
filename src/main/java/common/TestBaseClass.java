package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.logging.Log;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import testbed.core.AppiumLauncher;
import testbed.core.DeviceCapabilities;
import utilities.LogUtil;
import utilities.ScreenShot;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static driver.CreateDriver.getDriver;
import static org.testng.Assert.fail;


public class TestBaseClass {
    public static AndroidDriver<?> driver;
    private static Log log = LogUtil.getLog(TestBaseClass.class);
    public static ExtentHtmlReporter htmlReporter ;
    public static ExtentReports extentReporter;
    public static ExtentTest test;
    public static Boolean captureScreenShoot  = false;


    @BeforeClass
    public void prepareBeforeTest(ITestContext configParameters) throws MalformedURLException {
        driverInit();
       // startAppiumSession();


        reporterInitialization(configParameters.getName());
    }


    /**
     * Initialize driver
     */
    private void driverInit() {
        String deviceType = "Android";
        log.info("Initializing Appium driver for testBedName:::" +  " for device Type::" + deviceType);
        //AppiumDriver<?> driver = null;
        try {
            AppiumLauncher.startAppiumSession();
            log.info("Initializing local driver" );
           // driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            driver= (AndroidDriver<?>) getDriver(
                    new DeviceCapabilities().setMobileDeviceCapabilities());
            log.info(deviceType + " Driver created!! Driver details :::" +driver);
        } catch (Exception e) {
            log.error("Unable to initialize driver :::" + e.getMessage());
            e.printStackTrace();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void StartTest(Method method) {
       // driver.closeApp();
        //driver.launchApp();
        reporterStartTestExecution(method.getName());

    }





    /**
     * Method to check Screen Capture is ON/Off
     */
    public static Boolean checkCaptureScreenShot() {
        return captureScreenShoot;
    }

    protected  void reporterInitialization(String testBedName) {
        String dateName = new SimpleDateFormat("yyyyMMdd"+"_" +"hhmmss").format(new Date());
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/AppiumReport/"  +"ExtentReport_"+ testBedName +"_"+ dateName +".html");
        htmlReporter.config().setDocumentTitle("Android Appium Mobile App");
        htmlReporter.config().setReportName("Automation Execution Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        extentReporter = new ExtentReports();
        extentReporter.attachReporter(htmlReporter);

    }

    protected void reporterStartTestExecution(String tCaseName) {
        test = extentReporter.createTest(tCaseName);
        test.info("Application Cleared and Relaunched");
        test.info("Test Execution started for " + tCaseName);
    }

    protected void stepPass(String msg) {
        try {
            log.info(msg);
            if (TestBaseClass.checkCaptureScreenShot())
                test.pass(msg, MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot.getScreenshot(getDriver(), "Pass")).build());
            else
                test.pass(msg);
        } catch (Exception e) {
            e.printStackTrace();
            test.info(e.toString());
        }
    }

    protected void testCaseFail(String msg) {
        try {
            log.info(msg);
            test.fail(msg, MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot.getScreenshot(getDriver(), "Fail")).build());
            test.info("Test Cases Execution Stopped");
            fail(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void stepFail(String msg) {
        try {
            log.info(msg);
            test.fail(msg, MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot.getScreenshot(getDriver(), "Fail")).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void stepSkip(String msg) {
        try {
            log.info(msg);
            test.skip(msg, MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot.getScreenshot(getDriver(), "Skip")).build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void reportGeneration() {
        extentReporter.flush();
    }

    protected static void stepInfo(String msg) {
        log.info(msg);
        test.info(msg);
    }

    protected void stepError(String msg) {
        log.info(msg);
        test.error(msg);
    }

    protected void testFunctionality(String msg) {
        log.info(msg);
        test.assignCategory(msg);
    }

    protected Status currentTestStatus() {
        return test.getStatus();
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