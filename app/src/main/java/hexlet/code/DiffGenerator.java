package hexlet.code;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static hexlet.code.Status.ADDED;
import static hexlet.code.Status.REMOVED;
import static hexlet.code.Status.CHANGED;
import static hexlet.code.Status.UNCHANGED;

public class DiffGenerator {

    public static Map<String, Status> genDiff(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Map<String, Status> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(firstMap.keySet());
        keys.addAll(secondMap.keySet());

        for (String key : keys) {

            if (!firstMap.containsKey(key)) {
                result.put(key, new Status(ADDED, secondMap.get(key)));
            } else if (!secondMap.containsKey(key)) {
                result.put(key, new Status(REMOVED, firstMap.get(key)));
            } else if (!String.valueOf(firstMap.get(key)).equals(String.valueOf(secondMap.get(key)))) {
                result.put(key, new Status(CHANGED, firstMap.get(key), secondMap.get(key)));
            } else {
                result.put(key, new Status(UNCHANGED, firstMap.get(key)));
            }
        }

        return result;
    }
}
