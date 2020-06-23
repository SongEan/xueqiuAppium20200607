package wework.page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-08
 * Time: 22:46
 */
public class SchedulePage extends AppBasePage {


    private By addButton = By.id("gym");
    private By taskName = By.id("b2k");
    private By saveButton = byXpathText("保存");
    private By taskList = By.id("gg_");


    public SchedulePage(AndroidDriver<MobileElement> driver) {
        super(driver);
        this.driver = driver;
    }

    public SchedulePage addSchedule(String name, String date) {
        click(addButton);
        sendKeys(taskName, name);
        click(saveButton);
        return this;
    }

    public List<String> getSchedule(String date) {
        if (date != null) {
        }
        return driver.findElements(taskList).stream().map(x -> x.getText()).collect(Collectors.toList());
    }
}
