import java.io.IOException;
import java.nio.file.*;

public class Commands {

    private final Path rootDirectory;
    private Path currDirectory;

    public Commands(Path rootDirectory) {
        this.rootDirectory = rootDirectory;
        this.currDirectory = rootDirectory;
    }

    public void ls() {
        int len = currDirectory.toString().length();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(currDirectory)) {
            for (Path file : stream) {
                String res = file.toString().substring(len + 1);
                System.out.println(res + " " + Files.size(file) + " KB");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void mv(String source, String target) throws IOException {
        Path sourceP = Paths.get(currDirectory + "/" + source).normalize();
        Path targetP = Paths.get(currDirectory + "/" + target).normalize();

        if (Files.isRegularFile(sourceP)) {
            if (Files.isDirectory(targetP)) {
                targetP = Paths.get(targetP + "/" + sourceP.getFileName());
            }
            Files.move(sourceP, targetP, StandardCopyOption.REPLACE_EXISTING);
        }
        else {
            System.err.println("No such file: " + sourceP.toString());
        }
    }

    public void cd(Path newPath) {
        Path path = Paths.get(currDirectory.toString() + "/" + newPath.toString());
        if (Files.exists(path) && Files.isDirectory(path)) {
            currDirectory = path.normalize();
            System.out.println(this.currDirectory);
        }
        else
            System.out.println("Wrong path");
    }
}