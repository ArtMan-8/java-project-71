package hexlet.code;

import java.util.TreeSet;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String data1 = Parser.loadDataFromFile(filepath1);
        String data2 = Parser.loadDataFromFile(filepath2);

        var diff = Differ.diffJson(data1, data2);
        return diff;
    }

    private static String diffJson(String data1, String data2) throws Exception {
        Map<String, Object> json1 = Parser.fileDataToJsonMap(data1);
        Map<String, Object> json2 = Parser.fileDataToJsonMap(data2);

        TreeSet<String> allKeys = new TreeSet<String>();
        allKeys.addAll(json1.keySet());
        allKeys.addAll(json2.keySet());
        System.out.println(json1.keySet());
        System.out.println(json2.keySet());

        var result = new StringBuilder();

        result.append("{\n");
        for (String key : allKeys) {
            if (json1.containsKey(key) && json2.containsKey(key)) {
                if (json1.get(key).equals(json2.get(key))) {
                    result.append("    ").append(key).append(": ").append(json1.get(key)).append("\n");
                } else {
                    result.append("  - ").append(key).append(": ").append(json1.get(key)).append("\n");
                    result.append("  + ").append(key).append(": ").append(json2.get(key)).append("\n");
                }

            }

            if (json1.containsKey(key) && !json2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(json1.get(key)).append("\n");
            }

            if (!json1.containsKey(key) && json2.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(json2.get(key)).append("\n");
            }
        }
        result.append("}\n");

        return result.toString();
    }
}
