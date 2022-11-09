package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {

    private String firstFilePath;
    private String secondFilePath;

    @BeforeEach
    public void beforeEach() {
        firstFilePath = "./src/test/resources/file1.json";
        secondFilePath = "./src/test/resources/file2.json";
    }

    @Test
    public void testDiffer() throws Exception {

        String actual = Differ.generate(firstFilePath, secondFilePath);
        String expected = "{\n + breaktest: 20\n - faketest: \"false\"\n + faketest: 20" +
                "\n   make: 20\n - test: true\n + test: 10\n}";
        System.out.println(actual);
        assertEquals(expected, actual);
    }
}
