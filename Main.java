import model.Complaint;
import service.AuthService;
import service.EmergencyService;
import dao.ComplaintDAO;
import util.Validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static AuthService authService = new AuthService();
    private static ComplaintDAO complaintDAO = new ComplaintDAO();
    private static EmergencyService emergencyService = new EmergencyService();
    private static String currentUser = null;

    public static void main(String[] args) {
        // Auto-launch GUI
        ui.GUIApp gui = new ui.GUIApp();
        gui.init();
        System.out.println("GUI launched. CLI remains available.");
    
        while (true) {
            if (currentUser == null) showAuthMenu();
            else showMainMenu();
        }
    }

    private static void showAuthMenu() {
        System.out.println("\n--- Emergency Alert and Complaint System ---");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose option: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> register();
            case "2" -> login();
            case "3" -> System.exit(0);
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (!Validator.isValid(username)) {
            System.out.println("Invalid username.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (!Validator.isValid(password)) {
            System.out.println("Invalid password.");
            return;
        }

        if (authService.register(username, password)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Username already exists.");
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (authService.login(username, password)) {
            currentUser = username;
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void showMainMenu() {
        System.out.println("\n--- Welcome " + currentUser + " ---");
        System.out.println("1. File Complaint");
        System.out.println("2. View My Complaints");
        System.out.println("3. Raise Emergency Alert");
        System.out.println("4. Logout");
        System.out.print("Choose option: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> fileComplaint();
            case "2" -> viewComplaints();
            case "3" -> emergencyService.raiseEmergency(currentUser);
            case "4" -> currentUser = null;
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void fileComplaint() {
        System.out.print("Enter complaint details: ");
        String content = scanner.nextLine();
        if (!Validator.isValid(content)) {
            System.out.println("Complaint cannot be empty.");
            return;
        }
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        complaintDAO.saveComplaint(new Complaint(currentUser, content, timestamp));
        System.out.println("Complaint registered.");
    }

    private static void viewComplaints() {
        List<Complaint> complaints = complaintDAO.getUserComplaints(currentUser);
        if (complaints.isEmpty()) {
            System.out.println("No complaints found.");
            return;
        }
        System.out.println("\nYour Complaints:");
        for (Complaint c : complaints) {
            System.out.println("- [" + c.getTimestamp() + "] " + c.getContent());
        }
    }
}