package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/emergencydb";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678@";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            // Driver not found; ensure connector is on classpath
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void insertComplaint(String username, String content) {
        String sql = "INSERT INTO complaints(username, content) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, content);
            ps.executeUpdate();
            System.out.println("[DB] Complaint saved for user: " + username);
        } catch (Exception e) {
            System.err.println("[DB] Failed to save complaint: " + e.getMessage());
        }
    }

    public static void insertEmergency(String username, String details) {
        String sql = "INSERT INTO emergencies(username, details) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, details);
            ps.executeUpdate();
            System.out.println("[DB] Emergency saved for user: " + username);
        } catch (Exception e) {
            System.err.println("[DB] Failed to save emergency: " + e.getMessage());
        }
    }
}
