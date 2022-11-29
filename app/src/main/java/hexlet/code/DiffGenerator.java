package hexlet.code;


import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DiffGenerator {

    public static final String ADDED = "added";
    public static final String REMOVED = "removed";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private static Map<String, String> genDiff(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Map<String, String> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(firstMap.keySet());
        keys.addAll(secondMap.keySet());

        for (String key : keys) {

            if (!firstMap.containsKey(key)) {
                result.put(key, ADDED);
            } else if (!secondMap.containsKey(key)) {
                result.put(key, REMOVED);
            } else if (!String.valueOf(firstMap.get(key)).equals(String.valueOf(secondMap.get(key)))) {
                result.put(key, CHANGED);
            } else {
                result.put(key, UNCHANGED);
            }
        }

        return result;
    }

    public static Map<String, Object> diffToRender(Map<String, Object> firstMap, Map<String, Object> secondMap)
            throws Exception {

        Map<String, Object> resultedDiff = new LinkedHashMap<>();
        Map<String, String> genDiff = genDiff(firstMap, secondMap);

        for (Map.Entry<String, String> entry : genDiff.entrySet()) {
            String key = entry.getKey();
            Object value;
            switch (entry.getValue()) {
                case ADDED -> {
                    value = secondMap.get(key);
                    resultedDiff.put(key, new HashMap<String, Object>() {{
                            put(ADDED, value); }}
                    );
                }
                case REMOVED -> {
                    value = firstMap.get(key);
                    resultedDiff.put(key, new HashMap<String, Object>() {{
                            put(REMOVED, value); }}
                    );
                }
                case CHANGED -> {
                    value = firstMap.get(key);
                    Object newValue = secondMap.get(key);
                    resultedDiff.put(key, new HashMap<String, Object>() {{
                            put(REMOVED, value);
                            put(ADDED, newValue); }}
                    );
                }
                case UNCHANGED -> {
                    value = secondMap.get(key);
                    resultedDiff.put(key, value);
                }
                default -> throw new Exception("Unknown value: " + entry.getValue() + "!");
            }
        }
        return resultedDiff;
    }
}
