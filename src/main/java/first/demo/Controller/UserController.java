package first.demo.Controller;

import com.google.gson.Gson;
import first.demo.Dao.ReportRepository;
import first.demo.Dao.UserRepository;
import first.demo.Pojo.Project;
import first.demo.Pojo.Report;
import first.demo.Pojo.User;
import first.demo.Service.ReportService;
import first.demo.Service.UserService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @Resource
    private ReportService reportService;

    @Resource
    private ReportRepository reportRepository;

    @Resource
    private UserRepository userRepository;


//    返回值是0：账密错误 1：管理员 2：老师 3：学生
    @RequestMapping("/login")
    public void login(HttpServletResponse response, HttpServletRequest request, @RequestParam("username")String username,@RequestParam("password")String password) throws IOException {
//        response.setHeader("Access-Control-Allow-Origin", "*");//解决跨域
        PrintWriter out = response.getWriter();
        System.out.println("sss");
        User user = userService.selUserByUsernameAndPassword(username,password);
        if(user != null)
        {
//            request.getSession().setAttribute("username",username);
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
    public void iflogin(HttpServletResponse response, HttpServletRequest request,@RequestParam("username")String username) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("adad");
        Gson gson = new Gson();
//        if(username.equals(request.getSession().getAttribute("username")))
//        {
            User user = userService.selUserByUsername(username);
            if(user != null)
            {
                String str = gson.toJson(user);
                out.print(str);
            }else
            {
                out.print("null");
            }
//        }else
//        {
//            //重新登录
//            out.print("relogin");
//        }
        out.flush();

    }

//    项目申报
    @RequestMapping("/uploadreport")
    public void uploadreport(HttpServletResponse response,HttpServletRequest request,@RequestParam("description")String description,@RequestParam("type")String type,@RequestParam("username")String username,@RequestParam("nickname")String nickname,@RequestParam("credit")String credit,@RequestParam("project_name")String project_name) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Project project = new Project();
        Report report = new Report();
        project.setDescription(description);
        project.setType(type);
        project.setProject_name(project_name);


        report.setCredit(Integer.parseInt(credit));
        report.setProof("待审核");
        report.setReason("等待教师审核");
        report.setStu_username(username);
        report.setStu_nickname(nickname);
        report.setProject(project);

        boolean bok = reportService.addReport(report);
        out.print("SUCCESS");
        out.flush();
    }

