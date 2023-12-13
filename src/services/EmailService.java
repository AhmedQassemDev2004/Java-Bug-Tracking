package services;

import Users.Developer;

public class EmailService {
     public void sendEmailToDev(int DevId) {
         UserService userService = new UserService();
         Developer dev = new Developer(userService.findOne(DevId));

         if(dev != null) {
             System.out.println("Email sent to " + dev.getEmail());
         }
     }
}
