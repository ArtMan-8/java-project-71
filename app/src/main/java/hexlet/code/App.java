package hexlet.code;

import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff",
        version = "gendiff 1.0",
        description = "Compares two configuration files(json or yaml/yml) and shows a difference.",
        mixinStandardHelpOptions = true)
public class App implements Callable<Integer> {
    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 1;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @Option(names = { "-f", "--format" }, defaultValue = "stylish",
            description = "output format stylish, plain, json [default: stylish]")
    private String format;

    @Override
    public final Integer call() throws Exception {
        try {
            String diff = Differ.generate(filepath1, filepath2, format);
            System.out.println(diff);
        }  catch (Exception error) {
            System.out.println(error.getMessage());
            return ERROR_EXIT_CODE;
        }

        return SUCCESS_EXIT_CODE;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
