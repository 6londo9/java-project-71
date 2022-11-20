package hexlet.code;

import hexlet.code.Utils.Parser;
import hexlet.code.Utils.Formatter;

import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> firstFileAsMap = Parser.parse(filepath1);
        Map<String, Object> secondFileAsMap = Parser.parse(filepath2);
        Map<String, String> generatedDifference = Parser.genDiff(firstFileAsMap, secondFileAsMap);

        return Formatter.perform(generatedDifference, firstFileAsMap, secondFileAsMap, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return Differ.generate(filepath1, filepath2, "stylish");
    }
}
