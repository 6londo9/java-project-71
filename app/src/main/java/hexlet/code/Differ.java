package hexlet.code;

import hexlet.code.Formatter.Formatter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {
    private static String dataFormat;

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        dataFormat = getDataFormat(filepath1);
        Path firstFilePath = Path.of(filepath1).toAbsolutePath().normalize();
        Path secondFilePath = Path.of(filepath2).toAbsolutePath().normalize();
        String firstFileAsString = Files.readString(firstFilePath);
        String secondFileAsString = Files.readString(secondFilePath);

        Map<String, Object> firstFileAsMap = Parser.parse(firstFileAsString, dataFormat);
        Map<String, Object> secondFileAsMap = Parser.parse(secondFileAsString, dataFormat);
        Map<String, Status> diff = DiffGenerator.genDiff(firstFileAsMap, secondFileAsMap);

        return Formatter.format(diff, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return Differ.generate(filepath1, filepath2, "stylish");
    }

    private static String getDataFormat(String content) {
        return content.substring(content.lastIndexOf(".") + 1);
    }
}
