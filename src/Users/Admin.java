package Users;

import entites.User;
import enums.Role;

public class Admin extends User {
    public Admin(User user) {
        super(user.getId(), user.getName(), user.getEmail(),user.getPassword(), Role.ADMIN);
    }

    public Admin(Integer id, String name, String email,String password) {
        super(id, name, email,password, Role.ADMIN);
    }

    public void viewBugs() {
   	
    }

    public void addUser() {

    }

    public void showUsers() {

    }

    public void updateUser() {

    }

    public void deleteUser() {

    }
}
