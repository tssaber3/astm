package first.demo.pojo;

import javax.persistence.*;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "stu_username")
    private String stu_username;

    @Column(name = "stu_nickname")
    private String stu_nickname;

    //学分
    @Column(name = "credit")
    private int credit;

    //通过 代审核 为通过 的原因
    @Column(name = "reason")
    private String reason;

    //状态
    @Column(name = "proof")
    private String proof;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", stu_username='" + stu_username + '\'' +
                ", stu_nickname='" + stu_nickname + '\'' +
                ", credit=" + credit +
                ", reason='" + reason + '\'' +
                ", proof='" + proof + '\'' +
                ", project=" + project +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStu_username() {
        return stu_username;
    }

    public void setStu_username(String stu_username) {
        this.stu_username = stu_username;
    }

    public String getStu_nickname() {
        return stu_nickname;
    }

    public void setStu_nickname(String stu_nickname) {
        this.stu_nickname = stu_nickname;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
