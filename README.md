# 🚨 Emergency Alert and Complaint Management System

### 📘 Project Overview

A Java-based console application that allows users to securely register, log complaints, and raise emergency alerts. This lightweight system uses file-based storage and emphasizes modular design, error handling, and user interaction through a clean command-line interface.

## ✅ Core Functionalities

- User Registration & Login (with validation)
- Complaint Filing (with timestamp)
- View Complaint History
- Raise Emergency Alert (with real-time timestamp)
- Persistent Data Storage (.txt files)
- Modular Layered Architecture
- Robust Error Handling & Input Validation

## 🧠 Technologies Used

- Java (JDK 17+)
- File Handling (BufferedReader, FileWriter)
- Scanner-based Console UI
- Object-Oriented Programming (OOP)

## 📁 Folder Structure

EmergencyAlertSystem/
├── model/        # User & Complaint classes  
├── dao/          # Data access operations  
├── service/      # Business logic (auth/emergency)  
├── util/         # Validators and file helpers  
├── Main.java     # Entry point and UI navigation  
├── README.md     # Project documentation

## ▶️ How to Run the Project

### Prerequisites
- Java JDK 17 or later installed
- Any Java IDE (VS Code, IntelliJ, Eclipse) or terminal

### Compile & Execute

javac */*.java *.java
java Main

## 🧪 Input Validations

- Username and password must not be empty
- Duplicate usernames are rejected during registration
- Complaint content must be non-empty
- Invalid menu options show error messages without crashing

## 💥 Emergency Alert Feature

Users can raise an emergency alert at any time. The alert is instantly displayed on screen with a timestamp and the user's name.

Example:
[EMERGENCY] Alert from abhinav_jha at 2025-06-08 19:22:13

## 🧪 Testing & Reliability

- Fully tested on Java JDK 17
- Input validation across all major user inputs
- Files auto-create if not present (users.txt, complaints.txt)
- Error handling prevents crashes and guides user on invalid actions
- Clean and intuitive CLI for all user roles

## 👥 Team Members

Name           | Role                          | GitHub Profile
-------------- | ----------------------------- | -----------------------------------------
Abhinav Jha    | System Architect               | https://github.com/Scranoid
Sahil Mittal   | Core Developer                 | https://github.com/LazZzr
Paras Jha      | Auth System Developer          | https://github.com/parasjha55
Suraj Baghel   | Complaint Handling Integration | https://github.com/Surajbhaghel

## 📄 License

This project is intended for academic use only under Galgotias University’s B.Tech CSE curriculum.
