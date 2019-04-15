package first.demo.Service;

import first.demo.Pojo.User;

import javax.servlet.annotation.WebServlet;

@WebServlet("/userservlet")
public interface UserService {

    User selUserByUsername(String username);

    User selUserByUsernameAndPassword(String username,String password);
}
