package ui;

import model.Complaint;
import service.AuthService;
import service.EmergencyService;
import dao.ComplaintDAO;
import util.DataLogger;
import util.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUIApp {

    private JFrame frame;
    private JTextField userField;
    private JPasswordField passField;
    private JTextArea contentArea;
    private AuthService authService = new AuthService();
    private ComplaintDAO complaintDAO = new ComplaintDAO();
    private EmergencyService emergencyService = new EmergencyService();

    public void init() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Emergency Alert & Complaint System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 450);
            frame.setLayout(new BorderLayout());

            // Top: login panel
            JPanel top = new JPanel(new FlowLayout());
            userField = new JTextField(10);
            passField = new JPasswordField(10);
            JButton loginBtn = new JButton("Login");
            JButton openCliBtn = new JButton("Open CLI");
            top.add(new JLabel("User:"));
            top.add(userField);
            top.add(new JLabel("Pass:"));
            top.add(passField);
            top.add(loginBtn);
            top.add(openCliBtn);

            // Center: content panel
            JPanel center = new JPanel(new BorderLayout());
            contentArea = new JTextArea();
            center.add(new JScrollPane(contentArea), BorderLayout.CENTER);

            // Bottom: action buttons
            JPanel bottom = new JPanel(new FlowLayout());
            JButton submitComplaint = new JButton("Submit Complaint");
            JButton submitEmergency = new JButton("Submit Emergency");
            bottom.add(submitComplaint);
            bottom.add(submitEmergency);

            frame.add(top, BorderLayout.NORTH);
            frame.add(center, BorderLayout.CENTER);
            frame.add(bottom, BorderLayout.SOUTH);

            // Actions
            loginBtn.addActionListener(e -> {
                String user = userField.getText();
                String pass = new String(passField.getPassword());
                if (authService.login(user, pass)) {
                    JOptionPane.showMessageDialog(frame, "Login success: " + user);
                } else {
                    JOptionPane.showMessageDialog(frame, "Login failed");
                }
            });

            submitComplaint.addActionListener((ActionEvent e) -> {
                String user = userField.getText();
                if (user == null || user.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please login or enter username first.");
                    return;
                }
                String content = contentArea.getText();
                if (content == null || content.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter complaint content.");
                    return;
                }
                // existing DAO save (keeps original behavior)
                complaintDAO.saveComplaint(new Complaint(user, content));
                // additional logging and DB insert
                DataLogger.log("Complaint by " + user + ": " + content);
                DatabaseManager.insertComplaint(user, content);
                JOptionPane.showMessageDialog(frame, "Complaint submitted.");
            });

            submitEmergency.addActionListener((ActionEvent e) -> {
                String user = userField.getText();
                if (user == null || user.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please login or enter username first.");
                    return;
                }
                String details = contentArea.getText();
                if (details == null || details.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter emergency details.");
                    return;
                }
                emergencyService.raiseEmergency(user, details);
                DataLogger.log("Emergency by " + user + ": " + details);
                DatabaseManager.insertEmergency(user, details);
                JOptionPane.showMessageDialog(frame, "Emergency reported.");
            });

            openCliBtn.addActionListener(e -> {
                JOptionPane.showMessageDialog(frame, "Switching to CLI is not supported in GUI build. You can run Main.java for CLI.");
            });

            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        new GUIApp().init();
    }
}
