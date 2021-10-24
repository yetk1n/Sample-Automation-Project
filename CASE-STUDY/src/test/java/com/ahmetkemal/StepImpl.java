package com.ahmetkemal;


import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;

import helper.StoreHelper;
import io.appium.java_client.MobileElement;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class StepImpl extends DriverImpl {
    private Logger logger = LoggerFactory.getLogger(getClass());


    public MobileElement findElementValue(String key){
        String value = StoreHelper.INSTANCE.findElementInfoByKey(key).getAndroidValue();
        String type = StoreHelper.INSTANCE.findElementInfoByKey(key).getAndroidType();
        if(type.equals("xpath")){
            if(appiumDriver.findElements(By.xpath(value)).size() > 0) {
                return appiumDriver.findElement(By.xpath(value));
            }
            else Assert.fail(key + " element does not exist!");
        }
        else if (type.equals("id")){
            if(appiumDriver.findElements(By.id(value)).size() > 0) {
                return appiumDriver.findElement(By.id(value));

            }
            else Assert.fail(key + " element does not exist!");
        }
        return appiumDriver.findElement(By.id(value));
    }

    @Step({"Wait <seconds> seconds"})
    public void waitBySecond(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    @Step({"Does <key> element Exist"})
    public void doesExistElement(String key) {
        String value = StoreHelper.INSTANCE.findElementInfoByKey(key).getAndroidValue();
        String type = StoreHelper.INSTANCE.findElementInfoByKey(key).getAndroidType();

        if(type.equals("xpath")){
            if(appiumDriver.findElements(By.xpath(value)).size() > 0) {
                takeScreenshot();
                logger.info(key + " element exists.");
            }
            else {
                takeScreenshot();
                Assert.fail(key + " element does not exist!");
            }
        }
        else if (type.equals("id")){
            if(appiumDriver.findElements(By.id(value)).size() > 0) {
                takeScreenshot();
                logger.info(key + " element exists.");
            }
            else {
                takeScreenshot();
                Assert.fail(key + " element does not exist!");
            }
        }

    }

    @Step({"Click element by <key>"})
    public void clickByKey(String key) throws InterruptedException {
        findElementValue(key).click();
        logger.info("Clicked to " + key);
        Thread.sleep(2000);
    }

    @Step("Go Back")
    public static void goBack() {
        appiumDriver.navigate().back();
        appiumDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

}


