package hexlet.code.Utils;

import java.util.Map;

public class Formatter {

    private static StringBuilder sb;
    private static final String ADDED = "added";
    private static final String REMOVED = "removed";
    private static final String CHANGED = "changed";
    private static final String UNCHANGED = "unchanged";

    public static String perform(Map<String, String> mapToPerform, Map<String, Object> data1,
                                 Map<String, Object> data2, String format) {

        switch (format) {
            case "stylish" -> {
                return stylishOutput(mapToPerform, data1, data2);
            }
            case "plain" -> {
                return plainOutput(mapToPerform, data1, data2);
            }
            default -> {
                break;
            }
        }

        return "Unsupported format: " + format + "!";
    }

    public static String stylishOutput(Map<String, String> mapToPerform, Map<String, Object> data1,
                                       Map<String, Object> data2) {
        sb = new StringBuilder("{\n");
        for (Map.Entry<String, String> entry : mapToPerform.entrySet()) {

            switch (entry.getValue()) {
                case ADDED -> sb.append("  + ").append(entry.getKey())
                        .append(": ").append(data2.get(entry.getKey())).append("\n");
                case REMOVED -> sb.append("  - ").append(entry.getKey())
                        .append(": ").append(data1.get(entry.getKey())).append("\n");
                case CHANGED -> {
                    sb.append("  - ").append(entry.getKey()).append(": ")
                            .append(data1.get(entry.getKey())).append("\n");
                    sb.append("  + ").append(entry.getKey()).append(": ")
                            .append(data2.get(entry.getKey())).append("\n");
                }
                case UNCHANGED -> sb.append("    ").append(entry.getKey())
                        .append(": ").append(data1.get(entry.getKey())).append("\n");
                default -> throw new Error("Unknown value:" + entry.getValue() + "! Check your code.");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static String plainOutput(Map<String, String> mapToPerform, Map<String, Object> data1,
                                     Map<String, Object> data2) {
        sb = new StringBuilder("\n");
        String key;
        Object value;
        for (Map.Entry<String, String> entry : mapToPerform.entrySet()) {
            key = entry.getKey();

            switch (entry.getValue()) {
                case ADDED:
                    value = plainValueFormatter(data2.get(key));
                    sb.append("Property '").append(key).append("' was added with value: ")
                            .append(value).append("\n");
                    break;

                case REMOVED:
                    sb.append("Property '").append(key).append("' was removed\n");
                    break;

                case CHANGED:
                    Object pastValue = plainValueFormatter(data1.get(key));
                    Object presentValue = plainValueFormatter(data2.get(key));
                    sb.append("Property '").append(key).append("' was updated. From ").append(pastValue)
                            .append(" to ").append(presentValue).append("\n");
                    break;

                default:
                    break;
            }
        }

        return sb.toString();
    }

    private static Object plainValueFormatter(Object obj) {
        String objAsString = obj.toString();
        if (objAsString.startsWith("{") && objAsString.endsWith("}")
                || objAsString.startsWith("[") && objAsString.endsWith("]")) {
            return "[complex value]";
        }
        if (obj instanceof String) {
            obj = "'" + obj + "'";
        }
        return obj;
    }
}
