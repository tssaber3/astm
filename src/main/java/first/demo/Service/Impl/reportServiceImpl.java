package first.demo.Service.Impl;

import first.demo.Dao.ReportRepository;
import first.demo.Mapper.ReportMapper;
import first.demo.Pojo.Project;
import first.demo.Pojo.Report;
import first.demo.Service.ReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class reportServiceImpl implements ReportService {
    @Resource
    private ReportRepository reportRepository;

    @Resource
    private ReportMapper reportMapper;

    @Override
    public boolean addReport(Report report) {
        Report report1 = reportRepository.save(report);
        if(report1 != null)
        {
            return true;
        }else
        {
            return false;
        }
    }

//    获取待审核的全部项目
    @Override
    public List<Report> getReport() {
        List<Report> list = reportRepository.getReportsByProof("待审核");
        if(list != null)
        {
            return list;
        }else
        {
            return null;
        }

    }

    @Override
    public Report getReportById(int id) {
        Report report = reportRepository.getReportsById(id);
        if(report != null)
        {
            return report;
        }else
        {
            return null;
        }
    }

    @Override
    public boolean saveReport(Report report) {
        Report report1 = reportRepository.save(report);
        if(report1 != null)
        {
            return true;
        }else
        {
            return false;
        }
    }

    @Override
    public List<Report> getAllReport() {
        List<Report> list = reportRepository.findAll();
        if(list != null)
        {
            return list;
        }else
        {
            return null;
        }
    }


}
