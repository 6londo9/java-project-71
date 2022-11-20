package hexlet.code.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Parser {

    public static Map<String, Object> parse(String filepath) throws Exception {
        Path path = Path.of(filepath).toAbsolutePath().normalize();
        String file = Files.readString(path);

        String inputFormat = filepath.substring(filepath.lastIndexOf("."));
        ObjectMapper mapper = getMapper(inputFormat);
        return mapper.readValue(file, new TypeReference<>() {
        });
    }

    private static ObjectMapper getMapper(String mapperFormat) {
        if (mapperFormat.equals(".json")) {
            return new ObjectMapper();
        } else if (mapperFormat.equals(".yml")) {
            return new YAMLMapper();
        } else {
            return new ObjectMapper();
        }
    }

    public static Map<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, String> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key : keys) {

            if (!data1.containsKey(key)) {
                result.put(key, "added");
            } else if (!data2.containsKey(key)) {
                result.put(key, "removed");
            } else if (!(data1.get(key).toString()).equals(data2.get(key).toString())) {
                result.put(key, "changed");
            } else {
                result.put(key, "unchanged");
            }
        }

        return result;
    }
}
