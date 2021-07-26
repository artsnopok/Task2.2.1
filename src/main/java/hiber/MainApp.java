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

      userService.add(new User("Водитель Nissan", "Lastname1", "user1@mail.ru",
              new Car("Nissan", 1)));
      userService.add(new User("Водитель BMW", "Lastname2", "user2@mail.ru",
              new Car("BMW", 2)));
      userService.add(new User("Водитель KIA", "Lastname3", "user3@mail.ru",
              new Car("KIA", 3)));
      userService.add(new User("Водитель Porsche", "Lastname4", "user4@mail.ru",
              new Car("Porsche", 4)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = " + user.getUserCar().getModel() + ", car series " + user.getUserCar().getSeries());
         System.out.println();
      }

      System.out.println(userService.getUserByModelAndSeries("KIA", 3).getFirstName());

      context.close();
   }
}
