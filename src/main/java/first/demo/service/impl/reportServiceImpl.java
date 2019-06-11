package first.demo.service.impl;

import first.demo.dao.ReportRepository;
import first.demo.mapper.ReportMapper;
import first.demo.pojo.Project;
import first.demo.pojo.Report;
import first.demo.service.ReportService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
        List<Report> list = reportMapper.getReportByProof("待审核");

//        List<Report> list = reportRepository.getReportsByProof("待审核");
        if(list != null)
        {
            for (Report report:list){
                Project project = reportMapper.selProjectByReportId(report.getId());
                report.setProject(project);
            }
            return list;
        }else
        {
            return null;
        }

    }

    @Override
    public Report getReportById(int id) {
        Report report = reportMapper.getReportById(id);
//        Report report = reportRepository.getReportsById(id);
        if(report != null)
        {
            Project project = reportMapper.selProjectByReportId(id);
            report.setProject(project);
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
        List<Report> list = reportMapper.getAllReport();
        if(list != null)
        {
            for (Report report:list){
                Project project = reportMapper.selProjectByReportId(report.getId());
                report.setProject(project);
            }
            return list;
        }else
        {
            return null;
        }
    }

    @Override
    public List<Report> getReportByType(String type) {
        Specification<Report> specification = new Specification<Report>() {
            @Override
            public Predicate toPredicate(Root<Report> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                list.add(criteriaBuilder.equal(root.join("project").get("type"),type));
                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };
        List<Report> list = reportRepository.findAll(specification);
        if(list != null)
        {
            return list;
        }else
        {
            return null;
        }
    }

    @Override
    public List<Report> getReportByname(String username) {
        Specification<Report> specification = new Specification<Report>() {
            @Override
            public Predicate toPredicate(Root<Report> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                list.add(criteriaBuilder.equal(root.get("stu_username"),username));
                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };
        List<Report> list = reportRepository.findAll(specification);
        if(list != null)
        {
            return list;
        }else
        {
            return null;
        }
    }

    @Override
    public void updReport(Report report) {
        Project project = report.getProject();
        reportMapper.updReport(report.getCredit(),report.getStu_nickname(),report.getStu_username(),report.getId());
        reportMapper.updProject(project.getDescription(),project.getProject_name(),project.getType(),project.getId());
    }


}
