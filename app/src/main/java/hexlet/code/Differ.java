package hexlet.code;

import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String data1 = Parser.loadDataFromFile(filepath1);
        String data2 = Parser.loadDataFromFile(filepath2);

        Map<String, Object> json1 = Parser.fileDataToJsonMap(data1);
        Map<String, Object> json2 = Parser.fileDataToJsonMap(data2);

        System.out.println(json1);
        System.out.println(json2);
        return "";
    }
}
