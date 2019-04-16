package first.demo.Dao;

import first.demo.Pojo.Project;
import first.demo.Pojo.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Integer> , JpaSpecificationExecutor<Report> {
    List<Report> getReportsByProof(String Proof);

    Report getReportsById(int id);
}
