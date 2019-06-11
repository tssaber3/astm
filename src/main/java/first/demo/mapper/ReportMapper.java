package first.demo.mapper;

import first.demo.pojo.Project;
import first.demo.pojo.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReportMapper {
    //输出全部报告 且根据id倒叙输出 即使最新的输出到前面
    @Select("select * from report order by id desc")
    List<Report> getAllReport();

    @Select("select * from report where proof=#{proof}")
    List<Report> getReportByProof(String proof);

    @Select("select * from report where id=#{id}")
    Report getReportById(int id);

    @Select("select * from project " +
            "where id=" +
            "(select project_id from report " +
            "where id=#{id})")
    Project selProjectByReportId(int id);

    @Insert("insert into report " +
            "values(default,#{credit},#{proof},#{reason},#{stu_nickname},#{stu_username})")
    Report insReport(int credit,String proof,String reason,String stu_nickname,String stu_username);

    @Update("update report " +
            "set credit=#{credit},stu_nickname=#{stu_nickname},stu_username=#{stu_username} " +
            "where id=#{id}")
    void updReport(int credit,String stu_nickname,String stu_username,int id);

    @Update("update project " +
            "set description=#{description},project_name=#{project_name},type=#{type} " +
            "where id=#{id}")
    void updProject(String description,String project_name,String type,int id);
}
