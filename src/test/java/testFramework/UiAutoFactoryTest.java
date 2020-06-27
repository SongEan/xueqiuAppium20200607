package testFramework;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-23
 * Time: 09:41
 */

/**
 * 统一管理测试用例yaml
 */
class UiAutoFactoryTest {
    static BasePage basePage;

//    driver 执行结束后退出

    @AfterAll
    static void afterAll() {
        basePage.quit();
    }


    @ParameterizedTest
    @MethodSource
    void createDriver(TestCaseModel testCaseModel) {
        basePage.run(testCaseModel);
    }

    static Stream<TestCaseModel> createDriver() {

        basePage = UiAutoFactory.createDriver("web");
        basePage.loadPages("src/main/resources/testFramework");

        List<TestCaseModel> testCaseList = new ArrayList<>();
        Arrays.asList(
//                "/testFramework/webuiauto.yaml",
//                "/testFramework/webuiauto1.yaml",
                "/testFramework/webuiauto2.yaml"
        ).stream().forEach(path -> {

            TestCaseModel testCaseModel = basePage.load(path);
            testCaseList.add(testCaseModel);
        });

        return testCaseList.stream();
    }
}