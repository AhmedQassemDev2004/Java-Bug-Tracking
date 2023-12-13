package Users;

import entites.Bug;
import entites.User;
import enums.BugStatus;
import enums.Role;
import services.BugService;
import services.UserService;
import services.EmailService;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;

public class Tester extends User {

    public Tester(User user) {
        super(user.getId(), user.getName(), user.getEmail(), user.getPassword(), Role.TESTER);
    }

    public Tester(Integer id, String name, String email, String password) {
        super(id, name, email, password, Role.TESTER);
    }

    public void insertBug() {
        // insert bug to file
        String bugName, bugType, priority, bugLevel, projectName;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Bug Details");

        System.out.print("Name : ");
        bugName = scanner.nextLine();

        System.out.print("Type : ");
        bugType = scanner.nextLine();

        System.out.print("Priority : ");
        priority = scanner.nextLine();

        System.out.print("Level : ");
        bugLevel = scanner.nextLine();

        System.out.print("Project Name : ");
        projectName = scanner.nextLine();

        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Format the date and time as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String bugDate = currentDateTime.format(formatter);

        User dev = new User(0, null, null, null, null);
        User tester = new User(this.getId(), this.getName(), this.getEmail(), this.getPassword(), this.getRole());
        Bug bug = new Bug(null, bugName, bugType, priority, bugLevel, projectName, bugDate,
                BugStatus.NOT_COMPLETED, dev, tester);
        BugService bugService = new BugService();
        bugService.insert(bug);
        System.out.println("Inserted Successfully");
        System.out.println();
    }

    public void assignToDeveloper() {
        // Display NOT_COMPLETED bugs
        BugService bugService = new BugService();
        ArrayList<Bug> notCompletedBugs = new ArrayList<>(bugService.find().stream()
                .filter(bug -> BugStatus.NOT_COMPLETED.equals(bug.getStatus()))
                .collect(Collectors.toList()));

        if (!notCompletedBugs.isEmpty()) {
            System.out.println("Opened bugs:");
            BugService.displayBugs(notCompletedBugs);

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Bug Id: ");
            int bugID = scanner.nextInt();

            Bug bug = bugService.findOne(bugID);

            if (bug != null) {
                // Display developers for selection
                UserService userService = new UserService();
                ArrayList<User> allUsers = userService.find();

                ArrayList<User> developers = allUsers.stream()
                        .filter(user -> user.getRole() == Role.DEVELOPER)
                        .collect(Collectors.toCollection(ArrayList::new));

                if (!developers.isEmpty()) {
                    UserService.displayUsers(developers);

                    System.out.print("Enter Developer Id: ");
                    int devID = scanner.nextInt();

                    User dev = userService.findOne(devID);
                    User tester = userService.findOne(this.getId());

                    if (dev != null && dev.getRole() == Role.DEVELOPER) {
                        bug.setDeveloper(dev);
                        bug.setTester(tester);
                        bugService.update(bug);

                        System.out.println("Assigned Successfully");

                        EmailService emailService = new EmailService();
                        emailService.sendEmailToDev(devID);
                        System.out.println();
                    } else {
                        System.out.println("ERROR: Developer not found or not a valid developer");
                    }
                } else {
                    System.out.println("ERROR: No available developers");
                }
            } else {
                System.out.println("ERROR: Bug not found");
            }
        } else {
            System.out.println("No Opened bugs to assign.");
        }
    }


    public void attachScreenShot() {

        try {
            // Create an instance of Robot class
            Robot robot = new Robot();

            // Capture the entire screen
            BufferedImage screenshot = robot
                    .createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

            // Save the screenshot to a file
            saveScreenshot(screenshot, "fullScreen.png");

            // Sleep for a moment (you might need to adjust this based on your use case)
            Thread.sleep(2000);

            // Capture a specific area (replace the coordinates and size as needed)
            Rectangle areaRect = new Rectangle(100, 100, 500, 500);
            BufferedImage areaScreenshot = robot.createScreenCapture(areaRect);

            // Save the area screenshot to a file
            saveScreenshot(areaScreenshot, "areaScreenshot.png");

        } catch (AWTException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void saveScreenshot(BufferedImage image, String fileName) throws IOException {
        // Save the image to a file
        ImageIO.write(image, "png", new File(fileName));
        System.out.println("Screenshot saved: " + fileName);
    }

    public void monitorBugs() {
        BugService bugService = new BugService();
        ArrayList<Bug> bugs = bugService.find();

        ArrayList<Bug> completedBugs = new ArrayList<>();
        ArrayList<Bug> notCompletedBugs = new ArrayList<>();

        for (Bug bug : bugs) {
            if (BugStatus.COMPLETED.equals(bug.getStatus())) {
                completedBugs.add(bug);
            } else if (BugStatus.NOT_COMPLETED.equals(bug.getStatus())) {
                notCompletedBugs.add(bug);
            }
        }

        if (notCompletedBugs.size() > 0) {
            System.out.println("OPENED BUGS:");
            BugService.displayBugs(notCompletedBugs);
            System.out.println();
        } else {
            System.out.println("\nNO OPENED BUGS");
        }

        if (completedBugs.size() > 0) {
            System.out.println("\nCLOSED BUGS:");
            BugService.displayBugs(completedBugs);
            System.out.println();
        } else {
            System.out.println("NO CLOSED BUGS\n");
        }

    }


}
