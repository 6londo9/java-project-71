package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AppTest {

    private String firstFilePath;
    private String secondFilePath;

    @Test
    public void testJsonDiffer() throws Exception {
        firstFilePath = "./src/test/resources/file1.json";
        secondFilePath = "./src/test/resources/file2.json";
        String actual = Differ.generate(firstFilePath, secondFilePath);
        String expected = """
                {
                 + breaktest: 20
                 - faketest: false
                 + faketest: 20
                   make: 20
                 - test: true
                 + test: 10
                }""";
        assertEquals(expected, actual);
    }

    public void testYmlDiffer() throws Exception {
        firstFilePath = "./src/test/resources/file1.yml";
        secondFilePath = "./src/test/resources/file2.yml";
        String actual = Differ.generate(firstFilePath, secondFilePath);
        String expected;
        assertEquals(expected, actual);
    }
}
