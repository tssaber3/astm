package first.demo.Mapper;

import first.demo.Pojo.Report;
import first.demo.Pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportMapper {
    @Select("select * from report")
    List<Report> getAllReport();
}
