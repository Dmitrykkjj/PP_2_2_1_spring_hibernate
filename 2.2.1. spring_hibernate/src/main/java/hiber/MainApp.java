package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      System.out.println("[Creating 2 users]");
      User user1 = new User("Ivan", "Ivanov", "ivan@mail.ru");
      User user2 = new User("Boris", "Borisov", "boris@mail.ru");
      System.out.println("[Users are:\n" + user1 + "\n" + user2 + "]");

      System.out.println("[Creating 2 cars]");
      Car car1 = new Car("Audi", 1);
      Car car2 = new Car("BMW", 2);
      System.out.println("[Cars are:\n" + car1 + "\n" + car2 + "]");

      user1.setCar(car1);
      user2.setCar(car2);
      System.out.println("[Users been set to cars\n" + user1 + "\n" + user2 + "]");

      userService.add(user1);
      userService.add(user2);
      System.out.println("[Users been added to DB]");

      System.out.println("[List of users:]");
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }
      System.out.println("[Users been listed from DB]");

      System.out.println("[User you get by car is:\n" + userService.getUserByCar("BMW", 2) + "]");

      context.close();

   }
}
