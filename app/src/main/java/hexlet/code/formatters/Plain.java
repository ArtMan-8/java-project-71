package hexlet.code.formatters;

import hexlet.code.ChangedKey;
import hexlet.code.ChangedStatus;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(List<Map<ChangedKey, Object>> diffList) {
        var result = new StringBuilder();

        for (Map<ChangedKey, Object> map : diffList) {
            ChangedStatus status = (ChangedStatus) map.get(ChangedKey.STATUS);
            String key = "'" + map.get(ChangedKey.KEY) + "'";

            switch (status) {
                case ChangedStatus.CHANGED:
                    result.append("Property ").append(key).append(" was updated. From ")
                            .append(getFormatedValue(map, ChangedKey.VALUE_OLD)).append(" to ")
                            .append(getFormatedValue(map, ChangedKey.VALUE_NEW)).append("\n");
                    break;
                case ChangedStatus.REMOVED:
                    result.append("Property ").append(key).append(" was removed\n");
                    break;
                case ChangedStatus.ADDED:
                    result.append("Property ").append(key).append(" was added with value: ")
                            .append(getFormatedValue(map, ChangedKey.VALUE)).append("\n");
                    break;
                case ChangedStatus.UNCHANGED:
                    break;
                default:
                    throw new IllegalArgumentException("Unknown key status: " + status);
            }
        }

        return result.toString().trim();
    }

    private static String getFormatedValue(Map<ChangedKey, Object> map, ChangedKey key) {
        var value = map.get(key);

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        return String.valueOf(value);
    }
}
