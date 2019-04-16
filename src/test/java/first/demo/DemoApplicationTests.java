package first.demo;

import first.demo.Dao.UserRepository;
import first.demo.Pojo.Project;
import first.demo.Pojo.Report;
import first.demo.Pojo.Role;
import first.demo.Pojo.User;
import first.demo.Service.ReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Resource
    private UserRepository userRepository;

    @Resource
    private ReportService reportService;
    @Test
    public void contextLoads() {
        User user1 = new User();
        user1.setUsername("1274243927");
        user1.setNickname("霞之丘诗羽");
        user1.setPassword("123456");
        user1.setDeparment("计算机");
        user1.setGrade(1);//年级
        user1.setScore(0);//学分

        User user2 = new User();
        user2.setUsername("159922720907");
        user2.setNickname("英梨梨");
        user2.setPassword("123456");
        user2.setDeparment("计算机");
        user2.setGrade(1);//年级
        user2.setScore(0);//学分

        User user3 = new User();
        user3.setUsername("17347898501");
        user3.setNickname("加藤惠");
        user3.setPassword("123456");
        user3.setDeparment("计算机");
        user3.setGrade(1);//年级
        user3.setScore(0);//学分

        Role role1 = new Role();
        Role role2 = new Role();
        Role role3 = new Role();

        role1.setName("管理员");
        role2.setName("老师");
        role3.setName("学生");

        user1.setRole(role1);
        user2.setRole(role2);
        user3.setRole(role3);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

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

}
