package testFramework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-23
 * Time: 09:41
 */
class FactoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void createDriver() {
        BasePage basePage = Factory.createDriver("web");
        TestCasePOJO testCasePOJO = basePage.load("/testFramework/webuiauto.yaml");
        basePage.run(testCasePOJO);
    }
}