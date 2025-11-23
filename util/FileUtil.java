package util;

import java.io.*;
import java.util.*;

public class FileUtil {
    public static void appendLine(String path, String line) {
        try {
            File file = new File(path);
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(line);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("File write error: " + e.getMessage());
        }
    }

    public static List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) return lines;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
        }
        return lines;
    }
}