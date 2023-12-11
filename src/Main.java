import Users.Admin;
import Users.Developer;
import Users.ProjectManager;
import Users.Tester;
import Utility.Utility;
import entites.User;
import services.AuthService;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the bug tracking system");
        System.out.println("Please login");
        Utility.printLine();
        Scanner scanner = new Scanner(System.in);
        String email, password;

        System.out.print("Email: ");
        email = scanner.nextLine();

        if (!Utility.isValidEmail(email)) {
            System.out.println("Invalid email format. Please enter a valid email address.");
            System.exit(0);
        }

        System.out.print("Password: ");
        password = scanner.nextLine();

        User user = AuthService.login(email,password);

        if (user == null) {
            System.out.println("Login failed");
            System.exit(0);
        }

        System.out.println(user.toString());

        while (true) {
            switch (user.getRole()) {
                case ADMIN -> adminView(user);
                case TESTER -> testerView(user);
                case DEVELOPER -> developerView(user);
                case PROJECT_MANAGER -> projectManagerView(user);
            }
        }
    }

    public static void adminView(User user) {
        Admin admin = new Admin(user);
        // Write admin view and actions
    }

    public static void testerView(User user) {
        Tester tester = new Tester(user);
        // Write tester view and actions
    }

    public static void developerView(User user) {
        Developer dev = new Developer(user);
        // Write developer view and actions
    }

    public static void projectManagerView(User user) {
        ProjectManager pr = new ProjectManager(user);
        // Write developer view and actions
    }

}
