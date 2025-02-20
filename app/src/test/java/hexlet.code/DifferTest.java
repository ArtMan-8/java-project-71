package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

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

    static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of("stylish", "expectStylishDiff.txt"),
                Arguments.of("plain", "expectPlainDiff.txt"),
                Arguments.of("json", "expectJsonDiff.json")
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void generateDiffFromJsonFiles(String format, String fileName) throws Exception {
        String expected = readFixtures(fileName);
        String actual = Differ.generate(
                FIXTURES_DIR + "file1.json",
                FIXTURES_DIR + "file2.json",
                format);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void generateDiffFromYamlFiles(String format, String fileName) throws Exception {
        String expected = readFixtures(fileName);
        String actual = Differ.generate(
                FIXTURES_DIR + "file1.yml",
                FIXTURES_DIR + "file2.yml",
                format);
        assertEquals(expected, actual);
    }
}
