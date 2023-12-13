import Users.*;
import Utility.Utility;
import entites.User;
import services.AuthService;

public class Main {
    public static void main(String[] args) {
        Utility.printLine();
        System.out.println("Welcome to the bug tracking system");

        AuthService authService = new AuthService();

        User user = authService.login();

        if (user == null) {
            System.exit(0);
        }

        Menu menu = new Menu(user);
        menu.showMenu();
    }
}
