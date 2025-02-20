package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formater {
    public static String print(List<Map<ChangedKey, Object>> diffList, String format) {
        return switch (format) {
            case "stylish" -> Stylish.format(diffList);
            case "plain" -> Plain.format(diffList);
            default -> throw new IllegalArgumentException("Unknown output format: " + format);
        };
    }
}
