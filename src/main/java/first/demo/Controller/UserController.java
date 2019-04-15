package first.demo.Controller;

import first.demo.Dao.UserRepository;
import first.demo.Pojo.User;
import first.demo.Service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

//    返回值是0：账密错误 1：管理员 2：老师 3：学生
    @RequestMapping("/login")
    public void login(HttpServletResponse response, HttpServletRequest request, @RequestParam("username")String username,@RequestParam("password")String password) throws IOException {
//        response.setHeader("Access-Control-Allow-Origin", "*");//解决跨域
        PrintWriter out = response.getWriter();
        System.out.println("sss");
        User user = userService.selUserByUsernameAndPassword(username,password);
        if(user != null)
        {
            if (user.getRole().getName().equals("管理员"))
            {
                out.print(1);

            }else if (user.getRole().getName().equals("老师"))
            {
                out.print(2);
            }else if (user.getRole().getName().equals("学生"))
            {
                out.print(3);
            }
        }else
        {
            out.print(0);
        }
        out.flush();
    }


    //每次进入都要确认
    @RequestMapping("/iflogin")
    public void iflogin(HttpServletResponse response, HttpServletRequest request,@RequestParam("username")String username,@RequestParam("power")String power)
    {

    }
}
