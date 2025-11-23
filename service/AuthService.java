package service;

import dao.UserDAO;
import model.User;

public class AuthService {
    private UserDAO userDAO = new UserDAO();

    public boolean register(String username, String password) {
        if (userDAO.getUser(username) != null) return false;
        userDAO.saveUser(new User(username, password));
        return true;
    }

    public boolean login(String username, String password) {
        User user = userDAO.getUser(username);
        return user != null && user.getPassword().equals(password);
    }
}