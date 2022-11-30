package hexlet.code.Formatter;

import hexlet.code.Status;

import java.util.Map;

public class Formatter {

    public static String format(Map<String, Status> diff, String format) throws Exception {
        switch (format) {
            case "stylish" -> {
                return Stylish.render(diff);
            }
            case "plain" -> {
                return Plain.render(diff);
            }
            case "json" -> {
                return Json.render(diff);
            }
            default -> throw new Exception("Unknown format: " + format + "!");
        }
    }
}
