package ocp.study.part6;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FilesDemo {

    private static String ROOT_DIR = "C:\\Putty";

    static void files() throws IOException {
        System.out.println("--> List output:");
        // list(...)
        Files.list(Paths.get(ROOT_DIR))
                .filter(p -> p.toString().contains(".EXE"))
                .forEach(System.out::println);

        System.out.println("--> Walk output:");
        Files.walk(Paths.get(ROOT_DIR), 3, FileVisitOption.FOLLOW_LINKS)
                .filter(p -> p.toString().contains(".EXE"))
                .forEach(System.out::println);

        System.out.println("--> find output:");
        try (Stream<Path> p3 = Files.find(Paths.get(ROOT_DIR), 3, (path, attr) -> (path.toString().contains(".EXE")))) {
            p3.forEach(System.out::println);
        }

        System.out.println("--> lines output:");
        try (Stream<String> lines = Files.lines(Paths.get(ROOT_DIR + "/Test.txt"))) {
            lines.filter(s -> s.contains("test")).forEach(System.out::println);
        }
    }

    public static void main(String[] args) throws IOException {
        files();
    }
}
