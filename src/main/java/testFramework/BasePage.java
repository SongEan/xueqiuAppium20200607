package testFramework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-18
 * Time: 09:53
 */
public class BasePage {

    public void click(HashMap<String, Object> hashMap) {
        System.out.println("click-----");
        System.out.println(hashMap);
    }

    public void find() {

    }

    public void sendKeys(HashMap<String, Object> map) {

        System.out.println("sendKeys-----");
        System.out.println(map);
    }

    public void action(HashMap<String, Object> map) {
        System.out.println("action-----");
        System.out.println(map);
    }


    public void getText() {

    }

    /**
     * 读取的yaml配置文件字段进行点击，元素识别等操作解析
     *
     * @param testCase
     */
    public void run(TestCasePOJO testCase) {
        testCase.steps.stream().forEach(m -> {
/*            if (m.keySet().contains("click")) {
                click((HashMap<String, Object>) m.get("click"));
            }*/

//          获取yaml中click: {id: search2}类型字段
            if (m.containsKey("click")) {
                HashMap<String, Object> by = (HashMap<String, Object>) m.get("click");
                click(by);
            }

//            解析 sendkeys: "输入text！"
            if (m.containsKey("sendKeys")) {
                sendKeys(m);
            }

            if (m.containsKey("action")) {
                action(m);
            }
        });
    }


    /**
     * 读取yaml配置文件
     *
     * @param path
     * @return
     */
    public TestCasePOJO load(String path) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        TestCasePOJO testCasePOJO = null;
        try {
            testCasePOJO = objectMapper.readValue(BasePage.class.getResourceAsStream(path), TestCasePOJO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testCasePOJO;

    }
}
