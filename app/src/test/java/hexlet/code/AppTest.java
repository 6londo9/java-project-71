package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppTest {

    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;
    private static String firstFilePath;
    private static String secondFilePath;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultStylish = readFixture("stylish_output.txt");
        resultPlain = readFixture("plain_output.txt");
        resultJson = readFixture("json_output.txt");
    }
    @Test
    public void testStylishDiffer() throws Exception {
        firstFilePath = "./src/test/resources/fixtures/file1.json";
        secondFilePath = "./src/test/resources/fixtures/file2.json";
        String actual = Differ.generate(firstFilePath, secondFilePath);
        String expected = resultStylish;
        System.out.println(expected);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testPlainDiffer() throws Exception {
        firstFilePath = "./src/test/resources/fixtures/file1.json";
        secondFilePath = "./src/test/resources/fixtures/file2.json";
        String actual = Differ.generate(firstFilePath, secondFilePath, "plain");
        String expected = resultPlain;
        assertEquals(expected, actual);
    }

    @Test
    public void testJsonDiffer() throws Exception {
        firstFilePath = "fixtures/file1.json";
        secondFilePath = "fixtures/file2.json";
        String actual = Differ.generate(firstFilePath, secondFilePath, "json");
        String expected = resultJson;
        assertEquals(expected, actual);
    }
}
