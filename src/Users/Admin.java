package Users;

import Utility.Utility;
import entites.Bug;
import entites.User;
import enums.BugStatus;
import enums.Role;
import services.BugService;
import services.UserService;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    private final File file = new File("data\\bugs.txt");
    public Admin(User user) {
        super(user.getId(), user.getName(), user.getEmail(),user.getPassword(), Role.ADMIN);
    }

    public Admin(Integer id, String name, String email,String password) {
        super(id, name, email,password, Role.ADMIN);
    }

    public void viewBugs() {
        BugService bugService = new BugService();
        ArrayList<Bug> bugs = bugService.find();
        System.out.println("SHOW BUGS...");
        Utility.printLine();

        String format = "| %-4s | %-10s | %-10s | %-10s | %-10s | %-15s | %-10s | %-15s | %-10s | %-10s\n";
        String str = "";
        str += ("+-------------------------------------------------------------------------------------------------------------------------------------------+\n");
        str += (String.format(format, "id", "name", "Type", "priority", "Level", "projectName", "Date", "status", "developer", "tester"));
        str += ("+-------------------------------------------------------------------------------------------------------------------------------------------+\n");
        System.out.print(str);

        for (Bug bug : bugs) {
            String s = "";
            s += (String.format(format, bug.getId(), bug.getBugName(), bug.getBugType(),
                    bug.getPriority(), bug.getBugLevel(), bug.getProjectName(), bug.getBugDate(),
                    bug.getStatus(), ((bug.getDeveloper() != null) ? bug.getDeveloper().getName() : "NULL"), bug.getTester().getName()));
            s += ("+-------------------------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.print(s);
        }
    }

    public String showUsers() {
        UserService userService = new UserService();
        ArrayList<User> users = userService.find();
        System.out.println("SHOW USER...");
        Utility.printLine();

        String format = "| %-4s | %-20s | %-25s | %-10s\n";
        String str = "";
        str += "+-----------------------------------------------------------------------------------------------+\n";
        str += String.format(format, "ID", "Name", "Email", "Role");
        System.out.print(str);

        for (User user : users) {
            String s = "";
            s += "+-----------------------------------------------------------------------------------------------+\n";
            s += String.format(format, user.getId(), user.getName(), user.getEmail(), user.getRole());
            s += "+-----------------------------------------------------------------------------------------------+\n";
            System.out.print(s);
        }
        return null;
    }

    public void addUser() {
        UserService userService = new UserService();
        ArrayList<User> users = userService.find();

        Scanner scanner = new Scanner(System.in);
        boolean error = false;

        System.out.println("ADD USER...");
        String username = "", password = "", confirmPassword = "", email = "";
        Role role = null;

        System.out.print("Username: ");
        username = scanner.nextLine();
        System.out.print("Email: ");
        email = scanner.nextLine();
        System.out.print("Password: ");
        password = scanner.nextLine();
        System.out.print("Confirm Password: ");
        confirmPassword = scanner.nextLine();

        System.out.println("1) " + Role.ADMIN);
        System.out.println("2) " + Role.DEVELOPER);
        System.out.println("3) " + Role.PROJECT_MANAGER);
        System.out.println("4) " + Role.TESTER);
        System.out.print("Role: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> role = Role.ADMIN;
            case 2 -> role = Role.DEVELOPER;
            case 3 -> role = Role.PROJECT_MANAGER;
            case 4 -> role = Role.TESTER;
            default -> System.out.println("Not A Valid Choice");
        }
        for (User user : users) {
            if ((user.getEmail().equalsIgnoreCase(email))) {
                System.out.println("Email Already Exists");
                error = true;
                break;
            }
        }
        if (!error) {
            if (password.equals(confirmPassword)) {
                User user = new User(null, username, email, password, role);
                userService.insert(user);
                System.out.println("User added successfully");
            } else System.out.println("Password Does Not Match");
        } else {
            return;
        }

    }

    public void updateUser() {
        UserService userService = new UserService();
        ArrayList<User> users = userService.find();

        int choice;
        System.out.println("UPDATE USER...");

        Scanner scanner = new Scanner(System.in);
        showUsers();
        System.out.print("Enter User ID: ");
        int ID = new Scanner(System.in).nextInt();
        if (userService.findOne(ID) != null) {
            User user = userService.findOne(ID);

            String username, password, confirmPassword, email;
            Role role = null;

            boolean prev = false;
            while (!prev) {
                System.out.println("1) Update Username");
                System.out.println("2) Update Email");
                System.out.println("3) Update Password");
                System.out.println("4) Update Role");
                System.out.println("5) Previous Menu");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Username: ");
                        username = scanner.nextLine();
                        user.setName(username);
                        userService.update(user);
                        break;
                    case 2:
                        System.out.print("Email: ");
                        email = scanner.nextLine();
                        user.setEmail(email);
                        userService.update(user);
                        break;
                    case 3:
                        System.out.print("Password: ");
                        password = scanner.nextLine();
                        System.out.print("Confirm Password: ");
                        confirmPassword = scanner.nextLine();
                        if (password.equals(confirmPassword)) {
                            user.setPassword(password);
                            userService.update(user);
                        } else System.out.println("Password Does Not Match");
                        break;
                    case 4:
                        System.out.println("1) " + Role.ADMIN);
                        System.out.println("2) " + Role.DEVELOPER);
                        System.out.println("3) " + Role.PROJECT_MANAGER);
                        System.out.println("4) " + Role.TESTER);
                        System.out.print("Role: ");

                        int role_choice = scanner.nextInt();
                        switch (role_choice) {
                            case 1 -> role = Role.ADMIN;
                            case 2 -> role = Role.DEVELOPER;
                            case 3 -> role = Role.PROJECT_MANAGER;
                            case 4 -> role = Role.TESTER;
                            default -> System.out.println("Not A Valid Choice");
                        }
                        user.setRole(role);
                        userService.update(user);
                        break;
                    case 5:
                        Utility.printLine();
                        prev = true;
                        break;
                    default:
                        System.out.println("Not A Valid Choice");
                        break;
                }
            }

        } else System.out.println("No Such User");
    }

    public void deleteUser() {
        UserService userService = new UserService();
        ArrayList<User> users = userService.find();

        System.out.println("DELETE USER...");
        showUsers();
        System.out.print("Enter User ID: ");
        int ID = new Scanner(System.in).nextInt();

        if (userService.findOne(ID) != null) {
            userService.delete(userService.findOne(ID));
            System.out.println("Deleted Successfully");
        } else System.out.println("No Such User");
    }
}
