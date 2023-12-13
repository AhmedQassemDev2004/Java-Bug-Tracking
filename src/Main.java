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
        //write admin view and actions
        while (true) {
            Utility.printLine();
            System.out.println("1) View All Bugs.");
            System.out.println("2) Manage Users.");
            System.out.println("3) Exit.");
            int mainChoice = new Scanner(System.in).nextInt();
            Utility.printLine();

            if (mainChoice == 1) {
                admin.viewBugs();
            }
            else if (mainChoice == 2) {
                boolean prev = false;
                while (!prev) {
                    System.out.println("1) Show Users.");
                    System.out.println("2) Add User.");
                    System.out.println("3) Update User.");
                    System.out.println("4) Delete User.");
                    System.out.println("5) Previous Menu.");

                    int secChoice = new Scanner(System.in).nextInt();

                    switch (secChoice) {
                        case 1:
                            admin.showUsers();
                            break;
                        case 2:
                            admin.addUser();
                            break;
                        case 3:
                            admin.updateUser();
                            break;
                        case 4:
                            admin.deleteUser();
                            break;
                        case 5:
                            prev = true;
                            break;
                        default:
                            System.out.println("Please Enter A Valid Choice");
                            break;
                    }
                }
            }
            else if (mainChoice == 3) {
                System.out.println("Exiting...");
                System.exit(0);
                return;
            }
            else System.out.println("Please Enter A Valid Choice");
        }
    }

     public static void testerView(User user) {
         Tester tester = new Tester(user);
         while(true) {
             System.out.println("Choose Operation");
             System.out.println("1 : insert Bug");
             System.out.println("2 : assignToDeveloper");
             System.out.println("3 : attachScreenShot");
             System.out.println("4 : monitorBugs");
             System.out.println("5 : exit");

             int choice = new Scanner(System.in).nextInt();
             switch (choice) {
                 case 1 -> tester.insertBug();
                 case 2 -> tester.assignToDeveloper();
                 case 3 -> tester.attachScreenShot();
                 case 4 -> tester.monitorBugs();
                 case 5 -> {return;}

                 default -> throw new IllegalStateException("Unexpected value: " + choice);
             }
         }
    }

    public static void developerView(User user) {
        Developer dev = new Developer(user);
        // Write developer view and actions
        Scanner scanner = new Scanner(System.in); // to use it in code
        System.out.println("1) View assigned Bug\n2) Change Bug Status");
        int mChoice = scanner.nextInt();
        switch (mChoice){
            case 1:
                dev.viewAssignedBugs();
                break;
            case 2:
                dev.changeBugStatus();
                break;
            default:
                System.out.println("Wrong Input");
                break;
        }
    }

    public static void projectManagerView(User user) {
        ProjectManager pr = new ProjectManager(user);
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                    1 - Check developers and testers performance.
                    2 - Monitor Open and Closed bugs.
                    """);
        int choice = scanner.nextInt();
        switch (choice){
            case 1 :
                pr.checkDeveloperAndTesterPerformance();
                break;
            case 2 :
                pr.monitorBugs();
                break;
            default:
                System.out.println("INVALID CHOICE");
        }
    }
}
