package dao;

import model.User;
import util.FileUtil;

import java.util.List;

public class UserDAO {
    private static final String FILE_PATH = "users.txt";

    public void saveUser(User user) {
        FileUtil.appendLine(FILE_PATH, user.toFileFormat());
    }

    public User getUser(String username) {
        List<String> lines = FileUtil.readLines(FILE_PATH);
        for (String line : lines) {
            User user = User.fromFileFormat(line);
            if (user != null && user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}