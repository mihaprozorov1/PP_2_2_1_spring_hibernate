package hiber.service;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Modifier;
import java.util.List;

public interface UserService {
    void add(User user, Car car);

    List<User> listUsers();

    User getUserByModelCar(Car car);
}
