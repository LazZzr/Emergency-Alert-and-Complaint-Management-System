package com.eacms.service;

import com.eacms.dao.AlertDAO;
import com.eacms.util.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class AlertService {

    private final AlertDAO alertDAO = new AlertDAO();

    public void logAlertTransactional(String title, String message, int severity) throws SQLException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            alertDAO.insertAlert(conn, title, message, severity);
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) conn.setAutoCommit(true);
        }
    }
}
