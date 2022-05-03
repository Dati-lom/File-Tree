package filetree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Tester {
    private static Random rand;
    private static final int maxDepth = 10;
    private static int height;
    private static final long randSeed = 6942069;
    private static final int testCasesNumber = 20;
    private static Set<Path> pathSet;

    public static void main(String[] args) throws IOException {
        String rootStr = "Tester Dir";
        Path rootPath = Paths.get(rootStr);

        if (Files.isDirectory(rootPath)) {
            Files.walk(rootPath)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }

        Files.createDirectory(rootPath);

        rand = new Random(randSeed);
        List<String> failures = new ArrayList<>();
        int passed = 0;

        for (int i = 0; i < testCasesNumber; i++) {
            String dirStr = rootStr + "\\" + "dir" + i;
            Path dirPath = Paths.get(dirStr);
            pathSet = new HashSet<>();
            height = 0;
            createDir(dirStr, 0);

            filetree.File file = FileUtil.toFileRepresentation(dirPath);

            boolean correct = true;
            // Check getHeight
            if (file.getHeight() != height) {
                correct = false;
                failures.add("Test " + i + ": getHeight.");
            }

            // Check iterator
            Set<Path> pathSet2 = new HashSet<>();
            Iterator<filetree.File> it = file.iterator();
            while (it.hasNext()) {
                pathSet2.add(it.next().getPath());
            }

            if (!pathSet.equals(pathSet2)) {
                correct = false;
                failures.add("Test " + i + ": iterator.");
            }

            // Final
            if (correct) passed++;
        }

        System.out.println("Passed " + passed + "/" + testCasesNumber + " test cases.");
        if (passed != testCasesNumber) {
            System.out.println("Failures:");
            failures.forEach(System.out::println);
        }
    }

    private static void createDir(String dirStr, int depth) throws IOException {
        if (depth > maxDepth) return;
        if (depth > height) height = depth;

        Path dirPath = Paths.get(dirStr);
        pathSet.add(dirPath);
        if (rand.nextBoolean()) {
            Files.createDirectory(dirPath);
            int size = rand.nextInt(4);
            for (int i = 0; i < size; i++) {
                createDir(dirStr + "\\" + "dir" + i, depth + 1);
            }
        } else {
            Files.createFile(dirPath);
        }
    }
}