//    获得审核列表
    @RequestMapping("/getList")
    public void getList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        List<Report> list = reportService.getReport();
        if(list != null)
        {
            Map<String,Object> map = new HashMap<>();
            map.put("report",list);
            String str = gson.toJson(map);
            out.print(str);
        }else
        {
            out.print("false");
        }
        out.flush();
    }

    //通过id得到report对象
    @RequestMapping("/getreportbyid")
    public void getreportbyid(HttpServletResponse response,HttpServletRequest request,@RequestParam("id")Integer id) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Report report =  reportService.getReportById(id);
        if(report != null)
        {
            String str = gson.toJson(report);
            out.print(str);
        }else
        {
            out.print(false);
        }
        out.flush();
    }

    //学生项目通过 项目状态变为通过 学生学分增加
    @RequestMapping("/passproject")
    public void passproject(HttpServletRequest request,HttpServletResponse response,@RequestParam("id")Integer id,@RequestParam("username")String username) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Report report = reportService.getReportById(id);
        User user = userService.selUserByUsername(username);
        report.setProof("通过");
        int Score = report.getCredit();
        //增加学分
        user.setGrade(user.getGrade() + Score);
        if(userService.saveUser(user) && reportService.saveReport(report))
        {
            out.print("success");
        }
        else
        {
            out.print("failure");
        }
        out.flush();
    }

    //学生项目不通过
    @RequestMapping("rejectproject")
    public void  rejectproject(HttpServletRequest request,HttpServletResponse response,@RequestParam("id")Integer id) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Report report = reportService.getReportById(id);
        report.setProof("未通过");
        report.setReason("老师审核未通过");
        if(reportService.saveReport(report))
        {
            out.print("success");
        }else
        {
            out.print("failure");
        }
        out.flush();
    }

    @RequestMapping("/getreportbyall")
    public void getreportbyall(HttpServletResponse response, HttpServletRequest request,
                               @RequestParam("project_name")String project_name,
                               @RequestParam("user_name")String user_name,
                               @RequestParam("project_type")String project_type,
                               @RequestParam("project_credit")String project_credit) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Report report = new Report();
        Project project = new Project();
        project.setType(project_type);
        project.setProject_name(project_name);
        //多条件查询
        Specification<Report> specification = new Specification<Report>() {
            @Override
            public Predicate toPredicate(Root<Report> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                list.add(criteriaBuilder.equal(root.get("stu_username"),user_name));
                list.add(criteriaBuilder.equal(root.get("credit"),project_credit));
                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };
        List<Report> list = reportRepository.findAll(specification);
        for (Report report1 :list)
        {
            if(report1.getProject().Myequals(project))
            {
                System.out.println(report1);
                String str = gson.toJson(report1);
                out.print(str);
                out.flush();
                return;
            }
        }


    }


    //得到除管理员外的所有用户
    @RequestMapping("/getAllUser")
    public void getAllUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        List<User> list = userService.getAllStu();
        if(list != null)
        {
            map.put("user",list);
            String str = gson.toJson(map);
            out.print(str);
        }else
        {
            out.print("");
        }
        out.flush();
    }

    //得到全部的报告
    @RequestMapping("/getAllReport")
    public void getAllReport(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        List<Report> list = reportService.getAllReport();
        for (Report report:list)
        {
            map.put("report",list);
            String str = gson.toJson(map);
            out.print(str);
        }
        if(list != null)
        {
            out.print("");
        }
        out.flush();
    }

    @RequestMapping("/finduserbyid")
    public void finduserbyid(HttpServletResponse response,HttpServletRequest request,@RequestParam("id")String id) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        User user = userService.getUserById(Integer.parseInt(id));
        if(user != null)
        {
            String str = gson.toJson(user);
            out.print(str);
        }else
        {
            out.print("");
        }
        out.flush();
    }

    @RequestMapping("/saveuser")
    public void saveuser(HttpServletRequest request,HttpServletResponse response,
                         @RequestParam("username")String username,
                         @RequestParam("nickname")String nickanme,
                         @RequestParam("deparment")String deparment,
                         @RequestParam("score")String score,
                         @RequestParam("grade")String grade,
                         @RequestParam("id")String id) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        User user = userService.getUserById(Integer.parseInt(id));
        user.setUsername(username);
        user.setNickname(nickanme);
        user.setDeparment(deparment);
        user.setScore(Integer.parseInt(score));
        user.setGrade(Integer.parseInt(grade));
        user.setId(Integer.parseInt(id));

        userRepository.save(user);
        out.print("success");
        out.flush();
    }
    @RequestMapping("/saveteacher")
    public void saveteacher(HttpServletResponse response,HttpServletRequest request,
                            @RequestParam("username")String username,
                            @RequestParam("nickname")String nickname,
                            @RequestParam("id")String id) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        User user = userService.getUserById(Integer.parseInt(id));
        user.setUsername(username);
        userRepository.save(user);
        out.print("success");
        out.flush();
    }

    @RequestMapping("/savereport")
    public void savereport(HttpServletResponse response,HttpServletRequest request,
                            @RequestParam("username")String username,
                            @RequestParam("nickname")String nickname,
                            @RequestParam("id")String id,
                           @RequestParam("project_name")String project_name,
                           @RequestParam("credit")String credit,
                           @RequestParam("type")String type,
                           @RequestParam("description")String description) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Report report = reportService.getReportById(Integer.parseInt(id));
        if(report != null)
        {
            report.setStu_username(username);
            report.setStu_nickname(nickname);
            report.getProject().setProject_name(project_name);
            report.setCredit(Integer.parseInt(credit));
            report.getProject().setType(type);
            report.getProject().setDescription(description);
            reportRepository.save(report);
            out.print("success");
        }else
        {
            out.print("");
        }
        out.flush();
    }

    @RequestMapping("/findUserlike")
    public void findUserlike(HttpServletRequest request,HttpServletResponse response,@RequestParam("username")String usenrame) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Map<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        List<User> list = userRepository.findByUsernameLike(usenrame+"%");
        if(list != null)
        {
            map.put("user",list);
            String str = gson.toJson(map);
            System.out.println(str);
            out.print(str);
        }else
        {
            out.print("");
        }
        out.flush();
    }

    @RequestMapping("/findreportbyid")
    public void findreportbyid(HttpServletResponse response,HttpServletRequest request,@RequestParam("id")String id) throws IOException {
        response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Report report = reportService.getReportById(Integer.parseInt(id));
        Gson gson = new Gson();
        if(report != null)
        {
            String str = gson.toJson(report);
            out.print(str);
        }
        else
        {
            out.print("");
        }
        out.flush();
    }
}
