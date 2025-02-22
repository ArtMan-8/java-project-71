package hexlet.code;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new YAMLMapper();

    public static Map<String, Object> getMappedData(String data, String type) throws Exception {
        return switch (type) {
            case "json" -> JSON_MAPPER.readValue(data, new TypeReference<>() { });
            case "yaml", "yml" -> YAML_MAPPER.readValue(data, new TypeReference<>() { });
            default -> throw new IllegalArgumentException("Unknown file extension: " + type);
        };
    }
}
