package hexlet.code.Formatter;

import java.util.Map;

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
                        Object oldValue = deepValue.get("removed");
                        Object newValue = deepValue.get("added");
                        sb.append(" ".repeat(2)).append("- ").append(key).append(": ")
                                .append(oldValue).append("\n")
                                .append(" ".repeat(2)).append("+ ").append(key).append(": ")
                                .append(newValue).append("\n");
                    }

                    case UNCHANGED -> {
                        if (deepValue.containsKey("added")) {
                            Object addedValue = deepValue.get("added");
                            sb.append(" ".repeat(2)).append("+ ").append(key).append(": ")
                                    .append(addedValue)
                                    .append("\n");

                        } else {
                            Object removedValue = deepValue.get("removed");
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

//      Оставил на случай дальнейшего использования
//    public static String render(List<Object> diff) {
//
//        StringBuilder sb = new StringBuilder("{\n");
//
//        for (int i = 0; i < diff.size(); i++) {
//            String keyAndValue = diff.get(i).toString();
//            String key = keyAndValue.substring(0, keyAndValue.indexOf("="));
//            String value = keyAndValue.substring(keyAndValue.indexOf("=") + 1, keyAndValue.lastIndexOf(","));
//
//            if (keyAndValue.endsWith("added")) {
//                sb.append(" ".repeat(2)).append("+ ").append(key).append(": ").append(value).append("\n");
//
//            } else if (keyAndValue.endsWith("removed")) {
//                sb.append(" ".repeat(2)).append("- ").append(key).append(": ").append(value).append("\n");
//
//            } else if (keyAndValue.endsWith("unchanged")) {
//                sb.append(" ".repeat(TABULATION)).append(key).append(": ").append(value).append("\n");
//
//            } else {
//                String next = diff.get(i + 1).toString();
//                String nextValue = next.substring(next.indexOf("=") + 1, next.lastIndexOf(","));
//                sb.append(" ".repeat(2)).append("- ").append(key).append(": ").append(value).append("\n");
//                sb.append(" ".repeat(2)).append("+ ").append(key).append(": ").append(nextValue).append("\n");
//                i++;
//            }
//        }
//
//        sb.append("}");
//        return sb.toString();
//    }
}
