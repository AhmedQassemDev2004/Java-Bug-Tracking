package Users;

import entites.Bug;
import entites.User;
import enums.Role;

public class Tester extends User {

    public Tester(User user) {
        super(user.getId(), user.getName(), user.getEmail(),user.getPassword(), Role.TESTER);
    }
    public Tester(Integer id, String name, String email,String password) {
        super(id, name,email,password,Role.TESTER);
    }

    public void insertBug(Bug bug) {
        // insert bug to file
    }

    public void assignToDeveloper(Bug bug, Developer developer) {
        // assign bug to developer
    }

    public void attachScreenShot(Bug bug) {

    }

    public void monitorBugs() {

    }
}
