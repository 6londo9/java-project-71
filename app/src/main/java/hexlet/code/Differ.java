package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Utils.Performer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        Path firstPath = Path.of(filepath1).toAbsolutePath().normalize();
        String firstFile = Files.readString(firstPath);
        Path secondPath = Path.of(filepath2).toAbsolutePath().normalize();
        String secondFile = Files.readString(secondPath);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> firstFileAsMap = mapper.readValue(firstFile, new TypeReference<>() {
        });
        Map<String, Object> secondFileAsMap = mapper.readValue(secondFile, new TypeReference<>() {
        });
        Map<String, Object> resultMap = new TreeMap<>((s, t) -> {
            boolean sWithPlus = s.startsWith("+");
            boolean sWithMinus = s.startsWith("-");
            boolean sCases = sWithPlus || sWithMinus;

            boolean tWithPlus = t.startsWith("+");
            boolean tWithMinus = t.startsWith("-");
            boolean tCases = tWithPlus || tWithMinus;

            String ss = sCases ? s.substring(2) : s;
            String ts = tCases ? t.substring(2) : t;

            if (s.equals(t)) {
                return 0;
            }

            if ((sCases && tCases) && ss.equals(ts)) {
                if (sWithPlus) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return ss.compareTo(ts);
        });

        for (Map.Entry<String, Object> firstFileEntry : firstFileAsMap.entrySet()) {
            String keyForValueToCompare = firstFileEntry.getKey();
            Object firstFileValue = firstFileEntry.getValue();

            if (secondFileAsMap.containsKey(firstFileEntry.getKey())) {

                Object secondFileValue = secondFileAsMap.get(keyForValueToCompare);
                if (secondFileValue.equals(firstFileValue)) {

                    resultMap.put(keyForValueToCompare, firstFileValue);
                } else {

                    String keyForOldValue = "- " + keyForValueToCompare;
                    resultMap.put(keyForOldValue, firstFileValue);
                    String keyForNewValue = "+ " + keyForValueToCompare;
                    resultMap.put(keyForNewValue, secondFileValue);
                }
            } else {
                String removedKey = "- " + keyForValueToCompare;
                resultMap.put(removedKey, firstFileValue);
            }
        }

        for (Map.Entry<String, Object> secondFileEntry : secondFileAsMap.entrySet()) {
            if (!firstFileAsMap.containsKey(secondFileEntry.getKey())) {
                String newKey = "+ " + secondFileEntry.getKey();
                Object newValue = secondFileEntry.getValue();
                resultMap.put(newKey, newValue);
            }
        }

    return Performer.perform(mapper.writeValueAsString(resultMap));
    }
}
