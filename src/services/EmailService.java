package services;

import Users.Developer;
import Users.Tester;
import entites.User;

public class EmailService {
     public void sendEmailToDev(int DevId) {
         UserService userService = new UserService();
         User dev = userService.findOne(DevId);

         if(dev != null) {
             System.out.println("Email sent to developer : " + dev.getEmail());
         }
     }

    public void sendEmailToTester(int testerId) {
        UserService userService = new UserService();
        User tester = userService.findOne(testerId);

        if(tester != null) {
            System.out.println("Email sent to tester : " + tester.getEmail());
        }
    }
}
