package testFramework;

import wework.page.AppBasePage;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-23
 * Time: 09:37
 */
public class Factory {
    public static BasePage createDriver(String driverName) {
        if ("web".equals(driverName) || "selenium".equals(driverName)) {
            return new WebBasePage();
        }

        if ("app".equals(driverName) || "appium".equals(driverName)) {
            return new AppBasePage();
        }
        return null;
    }
}
