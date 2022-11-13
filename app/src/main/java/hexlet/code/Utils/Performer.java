package hexlet.code.Utils;

public class Performer {
    public static String perform(String stringToPerform) {
        String performedString = "{\n";
        String[] performedStringAsMap = stringToPerform.split(",");

        for (String str : performedStringAsMap) {
            String firstPartOfString = str.startsWith("{")
                    ? str.substring(1, str.indexOf(":") + 1) : str.substring(0, str.indexOf(":") + 1);
            firstPartOfString = firstPartOfString.replaceAll("\"", "") + " ";
            String lastPartOfString = str.endsWith("}")
                    ? str.substring(str.indexOf(":") + 1, str.length() - 1) : str.substring(str.indexOf(":") + 1);
            String resultedString = firstPartOfString + lastPartOfString;

            if (resultedString.startsWith("+") || resultedString.startsWith("-")) {
                performedString = performedString + " ";
            } else {
                performedString = performedString + "   ";
            }
            performedString = performedString + resultedString + "\n";
        }
        performedString = performedString + "}";
        return performedString;
    }
}
