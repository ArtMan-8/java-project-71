package hexlet.code;

import java.util.Objects;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

public class Comparison {
    public static List<Map<ChangedKey, Object>> form(
            Map<String, Object> fileData1,
            Map<String, Object> fileData2
    ) {
        List<Map<ChangedKey, Object>> diffList = new ArrayList<>();

        TreeSet<String> allKeys = new TreeSet<>();
        allKeys.addAll(fileData1.keySet());
        allKeys.addAll(fileData2.keySet());

        for (String key : allKeys) {
            Object value1 = fileData1.get(key);
            Object value2 = fileData2.get(key);

            Map<ChangedKey, Object> map = new TreeMap<>();
            map.put(ChangedKey.KEY, key);

            if (fileData1.containsKey(key) && fileData2.containsKey(key)) {
                if (Objects.equals(value1, value2)) {
                    map.put(ChangedKey.VALUE, value1);
                    map.put(ChangedKey.STATUS, ChangedStatus.UNCHANGED);
                } else {
                    map.put(ChangedKey.VALUE_OLD, value1);
                    map.put(ChangedKey.VALUE_NEW, value2);
                    map.put(ChangedKey.STATUS, ChangedStatus.CHANGED);
                }
            } else if (fileData1.containsKey(key) && !fileData2.containsKey(key)) {
                map.put(ChangedKey.VALUE, value1);
                map.put(ChangedKey.STATUS, ChangedStatus.REMOVED);
            } else if (!fileData1.containsKey(key) && fileData2.containsKey(key)) {
                map.put(ChangedKey.VALUE, value2);
                map.put(ChangedKey.STATUS, ChangedStatus.ADDED);
            }

            diffList.add(map);
        }

        return diffList;
    }
}
