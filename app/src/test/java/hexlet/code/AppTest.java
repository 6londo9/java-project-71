package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Utils.Formatter;
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

    @Test
    public void testYmlDiffer() throws Exception {
        firstFilePath = "./src/test/resources/file1.yml";
        secondFilePath = "./src/test/resources/file2.yml";
        String actual = Differ.generate(firstFilePath, secondFilePath);
        String expected = """
                {
                  - age: 33
                  + age: 32
                  + city: Bogota
                  - job: Civil Engineer
                    name: Garry
                }""";
        assertEquals(expected, actual);
    }

    @Test
    public void testStylishYmlDiffer() throws Exception {
        firstFilePath = "./src/test/resources/stylishyml1.yml";
        secondFilePath = "./src/test/resources/stylishyml2.yml";
        String actual = Differ.generate(firstFilePath, secondFilePath);
        String expected = """
                {
                  - age: 14
                  + faculty: Gryffindor
                  - friends: {first=Germiona, second=Ron}
                  + friends: {first=Germiona, second=Hagrid}
                    name: Garry
                }""";
        assertEquals(expected, actual);
    }

    @Test
    public void testPlainDiffer() throws Exception {
        firstFilePath = "./src/test/resources/stylishyml1.yml";
        secondFilePath = "./src/test/resources/stylishyml2.yml";
        String actual = Differ.generate(firstFilePath, secondFilePath, "plain");
        String expected = """
                \nProperty 'age' was removed
                Property 'faculty' was added with value: 'Gryffindor'
                Property 'friends' was updated. From [complex value] to [complex value]
                """;
        assertEquals(expected, actual);
    }
}
