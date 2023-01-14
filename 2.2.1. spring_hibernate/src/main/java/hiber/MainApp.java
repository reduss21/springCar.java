package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.persistence.NoResultException;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("name", "lastname", "com@alt.com");
      User user2 = new User("noname", "nolast", "nocom@noalt.nocom");
      User user3 = new User("wat", "da", "dog@doing.com");
      User user4 = new User("raib", "shydow", "lupinds@po.com");

      Car car1 = new Car("alpha", 2021);
      Car car2 = new Car("betta", 1001);
      Car car3 = new Car("gamma", 7);
      Car car4 = new Car("delta", 290);

      userService.addUser(user1.setCar(car1).setUser(user1));
      userService.addUser(user2.setCar(car2).setUser(user2));
      userService.addUser(user3.setCar(car3).setUser(user3));
      userService.addUser(user4.setCar(car4).setUser(user4));

      for (User user : userService.getUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println("----отделение----строки----");
      }
      System.out.println(userService.getUserByCar("betta", 1001));
      System.out.println("----отделение----строки----");
        try {
           User notFoundUser = userService.getUserByCar("gag", 90);
      } catch (NoResultException e) {
           System.out.println("User not found");
           System.out.println("----отделение----строки----");
        }

      context.close();
   }
}