package model;

public class Complaint {
    private String username;
    private String content;
    private String timestamp;

    public Complaint(String username, String content, String timestamp) {
        this.username = username;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String toFileFormat() {
        return username + "|" + content + "|" + timestamp;
    }

    public static Complaint fromFileFormat(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 3) {
            return new Complaint(parts[0], parts[1], parts[2]);
        }
        return null;
    }

    public String getUsername() { return username; }
    public String getContent() { return content; }
    public String getTimestamp() { return timestamp; }
}