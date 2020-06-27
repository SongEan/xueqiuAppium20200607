package webwework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import testFramework.WebBasePage;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;


/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-01
 * Time: 22:35
 */
public class ContentPage extends WebBasePage {

    By addMemberText = By.linkText("添加成员");
    By usernameInput = By.name("username");
    By deleteText = By.linkText("删除");
    By confirmText = By.linkText("确认");
    By memberSearchInput = By.id("memberSearchInput");
    By clearSearchInputButton = By.id("clearMemberSearchInput");
    By acctIdInput = By.name("acctid");
    By mobileInput = By.name("mobile");
    By saveButton = By.cssSelector(".js_btn_save");
    By addButton = By.cssSelector(".member_colLeft_top_addBtn");
    By addDepartmentText = By.linkText("添加部门");
    By department_tagName = By.name("name");
    By chooseDepartment = By.linkText("选择所属部门");
    By chooseDepartmentID = By.xpath("//*[@id='1688853803546277_anchor']");
    By addDepartmentConfirmButton = By.linkText("确定");
    By party_name = By.id("party_name");
    By tagTab = By.linkText("标签");
    By addTagButton = By.linkText("添加");
    By tagName = By.cssSelector(".ww_commonCntHead_title_inner_text");


    public ContentPage(RemoteWebDriver driver) {
        super(driver);
    }

    /**
     * 添加新用户
     *
     * @param username
     * @param acctid
     * @param memberAdd_phone
     */
    public ContentPage addMember(String username, String acctid, String memberAdd_phone) {
        while (driver.findElements(this.usernameInput).size() == 0) {
            click(addMemberText);
        }

        sendKeys(usernameInput, username);
        sendKeys(acctIdInput, acctid);
        sendKeys(mobileInput, memberAdd_phone);
        click(saveButton);

        return this;
    }

    /**
     * 搜索成员
     *
     * @param keyword
     * @return
     */
    public ContentPage searchMember(String keyword) {
        sendKeys(memberSearchInput, keyword);
        return this;
    }

    /**
     * 删除搜索到的成员
     */
    public ContentPage deleteMember() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        click(deleteText);
        click(confirmText);
//        删除搜索框输入内容
        click(clearSearchInputButton);
        return this;
    }

    /**
     * @return
     */
    public String getUserName() {
        return driver.findElement(By.cssSelector(".member_display_cover_detail_name")).getText();
    }

    /**
     * 上传通讯录
     *
     * @param path
     * @return
     */
    public ContentPage importFromFile(URL path) {
        String path_utf = "";
        try {
            path_utf = URLDecoder.decode(path.getFile(), "UTF-8");
            System.out.println(path_utf);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        click(By.cssSelector(".ww_operationBar:nth-child(1) .ww_btn_PartDropdown_left"));
        click(By.linkText("文件导入"));
        upload(By.name("file"), path_utf);
        click(By.linkText("导入"));
        click(By.linkText("完成"));

        return this;
    }


    /**
     * 添加部门
     *
     * @param department
     * @return
     */
    public ContentPage addDepartment(String department) {
        click(addButton);
        click(addDepartmentText);
        sendKeys(department_tagName, department);
        click(chooseDepartment);
        click(chooseDepartmentID);
        click(addDepartmentConfirmButton);
        return this;
    }

    //    获取部门名称
    public String getPartyName() {
        return driver.findElement(party_name).getText();
    }


    public ContentPage addTag(String tagName) {
        click(tagTab);
        click(addTagButton);
        sendKeys(department_tagName, tagName);
        return this;
    }

    public String getTagName() {
        return driver.findElement(tagName).getText();
    }
}
