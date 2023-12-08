package services;

import entites.User;
import enums.Role;

public class AuthService {
    static UserService userService = new UserService();
    public static User login(String email,String password) {
        User user = userService.findOneByEmail(email);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public static boolean register(User user) {
        return false;
    }

    public void logout() {
        return;
    }
}
