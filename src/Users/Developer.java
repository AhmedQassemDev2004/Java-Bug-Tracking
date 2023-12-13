package Users;

import entites.Bug;
import entites.User;
import enums.BugStatus;
import enums.Role;
import services.BugService;
import services.EmailService;
import services.UserService;

import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Developer extends User {
    private Bug[] assignedBug;

    public Developer(User user) {
        super(user.getId(), user.getName(), user.getEmail(),user.getPassword(), Role.DEVELOPER);
    }
    public Developer(Integer id, String name, String email,String password) {
        super(id, name, email,password,Role.DEVELOPER);
    }

    public Bug[] getAssignedBug() {
        return assignedBug;
    }

    public void setAssignedBug(Bug[] assignedBug) {
        this.assignedBug = assignedBug;
    }

    public void viewAssignedBugs() {
        // Print list of assigned bugs with its details
        BugService BS = new BugService();
        List<Bug> bugs = BS.find();
        // Call monitorBugs function

        List<Bug> notCompletedBugs = new ArrayList<>();

        for (Bug bug : bugs) {
            if (BugStatus.NOT_COMPLETED.equals(bug.getStatus()) && this.getId().equals(bug.getDeveloper().getId())) {
                notCompletedBugs.add(bug);
            }
        }

        if(notCompletedBugs.isEmpty()) System.out.println("No Bugs To be showed ");
        else {
            String format = "| %-4s | %-10s | %-10s | %-10s | %-10s | %-11s | %-10s | %-15s | %-15s | %-15s |\n";
            String separator = "+-------------------------------------------------------------------------------------------------------------------------------------------+";

            System.out.println(separator);
            System.out.format(format, "id", "name", "Type", "priority", "Level", "projectName", "Date", "status", "developer name", "tester name");
            System.out.println(separator);

            for (Bug bug : notCompletedBugs) {
                System.out.format(format, bug.getId(), bug.getBugName(), bug.getBugType(),
                        bug.getPriority(), bug.getBugLevel(), bug.getProjectName(), bug.getBugDate(),
                        bug.getStatus(), bug.getDeveloper().getName(), bug.getTester().getName());
                System.out.println(separator);
            }
        }

    }

    public void changeBugStatus() {
        // View bugs
        viewAssignedBugs();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Bug ID: ");
        int bugId = scanner.nextInt();

        BugService bugService = new BugService();
        Bug bug;
        bug = bugService.findOne(bugId);

        if(bug != null ){
            EmailService es = new EmailService();
            if (bug.getStatus() == BugStatus.NOT_COMPLETED){
                if (this.getId().equals(bug.getDeveloper().getId())) {
                    bug.setStatus(BugStatus.COMPLETED);
                    bugService.saveDataToFile();
                    System.out.println("Task has been completed");
                    String testerEmail = bug.getTester().getEmail();
                    System.out.println("Email sent to tester : " + testerEmail);
                }
                else {
                    System.out.println("You have no access to this Bug");
                }
            }
            else {
                System.out.println("Completed Task already");
            }
        }

    }

}

