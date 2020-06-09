package xueqiu.testcase;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import xueqiu.page.MainActivity;
import xueqiu.page.SearchPage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-07
 * Time: 07:53
 */
class SearchPageTest {
    static SearchPage searchPage;

    @BeforeAll
    static void setUp() {
//        mainActivity = new MainActivity();
//       searchPage = mainActivity.toSearchPage();
        searchPage = new MainActivity().toSearchPage();
    }

    @AfterAll
    static void tearDown() {
        searchPage.quit();
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "alibaba,   阿里巴巴",
                    "jd,    京东"
            }
    )
    void search(String keyword, String name) {
        assertEquals(searchPage.search(keyword).getSearchList().get(0), name);
    }

    @Test
    void getPrice() {
        assertTrue(searchPage.search("jd").getPrice() > 200);
    }
}