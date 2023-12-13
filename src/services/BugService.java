package services;

import Users.Tester;
import entites.Bug;
import entites.User;
import enums.BugStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BugService implements Service<Bug> {

    private final File file = new File("data\\bugs.txt");
    private final UserService userService = new UserService();
    private ArrayList<Bug> bugs;

    public BugService() {
        loadDataFromFile();
    }

    @Override
    public void saveDataToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Bug bug : bugs) {
                writer.println(bug.getId() + ","
                        + bug.getBugName() + ","
                        + bug.getBugType() + ","
                        + bug.getPriority() + ","
                        + bug.getBugLevel() + ","
                        + bug.getProjectName() + ","
                        + bug.getBugDate() + ","
                        + bug.getStatus() + ","
                        + (bug.getDeveloper() != null ? bug.getDeveloper().getId() : "0") + ","
                        + (bug.getTester() != null ? bug.getTester().getId() : "0"));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insert(Bug bug) {
        // Generate ID automatically
        int lastId = (bugs.isEmpty() ? 0 : bugs.get(bugs.size() - 1).getId());
        bug.setId(lastId + 1);

        // Implement insert logic here
        bugs.add(bug);
        saveDataToFile();
    }

    @Override
    public ArrayList<Bug> find() {
        // Implement find logic here
        return new ArrayList<>(bugs);
    }

    @Override
    public Bug findOne(Integer id) {
        // Implement findOne logic here
        for (Bug bug : bugs) {
            if (bug.getId().equals(id)) {
                return bug;
            }
        }
        return null;
    }

    @Override
    public void delete(Bug bug) {
        // Implement delete logic here
        bugs.remove(bug);
        saveDataToFile();
    }

    @Override
    public void update(Bug updatedBug) {
        for (int i = 0; i < bugs.size(); i++) {
            if (bugs.get(i).getId().equals(updatedBug.getId())) {
                bugs.set(i, updatedBug);
                saveDataToFile();
                return;
            }
        }
    }

    private void loadDataFromFile() {
        bugs = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] bugData = scanner.nextLine().split(",");
                int id = Integer.parseInt(bugData[0]);
                String bugName = bugData[1].trim();
                String bugType = bugData[2].trim();
                String priority = bugData[3].trim();
                String bugLevel = bugData[4].trim();
                String projectName = bugData[5].trim();
                String bugDate = bugData[6].trim();
                BugStatus status = BugStatus.valueOf(bugData[7].trim());
                User developer = userService.findOne(Integer.parseInt(bugData[8].trim()));
                User tester = userService.findOne(Integer.parseInt(bugData[9].trim()));

                if (tester == null) {
                    System.out.println("NO TESTER FOUND FOR " + id);
                }
                Bug bug = new Bug(id, bugName, bugType, priority, bugLevel, projectName, bugDate, status, developer, tester);
                bugs.add(bug);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void displayBugs(ArrayList<Bug> bugs) {
        String format = "| %-4s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s\n";
        String str = "";
        str += ("+----------------------------------------------------------------------------------------------------------------------------------+\n");
        str += (String.format(format, "id", "name", "Type", "priority", "Level", "projectName", "Date", "status", "developer name", "tester name"));
        System.out.print(str);

        for (Bug bug : bugs) {
            String s = "";
            s += ("+----------------------------------------------------------------------------------------------------------------------------------+\n");
            s += (String.format(format, bug.getId(), bug.getBugName(), bug.getBugType(),
                    bug.getPriority(), bug.getBugLevel(), bug.getProjectName(), bug.getBugDate(),
                    bug.getStatus(),
                    bug.getDeveloper() != null ? bug.getDeveloper().getName() : "-",
                    bug.getTester() != null ? bug.getTester().getName() : "-"));
            s += ("+----------------------------------------------------------------------------------------------------------------------------------+\n");
            System.out.print(s);
        }
    }
}