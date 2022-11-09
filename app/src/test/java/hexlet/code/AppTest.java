package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {

    private final Map<String, Object> firstFileAsMap = new HashMap<>();
    private final Map<String, Object> secondFileAsMap = new HashMap<>();
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        firstFileAsMap.put("test", 10);
        firstFileAsMap.put("make", 20);
        firstFileAsMap.put("faketest", 30);

        secondFileAsMap.put("test", 10);
        secondFileAsMap.put("make", 20);
        secondFileAsMap.put("faketest", 20);
        secondFileAsMap.put("breaktest", 20);
    }

    @Test
    public void testDiffer() throws Exception {
        String file1 = mapper.writeValueAsString(firstFileAsMap);
        String file2 = mapper.writeValueAsString(secondFileAsMap);

        String actual = Differ.generate(file1, file2);
        Map<String, Object> expectedAsMap = new LinkedHashMap<>();
        expectedAsMap.put("+ breaktest", 20);
        expectedAsMap.put("- faketest", 30);
        expectedAsMap.put("+ faketest", 20);
        expectedAsMap.put("make", 20);
        expectedAsMap.put("test", 10);
        String expected = mapper.writeValueAsString(expectedAsMap);
        System.out.println(expected);
        System.out.println(actual);

        assertEquals(expected, actual);
    }
}
