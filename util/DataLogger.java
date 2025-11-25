package util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class DataLogger {
    private static final String LOG_FILE = "complaints_log.txt";

    public static void log(String entry) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            fw.write(LocalDateTime.now() + " - " + entry + "\n");
            System.out.println("[LOG] " + entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
