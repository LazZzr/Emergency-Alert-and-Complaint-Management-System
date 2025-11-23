package dao;

import model.Complaint;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {
    private static final String FILE_PATH = "complaints.txt";

    public void saveComplaint(Complaint complaint) {
        FileUtil.appendLine(FILE_PATH, complaint.toFileFormat());
    }

    public List<Complaint> getUserComplaints(String username) {
        List<String> lines = FileUtil.readLines(FILE_PATH);
        List<Complaint> complaints = new ArrayList<>();
        for (String line : lines) {
            Complaint complaint = Complaint.fromFileFormat(line);
            if (complaint != null && complaint.getUsername().equals(username)) {
                complaints.add(complaint);
            }
        }
        return complaints;
    }
}