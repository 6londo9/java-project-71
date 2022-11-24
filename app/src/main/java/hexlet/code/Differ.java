package hexlet.code;

import hexlet.code.Formatter.Formatter;

import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> firstFileAsMap = Parser.parse(filepath1);
        Map<String, Object> secondFileAsMap = Parser.parse(filepath2);
        // Оставил для возможной дальнейшей замены
//        List<Object> diff = DiffGenerator.diffToRender(firstFileAsMap, secondFileAsMap);
        Map<String, Object> diff = DiffGenerator.diffToRender(firstFileAsMap, secondFileAsMap);

        return Formatter.format(diff, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return Differ.generate(filepath1, filepath2, "stylish");
    }
}
