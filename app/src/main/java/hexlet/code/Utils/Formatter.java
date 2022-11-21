package hexlet.code.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Formatter {

    private static StringBuilder sb;
    private static final String ADDED = "added";
    private static final String REMOVED = "removed";
    private static final String CHANGED = "changed";
    private static final String UNCHANGED = "unchanged";

    private static String key;
    private static String value;
    private static final int TABULATION = 4;

    public static String perform(Map<String, String> mapToPerform, Map<String, Object> data1,
                                 Map<String, Object> data2, String format) throws Exception {

        switch (format) {
            case "stylish" -> {
                return stylishOutput(mapToPerform, data1, data2);
            }
            case "plain" -> {
                return plainOutput(mapToPerform, data1, data2);
            }
            case "json" -> {
                return jsonOutput(mapToPerform, data1, data2);
            }
            default -> {
            }
        }

        return "Unsupported format: " + format + "!";
    }

    public static String stylishOutput(Map<String, String> performedMap, Map<String, Object> data1,
                                       Map<String, Object> data2) {
        sb = new StringBuilder("{\n");
        for (Map.Entry<String, String> entry : performedMap.entrySet()) {
            key = entry.getKey();

            switch (entry.getValue()) {
                case ADDED -> {
                    value = String.valueOf(data2.get(key));
                    sb.append("  + ").append(key).append(": ").append(value).append("\n");
                }
                case REMOVED -> {
                    value = String.valueOf(data1.get(key));
                    sb.append("  - ").append(key).append(": ").append(value).append("\n");
                }
                case CHANGED -> {
                    value = String.valueOf(data1.get(key));
                    Object newValue = String.valueOf(data2.get(entry.getKey()));

                    sb.append(" ".repeat(2)).append("- ").append(key).append(": ").append(value).append("\n");
                    sb.append(" ".repeat(2)).append("+ ").append(key).append(": ").append(newValue).append("\n");
                }
                case UNCHANGED -> {
                    value = String.valueOf(data1.get(key));
                    sb.append(" ".repeat(TABULATION)).append(key).append(": ").append(value).append("\n");
                }
                default -> throw new Error("Unknown value:" + entry.getValue() + "! Check your code.");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static String plainOutput(Map<String, String> performedMap, Map<String, Object> data1,
                                     Map<String, Object> data2) {
        sb = new StringBuilder("\n");
        for (Map.Entry<String, String> entry : performedMap.entrySet()) {
            key = entry.getKey();

            switch (entry.getValue()) {
                case ADDED -> {
                    value = plainValueFormatter(data2.get(key));
                    sb.append("Property '").append(key).append("' was added with value: ")
                            .append(value).append("\n");
                }
                case REMOVED -> sb.append("Property '").append(key).append("' was removed\n");
                case CHANGED -> {
                    Object pastValue = plainValueFormatter(data1.get(key));
                    Object presentValue = plainValueFormatter(data2.get(key));
                    sb.append("Property '").append(key).append("' was updated. From ").append(pastValue)
                            .append(" to ").append(presentValue).append("\n");
                }
                default -> {
                }
            }
        }

        return sb.toString();
    }

    private static String plainValueFormatter(Object obj) {
        String objAsString = String.valueOf(obj);
        if (objAsString.startsWith("{") && objAsString.endsWith("}")
                || objAsString.startsWith("[") && objAsString.endsWith("]")) {
            return "[complex value]";
        }
        if (obj instanceof String) {
            objAsString = "'" + objAsString + "'";
        }
        return objAsString;
    }

    public static String jsonOutput(Map<String, String> performedMap, Map<String, Object> data1,
                                     Map<String, Object> data2) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        sb = new StringBuilder("{");
        for (Map.Entry<String, String> entry : performedMap.entrySet()) {

            key = entry.getKey();
            sb.append("\"").append(key).append("\"");
            switch (entry.getValue()) {
                case ADDED -> {
                    value = jsonValueFormatter(data2.get(key));
                    sb.append(": {\"added\": ").append(value).append("}, ");
                }
                case REMOVED -> {
                    value = jsonValueFormatter(data1.get(key));
                    sb.append(": {\"removed\": ").append(value).append("}, ");
                }
                case CHANGED -> {
                    Object pastValue = jsonValueFormatter(data1.get(key));
                    Object presentValue = jsonValueFormatter(data2.get(key));
                    sb.append(": {\"was\": ").append(pastValue).append(", ")
                            .append("\"became\": ").append(presentValue).append("}, ");
                }
                case UNCHANGED -> {
                    value = jsonValueFormatter(data1.get(key));
                    sb.append(": ").append(value).append(", ");
                }
                default -> {
                }
            }
        }
        String jsonAsString = sb.substring(0, sb.length() - 2) + "}";
        Object json = mapper.readValue(jsonAsString, Object.class);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }

    private static String jsonValueFormatter(Object obj) {
        String objAsString = String.valueOf(obj);
        if (obj instanceof String) {
            return "\"" + objAsString + "\"";
        }
        return objAsString;
    }
}
