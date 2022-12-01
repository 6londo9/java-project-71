package hexlet.code;

import hexlet.code.Formatter.Formatter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.skyscreamer.jsonassert.JSONAssert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;


public final class AppTest {

    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;

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

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTests(String format) throws Exception {
        String filepath1 = getFixturePath("file1." + format).toString();
        String filepath2 = getFixturePath("file2." + format).toString();

        assertThat(Differ.generate(filepath1, filepath2))
                .isEqualTo(resultStylish);

        assertThat(Differ.generate(filepath1, filepath2, "stylish"))
                .isEqualTo(resultStylish);

        assertThat(Differ.generate(filepath1, filepath2, "plain"))
                .isEqualTo(resultPlain);

        String actualJson = Differ.generate(filepath1, filepath2, "json");
        JSONAssert.assertEquals(resultJson, actualJson, false);
    }

//    @Test
//    public void testStylishDifferFromJson() throws Exception {
//        firstFilePath = getFixturePath("file1.json").toString();
//        secondFilePath = getFixturePath("file2.json").toString();
//        String actual = Differ.generate(firstFilePath, secondFilePath);
//        String expected = resultStylish;
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testStylishDifferFromYML() throws Exception {
//        firstFilePath = getFixturePath("file1.yml").toString();
//        secondFilePath = getFixturePath("file1.yml").toString();
//        String actual = Differ.generate(firstFilePath, secondFilePath);
//        String expected = resultStylish;
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testPlainDifferFromJson() throws Exception {
//        firstFilePath = getFixturePath("file1.json").toString();
//        secondFilePath = getFixturePath("file2.json").toString();
//        String actual = Differ.generate(firstFilePath, secondFilePath, "plain");
//        String expected = resultPlain;
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testPlainDifferFromYML() throws Exception {
//        firstFilePath = getFixturePath("file1.yml").toString();
//        secondFilePath = getFixturePath("file2.yml").toString();
//        String actual = Differ.generate(firstFilePath, secondFilePath, "plain");
//        String expected = resultPlain;
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testJsonDifferFromJson() throws Exception {
//        firstFilePath = getFixturePath("file1.json").toString();
//        secondFilePath = getFixturePath("file1.json").toString();
//        String actual = Differ.generate(firstFilePath, secondFilePath, "json");
//        String expected = resultJson;
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testJsonDifferFromYml() throws Exception {
//        firstFilePath = getFixturePath("file1.yml").toString();
//        secondFilePath = getFixturePath("file2.yml").toString();
//        String actual = Differ.generate(firstFilePath, secondFilePath, "json");
//        String expected = resultJson;
//        assertEquals(expected, actual);
//    }

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
