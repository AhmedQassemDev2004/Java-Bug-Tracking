package services;

import Utility.Utility;
import entites.User;
import enums.Role;

import java.util.Scanner;

public class AuthService {
    static UserService userService = new UserService();
    public static User login(String email,String password) {

        User user = userService.findOneByEmail(email);

        if(user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public void logout() {
        System.exit(0);
        return;
    }
}
