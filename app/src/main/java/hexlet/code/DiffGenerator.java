package hexlet.code;


import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DiffGenerator {

    private static final String ADDED = "added";
    private static final String REMOVED = "removed";
    private static final String CHANGED = "changed";
    private static final String UNCHANGED = "unchanged";

    private static Map<String, String> genDiff(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Map<String, String> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(firstMap.keySet());
        keys.addAll(secondMap.keySet());

        for (String key : keys) {

            if (!firstMap.containsKey(key)) {
                result.put(key, "added");
            } else if (!secondMap.containsKey(key)) {
                result.put(key, "removed");
            } else if (!String.valueOf(firstMap.get(key)).equals(String.valueOf(secondMap.get(key)))) {
                result.put(key, "changed");
            } else {
                result.put(key, "unchanged");
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

    // Оставил для возможной дальнейшей замены
//    public static List<Object> diffToRend(Map<String, Object> firstMap, Map<String, Object> secondMap)
//            throws Exception {
//        List<Object> diff = new ArrayList<>();
//        Map<String, String> diffMap = genDiff(firstMap, secondMap);
//
//        for (Map.Entry<String, String> entry : diffMap.entrySet()) {
//            String key = entry.getKey();
//
//            Object value;
//            switch (entry.getValue()) {
//                case ADDED -> {
//                    value = secondMap.get(key);
//                    diff.add(key + "=" + value + ",added");
//                }
//                case REMOVED -> {
//                    value = firstMap.get(key);
//                    diff.add(key + "=" + value + ",removed");
//                }
//                case CHANGED -> {
//                    value = firstMap.get(key);
//                    Object newValue = secondMap.get(key);
//                    diff.add(key + "=" + value + ",changed");
//                    diff.add(key + "=" + newValue + ",changed");
//                }
//                case UNCHANGED -> {
//                    value = secondMap.get(key);
//                    diff.add(key + "=" + value + ",unchanged");
//                }
//                default -> throw new Exception("Unknown value: " + entry.getValue() + "!");
//            }
//        }
//        return diff;
//    }
}
