package hexlet.code.Formatter;

import hexlet.code.Status;

import java.util.Map;

import static hexlet.code.Status.ADDED;
import static hexlet.code.Status.REMOVED;
import static hexlet.code.Status.CHANGED;
import static hexlet.code.Status.UNCHANGED;

public class Stylish {

    private static final int TABULATION = 4;

    public static String render(Map<String, Status> diff) throws Exception {

        StringBuilder sb = new StringBuilder("{\n");

        for (Map.Entry<String, Status> entry : diff.entrySet()) {
            String key = entry.getKey();
            Status value = entry.getValue();
            switch (value.getStatusName()) {
                case ADDED -> {
                    Object addedValue = value.getNewValue();
                    sb.append(" ".repeat(2)).append("+ ").append(key).append(": ")
                            .append(addedValue)
                            .append("\n");
                }

                case REMOVED -> {
                    Object removedValue = value.getOldValue();
                    sb.append(" ".repeat(2)).append("- ").append(key).append(": ")
                            .append(removedValue)
                            .append("\n");
                }

                case CHANGED -> {
                    Object oldValue = value.getOldValue();
                    Object newValue = value.getNewValue();
                    sb.append(" ".repeat(2)).append("- ").append(key).append(": ")
                            .append(oldValue).append("\n")
                            .append(" ".repeat(2)).append("+ ").append(key).append(": ")
                            .append(newValue).append("\n");
                }

                case UNCHANGED -> {
                    Object unchangedValue = value.getOldValue();
                    sb.append(" ".repeat(TABULATION)).append(key).append(": ").append(unchangedValue).append("\n");
                }

                default -> throw new Exception("Unknown status name: " + value.getStatusName() + "!");
            }
        }

        sb.append("}");
        return sb.toString();
    }
}
