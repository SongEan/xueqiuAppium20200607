package testFramework;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-01
 * Time: 23:05
 */
public class WebBasePage extends BasePage {
    public RemoteWebDriver driver;
    public WebDriverWait wait;


    public WebBasePage() {
//        choseBrowser();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    public WebBasePage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    /**
     * 浏览器选择
     * browser="chrome" mvn -Dtest=TestContent test
     *
     * @return
     */
    public RemoteWebDriver choseBrowser() {
        String browserName = System.getenv("browser");

        if (browserName.equalsIgnoreCase("firefox")) {
//            如果Firefox无法打开，设置安装路径
            System.setProperty("webdriver.firefox.bin", "Users/ssj/Documents/Tools/webDriver/geckodriver");
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("chrome")) {
//            设置ChromeDriver路径为系统属性
            System.setProperty("webdriver.chrome.driver", "/Users/ssj/Documents/Tools/webDriver/chromedriver");
//            实例化driver为ChromeDriver对象
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
//            设置IE浏览器驱动程序的所在路径为系统属性值
            System.setProperty("webdriver.ie.driver", "/Users/ssj/Documents/Tools/webDriver/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        return driver;

    }

    /**
     * driver退出
     */
    public void quit() {
        driver.quit();
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

    /**
     * 加载上传文件
     *
     * @param by
     * @param path
     */
    public void upload(By by, String path) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(path);
    }

    @Override
    public void click(HashMap<String, Object> hashMap) {
        super.click(hashMap);
        String key = (String) hashMap.keySet().toArray()[0];
        String value = (String) hashMap.values().toArray()[0];

        By by = null;
        if (key.equals("id")) {
            by = By.id(value);
        }
        if (key.equals("linkText")) {
            by = By.linkText(value);
        }
        if (key.equals("partialLinkText")) {
            by = By.partialLinkText(value);
        }

        click(by);
    }

//    重写BasePage中action方法解析webuiauto.yaml文件中action: get字段
    @Override
    public void action(HashMap<String, Object> map) {
        super.action(map);
        if (map.get("action").equals("get")) {
            driver.get(map.get("url").toString());
        }
    }
}
