package hexlet.code.Formatter;


import java.util.List;
import java.util.Map;

import static hexlet.code.DiffGenerator.ADDED;
import static hexlet.code.DiffGenerator.REMOVED;


public class Plain {

    private static final int UPDATED = 2;
    private static final int ADDED_OR_REMOVED = 1;

    public static String render(Map<String, Object> diff) {

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Object> entry : diff.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map) {
                Map<String, Object> deepValue = (Map<String, Object>) value;

                switch (deepValue.size()) {
                    case UPDATED -> {
                        String oldValue = plainValueFormatter(deepValue.get(REMOVED));
                        String newValue = plainValueFormatter(deepValue.get(ADDED));
                        sb.append("\nProperty '").append(key).append("' was updated. From ")
                                .append(oldValue)
                                .append(" to ").append(newValue);
                    }

                    case ADDED_OR_REMOVED -> {
                        if (deepValue.containsKey(ADDED)) {
                            String addedValue = plainValueFormatter(deepValue.get(ADDED));
                            sb.append("\nProperty '").append(key).append("' was added with value: ")
                                    .append(addedValue);

                        } else {
                            sb.append("\nProperty '").append(key).append("' was removed");
                        }

                    }

                    default -> throw new IllegalStateException("Unexpected size: " + deepValue.size());
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
