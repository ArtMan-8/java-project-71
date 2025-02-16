package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static final String FIXTURES_DIR = "./src/test/resources/fixtures/";

    private static String readFixtures(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path).trim();
    }

    @Test
    void generate() throws Exception {
        String expected = readFixtures(FIXTURES_DIR + "expectStylishDiff.txt");
        String actual = Differ.generate(
                FIXTURES_DIR + "file1.json",
                FIXTURES_DIR + "file2.json",
                "stylish");
        assertEquals(expected, actual);
    }
}
