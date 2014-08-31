package issuetracking.org.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "project")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findByIdproject", query = "SELECT p FROM Project p WHERE p.idproject = :idproject"),
    @NamedQuery(name = "Project.findByDesc", query = "SELECT p FROM Project p WHERE p.desc = :desc"),
    @NamedQuery(name = "Project.findByName", query = "SELECT p FROM Project p WHERE p.name = :name")})
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idproject")
    private Integer idproject;
    @Column(name = "desc")
    private String desc;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<Issue> issueList;

    public Project() {
    }

    public Project(Integer idproject) {
        this.idproject = idproject;
    }

    public Project(Integer idproject, String name) {
        this.idproject = idproject;
        this.name = name;
    }

    public Integer getIdproject() {
        return idproject;
    }

    public void setIdproject(Integer idproject) {
        this.idproject = idproject;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Issue> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<Issue> issueList) {
        this.issueList = issueList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproject != null ? idproject.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.idproject == null && other.idproject != null) || (this.idproject != null && !this.idproject.equals(other.idproject))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "issuetracking.org.datalayer.Project[ idproject=" + idproject + " ]";
    }
    
}