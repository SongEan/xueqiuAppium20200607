package xueqiu.page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-07
 * Time: 07:44
 */
public class SearchPage extends BasePage {
    private By nameLocation = By.id("name");

    public SearchPage(AndroidDriver<MobileElement> driver) {
        super(driver);
    }

  /*  public SearchPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }*/

    /**
     * 搜索
     *
     * @param keyword
     * @return
     */
//    @Step("搜索")
    public SearchPage search(String keyword) {
//        driver.findElementById("com.xueqiu.android:id/search_input_text").sendKeys(keyword);
        do {
            sendKeys(By.id("search_input_text"), keyword);
        } while (driver.findElements(nameLocation).size() <= 0);
        return this;
    }

    /**
     * 获取搜索结果列表
     *
     * @return
     */
//    @Step("获取搜索结果列表")
    public List<String> getSearchList() {
        List<String> nameList = new ArrayList<>();
/*        for (MobileElement name : driver.findElements(nameLocation)) {
            nameList.add(name.getText());
        }*/
        driver.findElements(nameLocation).forEach(element -> nameList.add(element.getText()));
        return nameList;
    }

    /**
     * 获取股价
     *
     * @return
     */
//    @Step("获取股价")
    public double getPrice() {
        click(nameLocation);
//        driver.findElement(nameLocation).click();
        return Double.valueOf(find(By.id("current_price")).getText());
    }
}
