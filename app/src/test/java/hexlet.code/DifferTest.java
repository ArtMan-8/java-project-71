package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static final String FIXTURES_DIR = "./src/test/resources/fixtures/";

    private static String readFixtures(String filepath) throws Exception {
        Path path = Paths.get(FIXTURES_DIR + filepath).toAbsolutePath().normalize();
        return Files.readString(path).trim();
    }

    @Test
    void generateDiffFromJsonFiles() throws Exception {
        String expected = readFixtures("expectStylishDiff.txt");
        String actual = Differ.generate(
                FIXTURES_DIR + "file1.json",
                FIXTURES_DIR + "file2.json",
                "stylish");
        assertEquals(expected, actual);
    }

    @Test
    void generateDiffFromYamlFiles() throws Exception {
        String expected = readFixtures("expectStylishDiff.txt");
        String actual = Differ.generate(
                FIXTURES_DIR + "file1.yml",
                FIXTURES_DIR + "file2.yml",
                "stylish");
        assertEquals(expected, actual);
    }
}
