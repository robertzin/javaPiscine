import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: \"java Program --current-folder=[somePath]");
            System.exit(1);
        }
        if (args[0].indexOf("=") != 16) {
            System.err.println("Usage: \"java Program --current-folder=[somePath]");
            System.exit(1);
        }
        Path rootDirectory = Paths.get(args[0].substring(args[0].indexOf("=") + 1));
        if (Files.isDirectory(rootDirectory) && Files.exists(rootDirectory)) {
            System.out.println(rootDirectory);
        } else {
            System.err.println("Incorrect root path: " + rootDirectory);
            System.exit(1);
        }

        Commands cmds = new Commands(rootDirectory);
        Scanner scanner = new Scanner(System.in);
        String input;
        while (scanner.hasNextLine() && !(input = scanner.nextLine()).equals("exit")) {
            try {
                String[] cmdsArr = input.split(" ");
                switch (cmdsArr[0]) {
                    case "ls":
                        cmds.ls();
                        break;
                    case "mv":
                        cmds.mv(cmdsArr[1], cmdsArr[2]);
                        break;
                    case "cd":
                        cmds.cd(Paths.get(cmdsArr[1]));
                        break;
                    default:
                        if (!input.equals(""))
                            System.out.println("command not found: " + cmdsArr[0]);
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.toString());
            }
        }



    }
}
