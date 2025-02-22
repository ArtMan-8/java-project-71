package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;
import java.util.List;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String data1 = loadDataFromFile(filepath1);
        String data2 = loadDataFromFile(filepath2);

        String fileExtension = getFileExtension(filepath1);
        Map<String, Object> mappedData1 = Parser.getMappedData(data1, fileExtension);
        Map<String, Object> mappedData2 = Parser.getMappedData(data2, fileExtension);

        List<Map<ChangedKey, Object>> diffList = Comparison.form(mappedData1, mappedData2);
        return Formater.print(diffList, format);
    }

    private static String loadDataFromFile(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path).trim();
    }

    private static String getFileExtension(String filepath) {
        String[] split = filepath.split("\\.");
        return split[split.length - 1];
    }
}
