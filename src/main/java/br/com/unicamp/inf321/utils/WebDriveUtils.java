package br.com.unicamp.inf321.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriveUtils {

	private static final WebDriver webDriver;
	static {
		webDriver = new FirefoxDriver();
	}

	public static void openURL(String url) {
		try {
			Thread.sleep(1000);
			webDriver.get(url);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
