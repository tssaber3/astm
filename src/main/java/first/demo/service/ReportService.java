package first.demo.service;

import first.demo.pojo.Report;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/reportservlet")
public interface ReportService {
    boolean addReport(Report report);

    List<Report> getReport();

    Report getReportById(int id);

    boolean saveReport(Report report);

    List<Report> getAllReport();

    List<Report> getReportByType(String type);

    List<Report> getReportByname(String username);

    void updReport(Report report);

}
