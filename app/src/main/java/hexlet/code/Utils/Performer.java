package hexlet.code.Utils;

import java.util.Map;

public class Performer {
    private static final String ADDED = "added";
    private static final String REMOVED = "removed";
    private static final String CHANGED = "changed";
    private static final String UNCHANGED = "unchanged";

    public static String perform(Map<String, String> mapToPerform, Map<String, Object> data1,
                                 Map<String, Object> data2) {
        StringBuilder performedSb = new StringBuilder("{\n");
        for (Map.Entry<String, String> entry : mapToPerform.entrySet()) {

            switch (entry.getValue()) {
                case ADDED -> performedSb.append("  + " + entry.getKey() + ": "
                        + data2.get(entry.getKey()) + "\n");
                case REMOVED -> performedSb.append("  - " + entry.getKey() + ": "
                        + data1.get(entry.getKey()) + "\n");
                case CHANGED -> {
                    performedSb.append("  - " + entry.getKey() + ": "
                            + data1.get(entry.getKey()) + "\n");
                    performedSb.append("  + " + entry.getKey() + ": "
                            + data2.get(entry.getKey()) + "\n");
                }
                case UNCHANGED -> performedSb.append("    " + entry.getKey() + ": "
                        + data1.get(entry.getKey()) + "\n");
                default -> throw new Error("Unknown value:" + entry.getValue() + "! Check your code.");
            }
        }
        performedSb.append("}");

        return performedSb.toString();
    }
}
