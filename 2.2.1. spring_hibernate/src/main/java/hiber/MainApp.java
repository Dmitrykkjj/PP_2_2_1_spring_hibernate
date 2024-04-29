package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      User user1 = new User("Ivan", "Ivanov", "ivan@mail.ru");
      User user2 = new User("Boris", "Borisov", "boris@mail.ru");

      Car car1 = new Car("Audi", 1);
      Car car2 = new Car("BMW", 2);



      user1.setCar(car1);
      user2.setCar(car2);

      car1.setUser(user1);
      car2.setUser(user2);

      userService.add(user1);
      userService.add(user2);

      carService.add(car1);
      carService.add(car2);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      System.out.print(userService.getUserByCar("BMW", 2));

      context.close();

   }
}
