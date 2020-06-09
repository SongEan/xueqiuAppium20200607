package wework.page;

import org.openqa.selenium.By;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-08
 * Time: 22:35
 */
public class Wework extends BasePage {
    By scheduleButton = By.id("adv");

    public Wework() {
        super("com.tencent.wework", ".launch.LaunchSplashActivity");
    }

    public SchedulePage toSchedule() {
        click(scheduleButton);
        return new SchedulePage(driver);
    }
}
