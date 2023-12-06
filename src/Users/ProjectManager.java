package Users;

import entites.User;
import enums.Role;

public class ProjectManager extends User {

    public ProjectManager(User user) {
        super(user.getId(), user.getName(), user.getEmail(),user.getPassword(), Role.PROJECT_MANAGER);
    }
    public ProjectManager(Integer id, String name, String email,String password) {
        super(id, name, email, password,Role.PROJECT_MANAGER);
    }

    public void checkDeveloperPerformance() {
        // Unknown
    }

    public void monitorBugs() {
        // Unknown
    }
}
