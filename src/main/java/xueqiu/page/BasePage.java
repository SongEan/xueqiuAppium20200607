package xueqiu.page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-07
 * Time: 22:32
 */
public class BasePage {
    AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    public BasePage() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", "com.xueqiu.android.view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "RKK0217C19000101");
        desiredCapabilities.setCapability("noReset", "true");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
//        改善启动性能:跳过设备初始化，安装和运行设置应用程序或设置的权限
        desiredCapabilities.setCapability("skipDeviceInitialization", "true");
//        改善性能:关闭appium执行log
        desiredCapabilities.setCapability("skipLogcatCapture", "true");
//        改善性能:关闭uiAutomator2 server APP的安装
        desiredCapabilities.setCapability("skipServerInstallation", "true");

        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    public BasePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    /**
     * driver退出
     */
    public void quit() {
        driver.quit();
    }

    public MobileElement find(By by) {
        return driver.findElement(by);
    }

    /**
     * click方法
     *
     * @param by
     */
    public void click(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    /**
     * 输入内容方法
     *
     * @param by
     * @param string
     */
    public void sendKeys(By by, String string) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).sendKeys(string);
    }
}
