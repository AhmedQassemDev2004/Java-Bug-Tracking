package Users;

import entites.Bug;
import entites.User;
import enums.Role;

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
    }

    public void changeBugStatus() {
        // get bug id from user
        // change status to completed
    }


}
