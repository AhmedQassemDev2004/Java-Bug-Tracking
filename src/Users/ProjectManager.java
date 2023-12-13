package Users;

import entites.Bug;
import entites.User;
import enums.BugStatus;
import enums.Role;
import services.BugService;

import java.util.ArrayList;
import java.util.Scanner;

public class ProjectManager extends User {

    private final BugService BS = new BugService();

    public ProjectManager(User user) {
        super(user.getId(), user.getName(), user.getEmail(), user.getPassword(), Role.PROJECT_MANAGER);
    }

    public ProjectManager(Integer id, String name, String email, String password) {
        super(id, name, email, password, Role.PROJECT_MANAGER);
    }

    private void checkDeveloperPerformance() {
        ArrayList<User> developers = new ArrayList<>();
        ArrayList<Integer> bugsCompletedCount = new ArrayList<>();
        ArrayList<Integer> bugsUncompletedCount = new ArrayList<>();

        for (Bug bug : BS.find()) {
            User developer = bug.getDeveloper();
            int index = developers.indexOf(developer);

            if (index == -1) {
                developers.add(developer);
                bugsCompletedCount.add(0);
                bugsUncompletedCount.add(0);
                index = developers.indexOf(developer);
            }

            if (bug.getStatus() == BugStatus.COMPLETED) {
                bugsCompletedCount.set(index, bugsCompletedCount.get(index) + 1);
            } else {
                bugsUncompletedCount.set(index, bugsUncompletedCount.get(index) + 1);
            }
        }

        System.out.println("\nDeveloper Performance:\n");

        String format = "| %-20s | %-15s | %-15s |\n";
        String separator = "+----------------------+-----------------+-----------------+";

        System.out.println(separator);
        System.out.format(format, "Developer Name", "Bugs Completed", "Overdue Bugs");
        System.out.println(separator);

        for (int i = 0; i < developers.size(); i++) {
            User developer = developers.get(i);
            int bugsCompleted = bugsCompletedCount.get(i);
            int bugsUncompleted = bugsUncompletedCount.get(i);
            System.out.format(format, developer.getName(), bugsCompleted, bugsUncompleted);
            System.out.println(separator);
        }
    }

    private void checkTesterPerformance() {
        ArrayList<User> testers = new ArrayList<>();
        ArrayList<Integer> bugsFoundCount = new ArrayList<>();

        for (Bug bug : BS.find()) {
            User tester = bug.getTester();
            int index = testers.indexOf(tester);

            if (index == -1) {
                testers.add(tester);
                bugsFoundCount.add(1);
            } else {
                bugsFoundCount.set(index, bugsFoundCount.get(index) + 1);
            }
        }

        System.out.println("\nTester Performance:\n");

        String format = "| %-20s | %-15s |\n";
        String separator = "+----------------------+-----------------+";

        System.out.println(separator);
        System.out.format(format, "Tester Name", "Bugs Found");
        System.out.println(separator);

        for (int i = 0; i < testers.size(); i++) {
            User tester = testers.get(i);
            int bugsFound = bugsFoundCount.get(i);
            System.out.format(format, tester.getName(), bugsFound);
            System.out.println(separator);
        }
    }

    public void checkDeveloperAndTesterPerformance() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("""
                Select an option:
                1 - Check developers performance.
                2 - Check testers performance.
                3 - Previous menu.""");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkDeveloperPerformance();
                    break;
                case 2:
                    checkTesterPerformance();
                    break;
                case 3:
                    System.out.println("Exiting performance check.");
                    break;
                default:
                    System.out.println("INVALID CHOICE");
            }
        } while (choice != 3);
    }

    public void monitorBugs() {
        ArrayList<Bug> completedBugs = new ArrayList<>();
        ArrayList<Bug> notCompletedBugs = new ArrayList<>();

        for (Bug bug : BS.find()) {
            if (bug.getStatus() == BugStatus.COMPLETED)
                completedBugs.add(bug);
            else
                notCompletedBugs.add(bug);
        }

        System.out.println("CLOSED BUGS : ");
        BugService.displayBugs(completedBugs);

        System.out.println("\nOPENED BUGS : ");
        BugService.displayBugs(notCompletedBugs);
    }
}
