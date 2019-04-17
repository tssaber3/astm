package first.demo.Service;

import first.demo.Pojo.Project;
import first.demo.Pojo.Report;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/reportservlet")
public interface ReportService {
    boolean addReport(Report report);

    List<Report> getReport();

    Report getReportById(int id);

    boolean saveReport(Report report);

    List<Report> getAllReport();



}
