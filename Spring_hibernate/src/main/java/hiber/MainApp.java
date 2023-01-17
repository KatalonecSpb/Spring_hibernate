package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru")
              .setCar(new Car("Model1", 111)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru")
              .setCar(new Car("Model2", 222)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru")
              .setCar(new Car("Model3", 333)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru")
              .setCar(new Car("Model4", 444)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = ["+user.getCar()+"]");
         System.out.println();
      }

      User user = userService.getUserByCar("Model1", 111);
      System.out.println("Id = "+user.getId());
      System.out.println("First Name = "+user.getFirstName());
      System.out.println("Last Name = "+user.getLastName());
      System.out.println("Email = "+user.getEmail());
      System.out.println("Car = ["+user.getCar()+"]");
      System.out.println();

      try {
         User notFoundUser = userService.getUserByCar("Model 5678", 4568);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
