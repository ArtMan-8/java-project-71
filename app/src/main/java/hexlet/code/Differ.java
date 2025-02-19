package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Objects;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String fileExtension = getFileExtension(filepath1);

        String data1 = loadDataFromFile(filepath1);
        String data2 = loadDataFromFile(filepath2);

        Map<String, Object> mappedData1 = Parser.getMappedFileData(data1, fileExtension);
        Map<String, Object> mappedData2 = Parser.getMappedFileData(data2, fileExtension);

        List<Map<String, Object>> diffList = getDiffList(mappedData1, mappedData2);

        return switch (format) {
            case "stylish" -> Formater.stylish(diffList);
            default -> throw new IllegalArgumentException("Unknown output format: " + format);
        };
    }

    private static String loadDataFromFile(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path).trim();
    }

    private static String getFileExtension(String filepath) {
        String[] split = filepath.split("\\.");
        return split[split.length - 1];
    }

    private static List<Map<String, Object>> getDiffList(Map<String, Object> fileData1, Map<String, Object> fileData2) {
        List<Map<String, Object>> diffList = new ArrayList<>();

        TreeSet<String> allKeys = new TreeSet<>();
        allKeys.addAll(fileData1.keySet());
        allKeys.addAll(fileData2.keySet());

        for (String key : allKeys) {
            Object value1 = fileData1.get(key);
            Object value2 = fileData2.get(key);

            Map<String, Object> map = new HashMap<>();
            map.put("key", key);

            if (fileData1.containsKey(key) && fileData2.containsKey(key)) {
                if (Objects.equals(value1, value2)) {
                    map.put("value", value1);
                    map.put("status", "unchanged");
                } else {
                    map.put("valueOld", value1);
                    map.put("valueNew", value2);
                    map.put("status", "changed");
                }
            }

            if (fileData1.containsKey(key) && !fileData2.containsKey(key)) {
                map.put("value", value1);
                map.put("status", "removed");
            }

            if (!fileData1.containsKey(key) && fileData2.containsKey(key)) {
                map.put("value", value2);
                map.put("status", "added");
            }

            diffList.add(map);
        }

        return diffList;
    }
}
