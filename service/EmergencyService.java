package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmergencyService {
    public void raiseEmergency(String username) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("[EMERGENCY] Alert from " + username + " at " + time);
    }
}