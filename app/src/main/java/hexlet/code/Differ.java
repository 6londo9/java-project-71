package hexlet.code;

import hexlet.code.Utils.Parser;
import hexlet.code.Utils.Performer;

import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        // добавить поддержку формата в метод. Перенести сюда getMapper по приходящему формату
        Map<String, Object> firstFileAsMap = Parser.parse(filepath1);
        Map<String, Object> secondFileAsMap = Parser.parse(filepath2);
        Map<String, String> generatedDifference = Parser.genDiff(firstFileAsMap, secondFileAsMap);

        return Performer.perform(generatedDifference, firstFileAsMap, secondFileAsMap);
    }
}
