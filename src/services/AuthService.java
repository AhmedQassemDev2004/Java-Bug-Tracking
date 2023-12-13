package services;

import Utility.Utility;
import entites.User;
import java.util.Scanner;

public class AuthService {
    private static UserService userService = new UserService();

    public User login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please login");
        Utility.printLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (!Utility.isValidEmail(email)) {
            System.out.println("Invalid email format. Please enter a valid email address.");
            return null;
        }

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userService.findOneByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        System.out.println("Login failed");
        return null;
    }

    public void logout() {
        System.exit(0);
    }
}
