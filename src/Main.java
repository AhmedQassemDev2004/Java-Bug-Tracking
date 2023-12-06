import Users.Admin;
import Users.Developer;
import Users.ProjectManager;
import Users.Tester;
import entites.User;
import services.AuthService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String email, password;

        System.out.println("Welcome to bug tracking system");
        System.out.println("Please login");
        printLine();

        System.out.print("Email : ");
        email = scanner.nextLine();

        System.out.print("Password : ");
        password = scanner.nextLine();

        User user = AuthService.login(email,password);

        if(user == null) {
            System.out.println("Login failed");
            System.exit(0);
        }

        switch (user.getRole()) {
            case ADMIN -> {
                adminView(user);
            }
            case TESTER -> {
                testerView(user);
            }
            case DEVELOPER -> {
                developerView(user);
            }
            case PROJECT_MANAGER -> {
                projectManagerView(user);
            }
        }
    }

    public static void adminView(User user) {
        Admin admin = new Admin(user);
        // write admin view and actions
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

    public static void printLine() {
        System.out.println("======================================");
    }
}