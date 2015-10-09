package br.com.unicamp.inf321.helper;

import java.io.File;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author OtonielIsidoro
 */
public class Helper {

    /**
     * Random number generator.
     * Will be used to create random data used for input in test.
     */
    static private Random random = new Random(System.currentTimeMillis());

    /**
     * Timeout time in seconds used for waiting for element(s) to show up.
     */
    final static int timeOut = 30;

    /**
     * Generates a random number with 1 to max digits.
     *
     * @param max Maximum length of digits.
     * @return The random number
     */
    public static int getRandomInt(int max) {
        return random.nextInt(max) + 1;
    }

    /**
     * Creates an instance of the Firefox WebDriver.
     */
    private static class WebDriverHolder {
        private static final WebDriver INSTANCE = new FirefoxDriver();
    }

    /**
     * If not already created, creates the singleton webdriver object.
     *
     * @return the singleton webdriver object
     */
    public static WebDriver getInstance() {
        return WebDriverHolder.INSTANCE;
    }
    
    public static void takeScreenshot(String fileName) throws Exception{
    	File scrFile = ((TakesScreenshot) getInstance()).getScreenshotAs(OutputType.FILE);
    	// Now you can do whatever you need to do with it, for example copy somewhere
    	FileUtils.copyFile(scrFile, new File(fileName));
    }

    /**
     * Will wait for a specified web element to appear. If not found
     * an assertion will fail.
     *
     * @param by The description of the element
     * @return The matching element if found.
     */
    public static WebElement WaitForElement(By by) {
        for (int second = 0; ; second++) {
            if (second >= timeOut) {
                Assert.fail("timeout");
            }
            WebElement element = null;
            try {
                element = getInstance().findElement(by);
                return element;
            } catch (Exception e) {
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Will wait for a specified web element(s) to appear. If not found
     * an assertion will fail.
     *
     * @param by The description of the element
     * @return A list of matching element(s) if found.
     */
    public static List<WebElement> WaitForElements(By by) {
        for (int second = 0; ; second++) {
            if (second >= timeOut) {
                Assert.fail("timeout");
            }
            List<WebElement> elements = null;
            try {
                elements = getInstance().findElements(by);
                return elements;
            } catch (Exception e) {
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
