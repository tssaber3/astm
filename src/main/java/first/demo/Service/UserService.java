package first.demo.Service;

import first.demo.Pojo.User;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/userservlet")
public interface UserService {

    User selUserByUsername(String username);

    User selUserByUsernameAndPassword(String username,String password);

    boolean saveUser(User user);

    List<User> getAllStu();

    User getUserById(int id);
}
