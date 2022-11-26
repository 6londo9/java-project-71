package hexlet.code.Formatter;

import java.util.List;
import java.util.Map;

public class Plain {

    private static final int CHANGED = 2;
    private static final int UNCHANGED = 1;

    public static String render(Map<String, Object> diff) {

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Object> entry : diff.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map) {
                Map<String, Object> deepValue = (Map<String, Object>) value;

                switch (deepValue.size()) {
                    case CHANGED -> {
                        String oldValue = plainValueFormatter(deepValue.get("removed"));
                        String newValue = plainValueFormatter(deepValue.get("added"));
                        sb.append("\nProperty '").append(key).append("' was updated. From ")
                                .append(oldValue)
                                .append(" to ").append(newValue);
                    }

                    case UNCHANGED -> {
                        if (deepValue.containsKey("added")) {
                            String addedValue = plainValueFormatter(deepValue.get("added"));
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

    //Оставил на случай дальнейшего использования
//    public static String render(List<Object> diff) {
//
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < diff.size(); i++) {
//            String keyAndValue = String.valueOf(diff.get(i));
//            String key = keyAndValue.substring(0, keyAndValue.indexOf("="));
//            Object value = keyAndValue.substring(keyAndValue.indexOf("=") + 1, keyAndValue.lastIndexOf(","));
//
//            if (keyAndValue.endsWith("added")) {
//                sb.append("\nProperty '").append(key).append("' was added with value: ")
//                        .append(value);
//
//            } else if (keyAndValue.endsWith("removed")) {
//                sb.append("\nProperty '").append(key).append("' was removed");
//
//            } else if (keyAndValue.endsWith("changed")) {
//                String next = diff.get(i + 1).toString();
//                String nextValue = next.substring(next.indexOf("=") + 1, next.lastIndexOf(","));
//                String oldValue = plainValueFormatter(value);
//                String newValue = plainValueFormatter(nextValue);
//                sb.append("\nProperty '").append(key).append("' was updated. From ").append(oldValue)
//                        .append(" to ").append(newValue);
//                i++;
//            }
//        }
//
//        return sb.toString().replaceFirst("\n", "");
//    }
}
