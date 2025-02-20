package hexlet.code.formatters;

import hexlet.code.ChangedKey;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Json {
    public static String format(List<Map<ChangedKey, Object>> diffList) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diffList).trim();
    }
}
