package wework.page;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-08
 * Time: 23:07
 */
class SchedulePageTest {
    private static Wework wework;

    @BeforeAll
    static void beforeAll() {
        wework = new Wework();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void afterAll() {
        wework.quit();
    }

    @Test
    void addSchedule() {
        assertTrue(
                wework.toSchedule()
                        .addSchedule("游戏大厅", null)
                        .getSchedule("游戏大厅")
                        .contains("游戏大厅"));
    }

    @Test
    void getSchedule() {
    }
}