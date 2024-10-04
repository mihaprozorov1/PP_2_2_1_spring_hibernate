package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import jakarta.persistence.NoResultException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"),new Car("Toyota", 4));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"), new Car("Lexus", 3));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"), new Car("Mercedes", 5));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"), new Car("BMW", 6));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+ user.getCar());

         System.out.println();
      }

      User user = userService.getUserByModelCar("Model1",11);
      System.out.println("For user id = "+user.getId() + "Name is - " + user.getFirstName() + " Last name is - " + user.getLastName() + " Email is - " + user.getEmail()+ "Car is - " + "{ "+user.getCar()+" }");

      try {
         User notFoundUser = userService.getUserByModelCar("Model767",88);
      } catch (NoResultException e) {
         System.out.println("No found");
      }

      context.close();
   }
}
