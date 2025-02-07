package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Parser {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String loadDataFromFile(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path);
    }

    public static Map<String, Object> fileDataToJsonMap(String content) throws Exception {
        return mapper.readValue(content, new TypeReference<Map<String,Object>>(){});
    }
}
