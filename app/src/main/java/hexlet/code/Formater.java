package hexlet.code;

import java.util.List;
import java.util.Map;

public class Formater {
    public static String stylish(List<Map<ChangedKey, Object>> diffList) {
        var result = new StringBuilder();

        result.append("{\n");
        for (Map<ChangedKey, Object> map : diffList) {
            String key = (String) map.get(ChangedKey.KEY);
            ChangedStatus status = (ChangedStatus) map.get(ChangedKey.STATUS);
            Object value = map.get(ChangedKey.VALUE);

            switch (status) {
                case ChangedStatus.UNCHANGED:
                    result.append("    ").append(key).append(": ").append(value).append("\n");
                    break;
                case ChangedStatus.CHANGED:
                    result.append("  - ").append(key).append(": ").append(map.get(ChangedKey.VALUE_OLD)).append("\n");
                    result.append("  + ").append(key).append(": ").append(map.get(ChangedKey.VALUE_NEW)).append("\n");
                    break;
                case ChangedStatus.REMOVED:
                    result.append("  - ").append(key).append(": ").append(value).append("\n");
                    break;
                case ChangedStatus.ADDED:
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
