package webwework.page;

import org.openqa.selenium.By;
import testFramework.WebBasePage;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-01
 * Time: 15:26
 */
public class MainPage extends WebBasePage {

    public String url = "https://work.weixin.qq.com/wework_admin/frame#index";

    public MainPage() {
        super();
        driver.get(url);
//        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.id("js_tips")));
        Utils.addCookie(driver, "weWorkCookie.txt");
        driver.get(url);
    }

    /**
     * 进入通讯录页面
     *
     * @return
     */
    public ContentPage toContent() {
        click(By.cssSelector("#menu_contacts"));
//        driver.findElement(By.cssSelector("#menu_contacts")).click();
        return new ContentPage(driver);
    }


}
