package hexlet.code;

import hexlet.code.Formatter.Formatter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AppTest {

    private static String resultStylishFromJson;
    private static String resultStylishFromYml;
    private static String resultPlainFromJson;
    private static String resultPlainFromYml;
    private static String resultJsonFromJson;
    private static String resultJsonFromYml;
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
        resultStylishFromJson = readFixture("stylish_output_json.txt");
        resultPlainFromJson = readFixture("plain_output_json.txt");
        resultStylishFromYml = readFixture("stylish_output_yml.txt");
        resultPlainFromYml = readFixture("plain_output_yml.txt");
        resultJsonFromJson = readFixture("json_output_json.txt");
        resultJsonFromYml = readFixture("json_output_yml.txt");
    }
    @Test
    public void testStylishDifferFromJson() throws Exception {
        firstFilePath = "./src/test/resources/fixtures/file1.json";
        secondFilePath = "./src/test/resources/fixtures/file2.json";
        String actual = Differ.generate(firstFilePath, secondFilePath);
        String expected = resultStylishFromJson;
        assertEquals(expected, actual);
    }

    @Test
    public void testStylishDifferFromYML() throws Exception {
        firstFilePath = "./src/test/resources/fixtures/file1.yml";
        secondFilePath = "./src/test/resources/fixtures/file2.yml";
        String actual = Differ.generate(firstFilePath, secondFilePath);
        String expected = resultStylishFromYml;
        assertEquals(expected, actual);
    }

    @Test
    public void testPlainDifferFromJson() throws Exception {
        firstFilePath = "./src/test/resources/fixtures/file1.json";
        secondFilePath = "./src/test/resources/fixtures/file2.json";
        String actual = Differ.generate(firstFilePath, secondFilePath, "plain");
        String expected = resultPlainFromJson;
        assertEquals(expected, actual);
    }

    @Test
    public void testPlainDifferFromYML() throws Exception {
        firstFilePath = "./src/test/resources/fixtures/file1.yml";
        secondFilePath = "./src/test/resources/fixtures/file2.yml";
        String actual = Differ.generate(firstFilePath, secondFilePath, "plain");
        String expected = resultPlainFromYml;
        assertEquals(expected, actual);
    }

    @Test
    public void testJsonDifferFromJson() throws Exception {
        firstFilePath = "./src/test/resources/fixtures/file1.json";
        secondFilePath = "./src/test/resources/fixtures/file2.json";
        String actual = Differ.generate(firstFilePath, secondFilePath, "json");
        String expected = resultJsonFromJson;
        assertEquals(expected, actual);
    }

    @Test
    public void testJsonDifferFromYml() throws Exception {
        firstFilePath = "./src/test/resources/fixtures/file1.yml";
        secondFilePath = "./src/test/resources/fixtures/file2.yml";
        String actual = Differ.generate(firstFilePath, secondFilePath, "json");
        String expected = resultJsonFromYml;
        assertEquals(expected, actual);
    }

    @Test
    public void testExceptionWasThrown() {
        Map<String, Status> testMap1 = new HashMap<>();
        testMap1.put("key", new Status(Status.ADDED, 1));
        Exception exception = assertThrows(Exception.class, () -> Formatter.format(testMap1, "bingo"));

        String expectedMessage = "Unknown format: bingo!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
