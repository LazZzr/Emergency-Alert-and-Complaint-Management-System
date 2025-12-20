package com.eacms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlertDAO {

    public void insertAlert(Connection conn, String title, String message, int severity) throws SQLException {
        String sql = "INSERT INTO alerts (title, message, severity) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, message);
            ps.setInt(3, severity);
            ps.executeUpdate();
        }
    }
}
