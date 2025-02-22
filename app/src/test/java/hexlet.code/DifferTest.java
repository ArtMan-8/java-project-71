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

    private static Stream<Arguments> diffParameters() {
        return Stream.of(
                Arguments.of("stylish", "expectStylishDiff.txt"),
                Arguments.of("plain", "expectPlainDiff.txt"),
                Arguments.of("json", "expectJsonDiff.txt")
        );
    }

    @ParameterizedTest
    @MethodSource("diffParameters")
    public final void generateDiffFromJsonFiles(String format, String fileName) throws Exception {
        String expected = readFixtures(fileName);
        String actual = Differ.generate(
                FIXTURES_DIR + "file1.json",
                FIXTURES_DIR + "file2.json",
                format);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("diffParameters")
    public final void generateDiffFromYamlFiles(String format, String fileName) throws Exception {
        String expected = readFixtures(fileName);
        String actual = Differ.generate(
                FIXTURES_DIR + "file1.yml",
                FIXTURES_DIR + "file2.yml",
                format);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> fileParameters() {
        return Stream.of(
                Arguments.of("file1.json", "file2.json"),
                Arguments.of("file1.yml", "file2.yml")
        );
    }

    @ParameterizedTest
    @MethodSource("fileParameters")
    public final void generateDefaultDiff(String file1, String file2) throws Exception {
        String expected = readFixtures("expectStylishDiff.txt");

        String actual = Differ.generate(
                FIXTURES_DIR + file1,
                FIXTURES_DIR + file2
        );
        assertEquals(expected, actual);
    }
}
