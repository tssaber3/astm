package first.demo.service;

import first.demo.pojo.User;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/userservlet")
public interface UserService {

    User selUserByUsername(String username);

    User selUserByUsernameAndPassword(String username,String password);

    boolean saveUser(User user);

    List<User> getAllStu();

    User getUserById(int id);

    //通过学生id删除学生
    void delUserById(int id);

    //更新用户信息
    void updUser(User user);

    //更新用户密码
    void updUserPassword(String password);
}
