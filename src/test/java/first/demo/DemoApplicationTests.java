package first.demo;

import first.demo.dao.RoleRepository;
import first.demo.dao.UserRepository;
import first.demo.mapper.ReportMapper;
import first.demo.mapper.UserMapper;
import first.demo.pojo.Project;
import first.demo.pojo.Report;
import first.demo.pojo.Role;
import first.demo.pojo.User;
import first.demo.service.ReportService;
import first.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Resource
    private UserRepository userRepository;

    @Resource
    private ReportService reportService;

    @Resource
    private RoleRepository roleRepository;
    @Test
    public void contextLoads() {
        //表设计错误 一对多
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("admin");


        User user2 = new User();
        user2.setUsername("teacher1");
        user2.setNickname("teacher1");
        user2.setPassword("123456");
        user2.setType("学科竞赛");

        User user3 = new User();
        user3.setUsername("teacher2");
        user3.setNickname("teacher2");
        user3.setPassword("123456");
        user3.setType("科技创新活动");

        User user4 = new User();
        user4.setUsername("teacher3");
        user4.setNickname("teacher3");
        user4.setPassword("123456");
        user4.setType("学术论文");

        User user5 = new User();
        user5.setUsername("teacher4");
        user5.setNickname("teacher4");
        user5.setPassword("123456");
        user5.setType("学术活动");

        User user6 = new User();
        user6.setUsername("teacher5");
        user6.setNickname("teacher5");
        user6.setPassword("123456");
        user6.setType("文学创作.艺术作品或发明创造");


        User user7 = new User();
        user7.setUsername("teacher6");
        user7.setNickname("teacher6");
        user7.setPassword("123456");
        user7.setType("海外实践");

        User user8 = new User();
        user8.setUsername("teacher7");
        user8.setNickname("teacher7");
        user8.setPassword("123456");
        user8.setType("大学生创业");

        User user9 = new User();
        user9.setUsername("teacher8");
        user9.setNickname("teacher8");
        user9.setPassword("123456");
        user9.setType("专家讲座");

        User user10 = new User();
        user10.setUsername("teacher9");
        user10.setNickname("teacher9");
        user10.setPassword("123456");
        user10.setType("专业证书考试");

        User user11 = new User();
        user11.setUsername("teacher10");
        user11.setNickname("teacher10");
        user11.setPassword("123456");
        user11.setType("社团活动");


        User user12 = new User();
        user12.setUsername("teacher11");
        user12.setNickname("teacher11");
        user12.setPassword("123456");
        user12.setType("社会实践类/志愿服务类活动");

        User user13 = new User();
        user13.setUsername("teacher12");
        user13.setNickname("teacher12");
        user13.setPassword("123456");
        user13.setType("社会服务工作");

        User user14 = new User();
        user14.setUsername("teacher13");
        user14.setNickname("teacher13");
        user14.setPassword("123456");
        user14.setType("文体竞赛类活动");

        User user15 = new User();
        user15.setUsername("teacher14");
        user15.setNickname("teacher14");
        user15.setPassword("123456");
        user15.setType("大学生网络安全教育");

        User user16 = new User();
        user16.setUsername("学生1");
        user16.setNickname("张婷婷");
        user16.setDeparment("计算机");
        user16.setGrade(1);
        user16.setScore(0);
        user16.setPassword("123456");

        Role role1 = new Role();
        Role role2 = new Role();
        Role role3 = new Role();

        role1.setName("管理员");
        role2.setName("老师");
        role3.setName("学生");

        user1.setRole(role1);
        role1.getUsers().add(user1);
        user2.setRole(role2);
        user3.setRole(role2);
        user4.setRole(role2);
        user5.setRole(role2);
        user6.setRole(role2);
        user7.setRole(role2);
        user8.setRole(role2);
        user9.setRole(role2);
        user10.setRole(role2);
        user11.setRole(role2);
        user12.setRole(role2);
        user13.setRole(role2);
        user14.setRole(role2);
        user15.setRole(role2);
        role2.getUsers().add(user2);
        role2.getUsers().add(user3);
        role2.getUsers().add(user4);
        role2.getUsers().add(user5);
        role2.getUsers().add(user6);
        role2.getUsers().add(user7);
        role2.getUsers().add(user8);
        role2.getUsers().add(user9);
        role2.getUsers().add(user10);
        role2.getUsers().add(user11);
        role2.getUsers().add(user12);
        role2.getUsers().add(user13);
        role2.getUsers().add(user14);
        role2.getUsers().add(user15);
        user16.setRole(role3);
        role3.getUsers().add(user16);

//
        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
        userRepository.save(user8);
        userRepository.save(user9);
        userRepository.save(user10);
        userRepository.save(user11);
        userRepository.save(user12);
        userRepository.save(user13);
        userRepository.save(user14);
        userRepository.save(user15);
        userRepository.save(user16);




    }

    @Test
    public void  text1()
    {


        Project project = new Project();
        Report report = new Report();
        project.setDescription("第一次");
        project.setType("社团活动");
        project.setProject_name("我的涛涛");


        report.setCredit(Integer.parseInt("10"));
        report.setProof("待审核");
        report.setReason("等待教师审核");
        report.setStu_username("17347898501");
        report.setStu_nickname("加藤惠");
        report.setProject(project);

        reportService.addReport(report);
    }

    @Test
    public void  ssss()
    {
        System.out.println(":ssss");
    }

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Test
    public void text3()
    {
        List<Report> list = reportService.getAllReport();
        for (Report report:list){
            System.out.println(report);
        }
    }

    @Test
    public void text4(){

        User user = userRepository.findById(16).get();
        Role role = user.getRole();
        System.out.println(role.getUsers());
    }

}
