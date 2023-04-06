package Conn;

import entities.User;

public interface UserDAO {
    void registerUser(User user);
    boolean checkLoginUser(String userNameMail, String password);
    void deleteUser(String username);
    boolean userNameExists(String userName);
    boolean userMailExists(String userMail);
    String getUsername(String loginName);
}
