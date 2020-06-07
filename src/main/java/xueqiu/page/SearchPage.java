package xueqiu.page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-07
 * Time: 07:44
 */
public class SearchPage {
    AndroidDriver<MobileElement> driver;
    private By nameLocation = By.id("name");

    public SearchPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    /**
     * 搜索
     *
     * @param keyword
     * @return
     */
    @Step("搜索")
    public SearchPage search(String keyword) {
        driver.findElementById("com.xueqiu.android:id/search_input_text").sendKeys(keyword);
        return this;
    }

    /**
     * 获取搜索结果列表
     *
     * @return
     */
    @Step("获取搜索结果列表")
    public List<String> getSearchList() {
        List<String> nameList = new ArrayList<>();
        for (MobileElement name : driver.findElements(nameLocation)) {
            nameList.add(name.getText());
        }
        return nameList;
    }

    /**
     * 获取股价
     *
     * @return
     */
    @Step("获取股价")
    public double getPrice() {
        driver.findElement(nameLocation).click();
        return Double.valueOf(driver.findElement(By.id("current_price")).getText());
    }
}
