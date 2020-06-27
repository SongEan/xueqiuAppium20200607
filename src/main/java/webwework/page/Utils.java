package webwework.page;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-01
 * Time: 15:27
 */
public class Utils {

    /**
     * 添加cookie，注意要先打开页面地址后再调用
     *
     * @param driver
     * @param fileName
     */
    public static void addCookie(WebDriver driver, String fileName) {

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
//                读取cookie.txt每一行内容
                StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
                String name = stringTokenizer.nextToken();
                String value = stringTokenizer.nextToken();
                String domain = stringTokenizer.nextToken();
                String path = stringTokenizer.nextToken();

                Date expiry = null;
                String dt = stringTokenizer.nextToken();
                if (!dt.equals("null")) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    expiry = simpleDateFormat.parse(dt);
                }

                boolean isSecure = Boolean.parseBoolean(stringTokenizer.nextToken());
//                拼接cookie
                Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                driver.manage().addCookie(cookie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存cookie到文件
     *
     * @param driver
     * @param fileName
     */
    public static void saveCookie(WebDriver driver, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Cookie cookie : driver.manage().getCookies()) {
                //           cookie所有值
                bufferedWriter.write(
                        cookie.getName() + ";" +
                                cookie.getValue() + ";" +
                                cookie.getDomain() + ";" +
                                cookie.getPath() + ";" +
                                cookie.getExpiry() + ";" +
                                cookie.isSecure()
                );
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
