package hexlet.code.Formatter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Json {

    public static String render(Map<String, Object> diff) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(diff);
    }
}
