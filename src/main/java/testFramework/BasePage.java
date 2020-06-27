package testFramework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * Date: 2020-06-18
 * Time: 09:53
 */
public class BasePage {
    List<PageObjectModel> pages = new ArrayList<>();

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

        if (map.containsKey("page")) {
            String action = map.get("action").toString();
            String pageName = map.get("page").toString();
            pages.forEach(pom -> System.out.println("****" + pom.name));

            pages.stream().filter(
                    pom -> pom.name.equals(pageName))
                    .findFirst()
                    .get()
                    .methods.get(action)
                    .forEach(step -> {
                        action(step);
                    });
        } else {
            if (map.containsKey("click")) {
                HashMap<String, Object> by = (HashMap<String, Object>) map.get("click");
                click(by);
            }

//            解析 sendkeys: "输入text！"
            if (map.containsKey("sendKeys")) {
                sendKeys(map);
            }
        }
    }


    public void getText() {

    }

    public void quit() {

    }

    /**
     * 读取的yaml配置文件字段进行点击，元素识别等操作解析
     *
     * @param testCase
     */
    public void run(TestCaseModel testCase) {
        System.out.println(testCase.name);
        System.out.println(testCase.descriptions);
        testCase.steps.stream().forEach(m -> {
/*            if (m.keySet().contains("click")) {
                click((HashMap<String, Object>) m.get("click"));
            }*/


            if (m.containsKey("action")) {
                action(m);
            }

//          获取yaml中click: {id: search2}类型字段
            if (m.containsKey("click")) {
                HashMap<String, Object> by = (HashMap<String, Object>) m.get("click");
                click(by);
            }

//            解析 sendkeys: "输入text！"
            if (m.containsKey("sendKeys")) {
                sendKeys(m);
            }
        });
    }


    /**
     * 读取测试用例yaml配置文件
     *
     * @param path
     * @return
     */
    public TestCaseModel load(String path) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        TestCaseModel testCaseModel = null;
        try {
            testCaseModel = objectMapper.readValue(
                    BasePage.class.getResourceAsStream(path),
                    TestCaseModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testCaseModel;
    }


    /**
     * 读取单个po页面yaml配置文件
     *
     * @param path
     * @return
     */
    public PageObjectModel loadPage(String path) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        PageObjectModel pageObjectModel = null;
        try {
            pageObjectModel = objectMapper.readValue(
                    new File(path),
                    PageObjectModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pageObjectModel;
    }

    /**
     * 读取加载所有page页面yaml文件
     *
     * @param dir
     */
    public void loadPages(String dir) {
        Stream.of(new File(dir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains("Page");
            }
        })).forEach(path -> {
            path = dir + "/" + path;
            System.out.println(path);
            pages.add(loadPage(path));
        });
    }
}
