package hexlet.code.Formatter;


import hexlet.code.Status;

import java.util.List;
import java.util.Map;

import static hexlet.code.Status.ADDED;
import static hexlet.code.Status.REMOVED;
import static hexlet.code.Status.CHANGED;
import static hexlet.code.Status.UNCHANGED;

public class Plain {

    public static String render(Map<String, Status> diff) throws Exception {

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Status> entry : diff.entrySet()) {
            String key = entry.getKey();
            Status value = entry.getValue();
            switch (value.getStatusName()) {
                case ADDED -> {
                    String addedValue = plainValueFormatter(value.getNewValue());
                    sb.append("\nProperty '").append(key).append("' was added with value: ")
                            .append(addedValue);
                }

                case REMOVED -> sb.append("\nProperty '").append(key).append("' was removed");

                case CHANGED -> {
                    String oldValue = plainValueFormatter(value.getOldValue());
                    String newValue = plainValueFormatter(value.getNewValue());
                    sb.append("\nProperty '").append(key).append("' was updated. From ")
                            .append(oldValue)
                            .append(" to ").append(newValue);
                }

                default -> {
                    if (!value.getStatusName().equals(UNCHANGED)) {
                        throw new Exception("Unknown status name: " + value.getStatusName() + "!");
                    }
                }
            }
        }
        return sb.toString().replaceFirst("\n", "");
    }


    private static String plainValueFormatter(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }

        return value.toString();
    }
}
