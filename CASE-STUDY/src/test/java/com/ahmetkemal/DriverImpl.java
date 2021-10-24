package com.ahmetkemal;

import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.BeforeSpec;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DriverImpl {
    public static AppiumDriver<MobileElement> appiumDriver;
    private Logger logger = LoggerFactory.getLogger(getClass());


    public String takeScreenshot() {
        TakesScreenshot driver = (TakesScreenshot) appiumDriver;
        Date dateNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd_hhmmss_SSS");
        String fileName = "screenshot_" + ft.format(dateNow) + ".png";
        File screenshotFile = new File(Paths.get(System.getenv("gauge_screenshots_dir"), fileName).toString());
        File tmpFile = driver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(tmpFile, screenshotFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotFile.getName();
    }

    @BeforeSpec
    public void initializeDriver() throws MalformedURLException {
        String deviceName = System.getenv("DEVICE_NAME");
        String deviceUDID = System.getenv("DEVICE_UDID");
        String appPackage = System.getenv("APP_PACKAGE");
        String appActivity = System.getenv("APP_ACTIVITY");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "10");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, deviceUDID);
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);

        URL url = new URL("http://localhost:4723/wd/hub");
        appiumDriver = new AndroidDriver(url,desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }



    @AfterSpec
    public void afterScenario() throws InterruptedException {
        takeScreenshot();
        appiumDriver.quit();
    }

}
