package testFramework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-18
 * Time: 22:32
 */
class BasePageTest {
    private static BasePage basePage;

    @BeforeAll
    static void beforeAll() {
        basePage = new BasePage();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void run() {
        TestCaseModel testCaseModel = basePage.load("/testFramework/uiauto.yaml");
        basePage.run(testCaseModel);
    }

    @Test
    void load() {
        TestCaseModel testCaseModel = basePage.load("/testFramework/uiauto.yaml");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(testCaseModel));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    @Test
    void runPOM(){
        basePage.loadPages("src/main/resources/testFramework");
        TestCaseModel testCaseModel = basePage.load("/testFramework/webuiauto2.yaml");
        basePage.run(testCaseModel);

    }


}