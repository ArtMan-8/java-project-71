package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Parser {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String loadDataFromFile(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path).trim();
    }

    public static Map<String, Object> fileDataToJsonMap(String content) throws Exception {
        return MAPPER.readValue(content, new TypeReference<>() { });
    }
}
