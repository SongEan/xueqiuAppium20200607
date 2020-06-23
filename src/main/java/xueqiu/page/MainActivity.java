package xueqiu.page;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-07
 * Time: 07:42
 */
public class MainActivity extends BasePage{

    public MainActivity() {

    }

    /**
     * 进入搜索
     *
     * @return
     */
//    @Step("进入搜索")
    public SearchPage toSearchPage() {
        click(By.id("home_search"));
//        MobileElement el1 = (MobileElement) driver.findElementById("com.xueqiu.android:id/home_search");
//        el1.click();
        return new SearchPage(driver);
    }


    public void toStock() {
        return;
    }
}
