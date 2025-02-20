package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static final String FIXTURES_DIR = "./src/test/resources/fixtures/";

    private static String readFixtures(String filepath) throws Exception {
        Path path = Paths.get(FIXTURES_DIR + filepath).toAbsolutePath().normalize();
        return Files.readString(path).trim();
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of("stylish", "expectStylishDiff.txt"),
                Arguments.of("plain", "expectPlainDiff.txt"),
                Arguments.of("json", "expectJsonDiff.txt")
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public final void generateDiffFromJsonFiles(String format, String fileName) throws Exception {
        String expected = readFixtures(fileName);
        String actual = Differ.generate(
                FIXTURES_DIR + "file1.json",
                FIXTURES_DIR + "file2.json",
                format);
        assertEquals(expected, actual);
    }

    @Test
    public final void generateDiffFromYamlFiles() throws Exception {
        String expected = readFixtures("expectJsonDiff.txt");
        String actual = Differ.generate(
                FIXTURES_DIR + "file1.yml",
                FIXTURES_DIR + "file2.yml",
                "json");
        assertEquals(expected, actual);
    }
}
