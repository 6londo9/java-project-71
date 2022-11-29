package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

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
}
