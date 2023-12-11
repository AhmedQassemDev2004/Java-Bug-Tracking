package services;

import entites.User;
import enums.Role;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserService implements Service<User> {
    private final File file = new File("data\\users.txt");
    private final ArrayList<User> users = new ArrayList<>();

    public UserService() {
        loadDataFromFile();
    }

    @Override
    public void insert(User user) {
        // Generate ID automatically
        User lastUser = users.get(users.size() - 1);
        user.setId(lastUser.getId() + 1);

        // Implement insert logic here
        users.add(user);
        saveDataToFile();
    }
    @Override
    public ArrayList<User> find() {
        return new ArrayList<>(users);
    }

    @Override
    public User findOne(Integer id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User findOneByEmail(String email) {
        for (User user:users) {
            if(user.getEmail().equals(email))
                return user;
        }

        return null;
    }

    @Override
    public void delete(User user) {
        // Implement delete logic here
        users.remove(user);
        saveDataToFile();
    }

    @Override
    public void update(User user) {
        // Implement update logic here
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                saveDataToFile();
                return;
            }
        }
    }

    @Override
    public void saveDataToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (User user : users) {
                writer.println(user.getId() + ", " + user.getName() + ", " + user.getEmail() + ", " + user.getPassword() + ", " + user.getRole());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadDataFromFile() {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] userData = scanner.nextLine().split(", ");
                int id = Integer.parseInt(userData[0]);
                String name = userData[1];
                String email = userData[2];
                String password = userData[3];
                Role role = null;
                switch (userData[4]) {
                    case "ADMIN" -> role = Role.ADMIN;
                    case "DEVELOPER" -> role = Role.DEVELOPER;
                    case "TESTER" -> role = Role.TESTER;
                    case "PROJECT_MANAGER" -> role = Role.PROJECT_MANAGER;
                }
                // Assuming the Role enum is present in the User class
                User user = new User(id, name, email, password, role);
                users.add(user);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
