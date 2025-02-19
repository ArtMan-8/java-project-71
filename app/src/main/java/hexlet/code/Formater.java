package hexlet.code;

import java.util.List;
import java.util.Map;

public class Formater {
    public static String stylish(List<Map<String, Object>> diffList) {
        var result = new StringBuilder();

        result.append("{\n");
        for (Map<String, Object> map : diffList) {
            String key = (String) map.get("key");
            String status = (String) map.get("status");
            Object value = map.get("value");

            switch (status) {
                case "unchanged":
                    result.append("    ").append(key).append(": ").append(value).append("\n");
                    break;
                case "changed":
                    result.append("  - ").append(key).append(": ").append(map.get("valueOld")).append("\n");
                    result.append("  + ").append(key).append(": ").append(map.get("valueNew")).append("\n");
                    break;
                case "removed":
                    result.append("  - ").append(key).append(": ").append(value).append("\n");
                    break;
                case "added":
                    result.append("  + ").append(key).append(": ").append(value).append("\n");
                    break;
                default:
                    throw new IllegalArgumentException("Unknown key status: " + status);
            }
        }
        result.append("}");

        return result.toString();
    }
}
