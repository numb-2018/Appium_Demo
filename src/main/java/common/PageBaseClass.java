package common;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import driver.CreateDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.logging.Log;
import utilities.LogUtil;
import utilities.ScreenShot;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.fail;


public class PageBaseClass {

	protected AndroidDriver<?> driver;
	private static final Log log = LogUtil.getLog(PageBaseClass.class);
	//For Extent Report Generation
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extentReporter;
	public static ExtentTest test;
	ScreenShot screenshot;


	protected AppiumDriver<?> getDriver(){
		if (driver == null) {
			driver = (AndroidDriver<?>) CreateDriver.getDriver();
		}
		return this.driver;
	}




	/**
	 * Methods for Report Generation and Steps Validation
	 */
	protected void reporterInitialization(String testBedName) {
		String dateName = new SimpleDateFormat("yyyyMMdd" + "_" + "hhmmss").format(new Date());
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/AppiumReport/" + "ExtentReport_" + testBedName + "_" + dateName + ".html");
		htmlReporter.config().setDocumentTitle("Android Premise Mobile App");
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
				test.pass(msg, MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot.getScreenshot(driver, "Pass")).build());
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
			test.fail(msg, MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot.getScreenshot(driver, "Fail")).build());
			test.info("Test Cases Execution Stopped");
			fail(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void stepFail(String msg) {

		try {
			log.info(msg);
			test.fail(msg, MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot.getScreenshot(driver, "Fail")).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void stepSkip(String msg) {
		try {
			log.info(msg);
			test.skip(msg, MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot.getScreenshot(driver, "Skip")).build());
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

}










	/*protected WebElement findElementByXpath(String locator) {
			return driver.findElement(By.xpath(locator));
	}
	
	protected WebElement findElementById(String locator) {
		return driver.findElement(By.id(locator));
	}
	
	protected boolean verifyPageTitle(WebElement locator, String expectedPageTitle){
		try {
			waitForVisibilityOfElement(locator);
			assertEquals(locator.getText() , expectedPageTitle);
			return true;
			}
			catch (AssertionError e) {
				e.printStackTrace();
				return false;
			}
			catch (Exception e) {
			e.printStackTrace();
			return false;
			}
	}
	
	protected void clickOnElement(WebElement locator) throws Exception{
			waitForVisibilityOfElement(locator);
			log.info("Clicking on Element having locator:::" + locator);
			(locator).click();
			log.info("Clicked on Element having locator:::" + locator);	
	}
	protected boolean clickOnElement(WebElement element, String failureString) throws Exception {
		try {
			waitForVisibilityOfElement((element));
			if (element.isDisplayed()) {
				clickOnElement(element);
				log.info("Element found and Displayed::: true");
				return true;
			} else {
				log.info("Element is not Displayed::: FALSE::: " + failureString);
				return false;
			}
		} catch (Exception e) {
			log.error("This is exception isElementByLocatorNotDisplayed:  " + element
					+ ". Error message:::" + e.getMessage());
			return false;
		}
	}
	protected void waitForVisibilityOfElement(WebElement locator) {
		try {
		WebDriverWait wait=new WebDriverWait(driver, 5);
		log.info("Waiting for Visibility of Element having locator::" + locator);
		wait.until(ExpectedConditions.visibilityOf(locator));

		}
		catch (Exception e) {
		log.error("Element not visible for locator ::" + locator);
			e.printStackTrace();
		}
	}
	
	
	protected boolean isElementDisplayed(WebElement element) {
		try {
			waitForVisibilityOfElement((element));
			if (element.isDisplayed()) {
				log.info("Element found and Displayed::: true");
				return true;
			} else {
				log.info("Element is not Displayed::: FALSE");
				return false;
			}
		}
		catch (Exception e) {
		log.error("This is exception isElementByLocatorNotDisplayed:  " + element
				+ ". Error message:::" + e.getMessage());
		return false;
		}
		
		
	}
	
	
*/

	
	/**
	 * Overloaded verifyText method to assert text visibility and expected text
	 * @param locator represents web element on the screen
	 * @param expectedText represents web element's text
	 *
	 */
	/*protected void verifyText(WebElement locator, String expectedText) {
		try {
			assertTrue(locator.isDisplayed());
			assertEquals(locator.getText() , expectedText);
			test.info("<b>"+expectedText+"</b>" + " is displayed");
			}catch (ElementNotVisibleException e) {
				test.fail("<b>"+ expectedText +"</b>" + " is not visible");
			}catch (AssertionError e) {
				test.fail("<b>"+ locator.getText() +"</b>" + " is displayed instead of " + "<b>"+ expectedText+"</b>");
			}catch (Exception e) {
				test.fail(expectedText + " locator is not present");
			}
	}
	
	protected void verifyText(WebElement locator, String expectedText , String failureString) {
		try {
			assertTrue(locator.isDisplayed());
			assertEquals(locator.getText() , expectedText);
			test.info(expectedText + " is displayed");
			}catch (AssertionError e) {
				test.fail(failureString);
			}catch (Exception e) {
				test.fail(expectedText + " locator is not present");
			}
	}
	protected void verifyText(WebElement locator, String expectedText , String failureString, String successString) {
		try {
			assertTrue(locator.isDisplayed());
			assertEquals(locator.getText() , expectedText);
			test.info(successString);
			}catch (AssertionError e) {
				test.fail(failureString);
			}catch (Exception e) {
				test.fail(expectedText + " locator is not present");
			}
	}
	protected void verifyTextContains(WebElement locator, String expectedText, String failureString) {
		try {
			assertTrue(locator.isDisplayed());
			assertTrue(locator.getText().contains(expectedText));
			test.info(expectedText + " is displayed");
		} catch (AssertionError e) {
			test.fail(failureString);
		} catch (Exception e) {
			test.fail(expectedText + " locator is not present");
		}

	}*/



	/**
	 * To verify that passed element is displayed or not
	 * @param locator represents web element on the screen
	 * @param iconName represents web element's text
	 */
	
	/*protected void verifyImageIsDisplayed(WebElement locator, String iconName) {
		try {
			assertTrue(locator.isDisplayed());
			test.info("<b>"+iconName+"</b>" + " is displayed");
			}catch (ElementNotVisibleException e) {
				test.fail("<b>"+ iconName +"</b>" + " is not visible");
			}catch (AssertionError e) {
				test.fail("<b>"+ iconName +"</b>" + " is not displayed");
			}catch (Exception e) {
				test.fail(iconName + " is not present");
			}
		
		
	}*/

	
	
	/**
	 * Scroll page UP by 50%
	 */
	/*protected void scrollScreenUpBy50Percent() {
		try {
		TouchAction action = new TouchAction(driver);
		Dimension size	=driver.manage().window().getSize();
		int width=size.width;
		int height=size.height;	
		int middleOfX=width/2;
		int startYCoordinate= (int)(height*.7);
		int endYCoordinate= (int)(height*.2);
		action.press(PointOption.point(middleOfX, startYCoordinate))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(middleOfX, endYCoordinate));
		action.perform();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/**
	 * Scroll page Down by 50%
	 */
	/* protected void scrollScreenDownBy50Percent() {
		try {
		TouchAction action = new TouchAction(driver);
		Dimension size	=driver.manage().window().getSize();
		int width=size.width;
		int height=size.height;	
		int middleOfX=width/2;
		int startYCoordinate= (int)(height*.2);
		int endYCoordinate= (int)(height*.7);
		action.press(PointOption.point(middleOfX, startYCoordinate))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(middleOfX, endYCoordinate));
		action.perform();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/**
	 * Scroll Horizontally
	 */
	/* protected void scrollHorizontally() {
		try {
		TouchAction action = new TouchAction(driver);
		Dimension size	=driver.manage().window().getSize();
		int width=size.width;
		int height=size.height;			
		int middleOfY=height/2;
		int startXCoordinate= (int)(width*.9);
		int endXCoordinate= (int)(width*.2);
		action.press(PointOption.point(startXCoordinate, middleOfY))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(endXCoordinate, middleOfY)).release();
		action.perform();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/**
	 * Scroll until specific element is present
	 */
	/* public void scrollToViewBasedOnExactText(String text) {
		 driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
				+ ".scrollIntoView(new UiSelector().text(\""+text+"\").instance(0))"));
	}*/
	
	
	/**
	 * Scroll until specific element is present
	 */
	/* public void scrollToViewBasedOnText(String text) {
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
				+ ".scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))"));
	}*/

	/**
	 * Scroll until specific starting text is present
	 */
	/* public void scrollToViewBasedOnStartingText(String text) {
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))"
				+ ".scrollIntoView(new UiSelector().textStartsWith(\""+text+"\").instance(0))"));
	}*/

	/**
	 * Method to input text inside any search box
	 * @param searchBox represents web element on the screen
	 * @param text represents web element's text
	 */
	/*protected void inputText(WebElement searchBox, String text) {
		try {
			searchBox.sendKeys(text);
			test.info("<b>" + text + "</b>" + " text is entered in searchBox");
		} catch (ElementNotVisibleException e) {
			testCaseFail(searchBox + " is not visible");
		} catch (Exception e) {
			testCaseFail(text + " text could not be entered");
		}
	}*/

	/**
	 * Method to verify arguments are present or not
	 * @param element represents web element on the screen
	 * @param elementText represent web element's text
	 */
	/*protected void verifyElementPresent(WebElement element, String elementText) {
		try {
			assertTrue(element.isDisplayed());
			test.info(elementText + ": is displayed");
		}catch (ElementNotVisibleException e) {
			test.fail(elementText + ": is not visible");
		}catch (AssertionError e) {
			test.fail(elementText + ": is not displayed");
		}catch (Exception e) {
			test.fail(elementText + ":  is not present");
		}
	}*/

	/**
	 * Scroll using given point option coordinates (START point using element coordinates)
	 * @param startX represents Start element's X co-ordinates
	 * @param startY represents Start element's Y co-ordinates
	 * @param endX represent End element's X co-ordinates
	 * @param endY represents End element's Y co-ordinates
	 */
	/*public void screenScrollFromElementCoordinates(double startX, double startY, double endX, double endY) {
		Dimension windowSize = driver.manage().window().getSize();
		new TouchAction(driver).press(PointOption.point((int) startX, (int) startY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
				.moveTo(PointOption.point((int) ((windowSize.width) * endX), (int) ((windowSize.height) * endY)))
				.release().perform();
	}*/
	
	/**
	 * Method to verify web element  is present or not
	 * @param element
	 * @return true or false
	 */

	/*protected boolean verifyElementPresent(WebElement element) {
		try {
			
			if (element.isDisplayed()) {
				return true;
			} else {
				return false;
			}
		}
		catch (Exception e) {
		log.error("This is exception isElementByLocatorNotDisplayed:  " + element
				+ ". Error message:::" + e.getMessage());
		return false;
		}
	}*/

	/**
	 * Method to verify web element  is present or not
	 *
	 * @param element
	 * @return true or false
	 */
	/*protected boolean verifyElementEnabled(WebElement element) {
		try {
			if (element.isEnabled()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error("This is exception isElementByLocatorEnabled:  " + element
					+ ". Error message:::" + e.getMessage());
			return false;
		}
	}*/


	/**
	 * Method to scroll up the screen
	 */
	/*public void googleScreenScrollUp() {
		try {
			screenScrollPressOptionCoordinates(0.30, 0.60, 0.50, 0.30);
		} catch (Exception e) {
			log.error("Failed to googleScreenScrollUp due to :::" + e.getMessage());
		}
	}*/

	/**
	 * Generic method to scroll the screen using given point option coordinates
	 */
	/*public void screenScrollPressOptionCoordinates(double startX, double startY, double endX, double endY) {
		Dimension windowSize = driver.manage().window().getSize();
		new TouchAction(driver)
				.press(PointOption.point((int) ((windowSize.width) * startX), (int) ((windowSize.height) * startY)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
				.moveTo(PointOption.point((int) ((windowSize.width) * endX), (int) ((windowSize.height) * endY)))
				.release().perform();
	}*/

	/**
	 * Method to refresh the screen
	 */
	/*public boolean screenPullToRefresh() {
		try {
			int i = 0;
			while (i < 4) {
				screenScrollPressOptionCoordinates(0.50, 0.30, 0.50, 0.85);
				i++;
			}
			return true;
		} catch (Exception e) {
			log.error("Unable to Pull and Refresh the screen");
		}
		return false;
	}*/

	/**
	 * Method to refresh the screen
	 */
	/*public boolean screenPullToRefresh(int numberOfTimes) {
		try {
			int i = 0;
			while (i < numberOfTimes) {
				screenScrollPressOptionCoordinates(0.50, 0.30, 0.50, 0.90);
				wait(1000);
				i++;
			}
			return true;
		} catch (Exception e) {
			log.error("Unable to Pull and Refresh the screen");
		}
		return false;
	}*/

	/**
	 * Generic method to scroll the Filter Result Screen & End of Year Card Landing Screen
	 */
	/*public boolean screenPullForYearChange(int numberOfTimes) {
		try {
			int i = 0;
			while (i < numberOfTimes) {
				screenScrollPressOptionCoordinates(0.30, 0.50, 0.30, 0.80);
				Thread.sleep(1000);
				i++;
			}
			return true;
		} catch (Exception e) {
			log.error("Unable to Pull and Refresh the screen");
		}
		return false;
	}*/

	/**
	 * Method to find attribute value of element Param: locator: element locator
	 * attribute: Attribute which value have to find
	 */
	/*protected boolean verifyIsCheckBoxSelected(WebElement element, String failureString) {
		String text = "";
		try {
			text = element.getAttribute("checked");
			log.info(" checked Attribute value: " + text);
		} catch (Exception ex) {
			testCaseFail("Exception occurred while getting element attribute 'checked' for:::" + element + "\n"+failureString+ "Error::"
					+ ex.getMessage());
		}
		return text.contains("true");
	}*/


	/**
	 * Method to wait for element is present and click-able
	 */
	/*protected boolean verifyIsElementClickable(WebElement element, String failureString) {
		boolean flag = false;
		try {
			waitForVisibilityOfElement(element);
			String value = element.getAttribute("clickable");
			if (value.equalsIgnoreCase("true"))
				flag = true;
			log.info("Element found ::elementToBeClickable::with locator : " + element);
		} catch (Exception e) {
			testCaseFail(failureString);
			e.printStackTrace();
		}
		return flag;
	}*/


	/**
	 * To verify that passed text is similar or not
	 * @param actualText represents text from property or any other file/locator
	 * @param expectedText represents expected text
	 */
	/*protected void verifyTextContains(String actualText, String expectedText, String failureString) {
		try {
			assertTrue(actualText.contains(expectedText));
			test.info(expectedText + " is displayed");
		} catch (AssertionError e) {
			test.fail(failureString);
		} catch (Exception e) {
			test.fail(expectedText + " locator is not present");
		}
	}
}*/
