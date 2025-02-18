package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.TreeSet;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String fileExtension = getFileExtension(filepath1);

        String data1 = loadDataFromFile(filepath1);
        String data2 = loadDataFromFile(filepath2);

        Map<String, Object> mappedData1 = Parser.getMappedFileData(data1, fileExtension);
        Map<String, Object> mappedData2 = Parser.getMappedFileData(data2, fileExtension);

        return switch (format) {
            case "stylish" -> stylishDiffPrint(mappedData1, mappedData2);
            default -> throw new IllegalArgumentException("Unknown output format");
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

    private static String stylishDiffPrint(Map<String, Object> fileData1, Map<String, Object> fileData2) {
        TreeSet<String> allKeys = new TreeSet<>();
        allKeys.addAll(fileData1.keySet());
        allKeys.addAll(fileData2.keySet());

        var result = new StringBuilder();

        result.append("{\n");
        for (String key : allKeys) {
            if (fileData1.containsKey(key) && fileData2.containsKey(key)) {
                if (fileData1.get(key).equals(fileData2.get(key))) {
                    result.append("    ").append(key).append(": ").append(fileData1.get(key)).append("\n");
                } else {
                    result.append("  - ").append(key).append(": ").append(fileData1.get(key)).append("\n");
                    result.append("  + ").append(key).append(": ").append(fileData2.get(key)).append("\n");
                }

            }

            if (fileData1.containsKey(key) && !fileData2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(fileData1.get(key)).append("\n");
            }

            if (!fileData1.containsKey(key) && fileData2.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(fileData2.get(key)).append("\n");
            }
        }
        result.append("}");

        return result.toString();
    }
}
