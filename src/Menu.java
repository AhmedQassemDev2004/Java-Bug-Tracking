import Users.Admin;
import Users.Developer;
import Users.ProjectManager;
import Users.Tester;
import Utility.Utility;
import entites.User;

import java.util.Scanner;

class Menu {
    private final User user;

    public Menu(User user) {
        this.user = user;
    }

    public void showMenu() {
        while (true) {
            switch (user.getRole()) {
                case ADMIN -> adminMenu();
                case TESTER -> testerMenu();
                case DEVELOPER -> developerMenu();
                case PROJECT_MANAGER -> projectManagerMenu();
            }
        }
    }

    private void adminMenu() {
        Admin admin = new Admin(user);
        while (true) {
            Utility.printLine();
            System.out.println("1) View All Bugs.");
            System.out.println("2) Manage Users.");
            System.out.println("3) Exit.");
            int mainChoice = new Scanner(System.in).nextInt();
            Utility.printLine();

            if (mainChoice == 1) {
                admin.viewBugs();
            } else if (mainChoice == 2) {
                manageUsers(admin);
            } else if (mainChoice == 3) {
                System.out.println("Exiting...");
                System.exit(0);
            } else {
                System.out.println("Please Enter A Valid Choice");
            }
        }
    }

    private void manageUsers(Admin admin) {
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

    private void testerMenu() {
        Tester tester = new Tester(user);
        while (true) {
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
                case 5 -> System.exit(0);

                default -> throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }

    private void developerMenu() {
        Developer dev = new Developer(user);
        Scanner scanner = new Scanner(System.in); // to use it in code
        int mChoice;
        do {
            System.out.println("1) View assigned Bug\n2) Change Bug Status\n3) Exit");
            mChoice = scanner.nextInt();
            switch (mChoice) {
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
        }while (mChoice != 3);
    }

    private void projectManagerMenu() {
        ProjectManager pr = new ProjectManager(user);
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("""
                    1 - Check developers and testers performance.
                    2 - Monitor Open and Closed bugs.
                    """);
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    pr.checkDeveloperAndTesterPerformance();
                    break;
                case 2:
                    pr.monitorBugs();
                    break;
                default:
                    System.out.println("INVALID CHOICE");
            }
        } while (choice != 3);
    }
}