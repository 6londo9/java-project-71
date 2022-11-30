package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    public static Map parse(String content, String dataFormat) throws Exception {
        switch (dataFormat) {
            case "json" -> {
                return parseJson(content);
            }
            case "yml" -> {
                return parseYaml(content);
            }
            default -> throw new Exception("Unknown data format: " + dataFormat + "!");
        }
    }

    private static Map parseJson(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }

    private static Map parseYaml(String content) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(content, Map.class);
    }
}
