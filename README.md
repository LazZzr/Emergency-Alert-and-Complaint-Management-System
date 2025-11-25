# 🚨 Emergency Alert & Complaint Management System
A Java-based system that allows users to register, log in, file complaints, and submit emergency alerts.  
This enhanced version includes **GUI integration, JDBC-based MySQL storage, file logging**, and a modular structure.

---

# 📁 Project File Structure

```
Emergency-Alert-and-Complaint-Management-System/
│
├── Main.java
├── pom.xml
│
├── dao/
│   ├── UserDAO.java
│   ├── ComplaintDAO.java
│
├── model/
│   ├── User.java
│   ├── Complaint.java
│
├── service/
│   ├── AuthService.java
│   ├── EmergencyService.java
│
├── util/
│   ├── Validator.java
│   ├── FileUtil.java
│   ├── DatabaseManager.java      <-- JDBC Integration
│   ├── DataLogger.java           <-- File-Based Logging
│
├── ui/
│   ├── GUIApp.java               <-- Java Swing GUI
│
└── database/
    └── emergencydb.sql           <-- MySQL DB Schema
```

---

# 🗄️ Database Schema (MySQL)

```
CREATE DATABASE emergencydb;
USE emergencydb;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    password VARCHAR(100)
);

CREATE TABLE complaints (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100),
    content TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE emergencies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100),
    details TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

# 🔌 JDBC Integration — DatabaseManager.java

```
public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/emergencydb";
    private static final String USER = "root";
    private static final String PASSWORD = "yourpassword";

    static {
        try { Class.forName("com.mysql.cj.jdbc.Driver"); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public static void insertComplaint(String username, String content) {
        String sql = "INSERT INTO complaints(username, content) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, content);
            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void insertEmergency(String username, String details) {
        String sql = "INSERT INTO emergencies(username, details) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, details);
            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }
}
```

---

# 📝 File Logger — DataLogger.java

```
public class DataLogger {

    private static final String LOG_FILE = "complaints_log.txt";

    public static void log(String entry) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            fw.write(LocalDateTime.now() + " - " + entry + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

---

# 🖥️ GUI — GUIApp.java (Swing)

```
public class GUIApp {

    public void init() {
        JFrame frame = new JFrame("Emergency Alert System");
        frame.setSize(600, 450);

        JTextField userField = new JTextField(10);
        JPasswordField passField = new JPasswordField(10);
        JTextArea textArea = new JTextArea();

        JButton loginBtn = new JButton("Login");
        JButton complaintBtn = new JButton("Submit Complaint");
        JButton alertBtn = new JButton("Report Emergency");

        loginBtn.addActionListener(e -> {
            if (authService.login(userField.getText(), new String(passField.getPassword()))) {
                JOptionPane.showMessageDialog(frame, "Login Successful");
            }
        });

        complaintBtn.addActionListener(e -> {
            DatabaseManager.insertComplaint(userField.getText(), textArea.getText());
            DataLogger.log("Complaint by " + userField.getText());
        });

        alertBtn.addActionListener(e -> {
            DatabaseManager.insertEmergency(userField.getText(), textArea.getText());
            DataLogger.log("Emergency by " + userField.getText());
        });

        frame.add(userField, BorderLayout.NORTH);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(alertBtn, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUIApp().init();
    }
}
```

---

# ▶️ Updated Main.java

```
public static void main(String[] args) {

    // Launch GUI automatically
    ui.GUIApp app = new ui.GUIApp();
    app.init();

    System.out.println("GUI launched. CLI mode still available.");
}
```

---

# 👥 Team Members

## **1️⃣ Raghav Rai**
**Role:** Main Developer, System Architect & Project Owner  
**Admission ID:** 24SCSE1010751  
**GitHub:** https://github.com/raghavrai25  
### Responsibilities
- Designed complete system architecture  
- Implemented core modules (User, Complaint, Emergency)  
- Managed file-based data storage  
- Oversaw project integration end‑to‑end  

---

## **2️⃣ Sahil Mittal**
**Role:** Login, Registration & Complaint Management Lead  
**Admission ID:** 24SCSE1011250  
**GitHub:** https://github.com/LazZzr  
### Responsibilities
- Built secure login & registration logic  
- Implemented complaint logging  
- Helped integrate emergency alert subsystem  

---

# ✅ Summary
This project includes:  
✔ GUI system  
✔ JDBC-backed complaint & emergency storage  
✔ File-based logging  
✔ Clean MVC-style structure  
✔ Complete team documentation  


