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
