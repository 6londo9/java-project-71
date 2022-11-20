package hexlet.code.Utils;

import java.util.Map;

public class Formatter {

    private static String performedOutput;
    private static final String ADDED = "added";
    private static final String REMOVED = "removed";
    private static final String CHANGED = "changed";
    private static final String UNCHANGED = "unchanged";

    public static String perform(Map<String, String> mapToPerform, Map<String, Object> data1,
                                 Map<String, Object> data2, String format) {
        switch (format) {
            case "stylish" -> performedOutput = stylishOutput(mapToPerform, data1, data2);
        }

        return performedOutput;
    }

    public static String stylishOutput(Map<String, String> mapToPerform, Map<String, Object> data1,
                                       Map<String, Object> data2) {
        StringBuilder performedSb = new StringBuilder("{\n");
        for (Map.Entry<String, String> entry : mapToPerform.entrySet()) {

            switch (entry.getValue()) {
                case ADDED -> performedSb.append("  + ").append(entry.getKey())
                        .append(": ").append(data2.get(entry.getKey())).append("\n");
                case REMOVED -> performedSb.append("  - ").append(entry.getKey())
                        .append(": ").append(data1.get(entry.getKey())).append("\n");
                case CHANGED -> {
                    performedSb.append("  - ").append(entry.getKey()).append(": ")
                            .append(data1.get(entry.getKey())).append("\n");
                    performedSb.append("  + ").append(entry.getKey()).append(": ")
                            .append(data2.get(entry.getKey())).append("\n");
                }
                case UNCHANGED -> performedSb.append("    ").append(entry.getKey())
                        .append(": ").append(data1.get(entry.getKey())).append("\n");
                default -> throw new Error("Unknown value:" + entry.getValue() + "! Check your code.");
            }
        }
        performedSb.append("}");
        return performedSb.toString();
    }
}
