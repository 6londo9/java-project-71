package hexlet.code.Utils;

public class Performer {
    public static String perform(String stringToPerform) {
        String performedString = "{\n";
        String[] performedStringAsMap = stringToPerform.split(",");

        for (String str : performedStringAsMap) {
            String firstPartOfString = str.startsWith("{") ?
                    str.substring(1, str.indexOf(":")) : str.substring(0, str.indexOf(":"));
            String lastPartOfString = str.endsWith("}") ?
                    str.substring(str.indexOf(":"), str.length() - 1) : str.substring(str.indexOf(":"));
            String resultedString = firstPartOfString.replaceAll("\"", "")
                    + " " + lastPartOfString;

            if (resultedString.startsWith("+") || resultedString.startsWith("-")) {
                performedString = performedString + " " + resultedString + "\n";
            } else {
                performedString = performedString + "   " + resultedString + "\n";
            }
        }
        performedString = performedString + "}";
        return performedString;
    }
}
