package services;

import entites.Bug;
import entites.User;
import entities.Bug;
import enums.BugStatus;
import entities.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BugService implements Service<Bug> {

    private final File file = new File("data\\bugs.txt");
    private final UserService userService = new UserService();
    private final ArrayList<Bug> bugs = new ArrayList<>();

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
                        + bug.getDeveloper().getId());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insert(Bug bug) {
        // Generate ID automatically
        Bug lastBug = bugs.get(bugs.size() - 1);
        bug.setId(lastBug.getId() + 1);

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
        // Implement update logic here
        for (int i = 0; i < bugs.size(); i++) {
            if (bugs.get(i).getId().equals(updatedBug.getId())) {
                bugs.set(i, updatedBug);
                saveDataToFile();
                return;
            }
        }
    }

    private void loadDataFromFile() {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] bugData = scanner.nextLine().split(",");
                int id = Integer.parseInt(bugData[0]);
                String bugName = bugData[1];
                String bugType = bugData[2];
                String priority = bugData[3];
                String bugLevel = bugData[4];
                String projectName = bugData[5];
                String bugDate = bugData[6];
                BugStatus status = BugStatus.valueOf(bugData[7]);
                // Extract other bug properties here
                User developer = userService.findOne(Integer.parseInt(bugData[8]));

                Bug bug = new Bug(id, bugName, bugType, priority, bugLevel, projectName, bugDate, status, developer);
                bugs.add(bug);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
