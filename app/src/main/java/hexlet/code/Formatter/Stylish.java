package hexlet.code.Formatter;

import java.util.Map;

import static hexlet.code.DiffGenerator.REMOVED;
import static hexlet.code.DiffGenerator.ADDED;

public class Stylish {

    private static final int TABULATION = 4;
    private static final int CHANGED = 2;
    private static final int UNCHANGED = 1;



    public static String render(Map<String, Object> diff) {

        StringBuilder sb = new StringBuilder("{\n");

        for (Map.Entry<String, Object> entry : diff.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map) {
                Map<String, Object> deepValue = (Map<String, Object>) value;

                switch (deepValue.size()) {
                    case CHANGED -> {
                        Object oldValue = deepValue.get(REMOVED);
                        Object newValue = deepValue.get(ADDED);
                        sb.append(" ".repeat(2)).append("- ").append(key).append(": ")
                                .append(oldValue).append("\n")
                                .append(" ".repeat(2)).append("+ ").append(key).append(": ")
                                .append(newValue).append("\n");
                    }

                    case UNCHANGED -> {
                        if (deepValue.containsKey(ADDED)) {
                            Object addedValue = deepValue.get(ADDED);
                            sb.append(" ".repeat(2)).append("+ ").append(key).append(": ")
                                    .append(addedValue)
                                    .append("\n");

                        } else {
                            Object removedValue = deepValue.get(REMOVED);
                            sb.append(" ".repeat(2)).append("- ").append(key).append(": ")
                                    .append(removedValue)
                                    .append("\n");
                        }
                    }

                    default -> throw new IllegalStateException("Unexpected size: " + deepValue.size());
                }
            } else {
                sb.append(" ".repeat(TABULATION)).append(key).append(": ").append(value).append("\n");
            }
        }

        sb.append("}");
        return sb.toString();
    }
}
