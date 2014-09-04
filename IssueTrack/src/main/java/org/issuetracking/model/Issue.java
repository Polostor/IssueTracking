package org.issuetracking.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "issue")
@NamedQueries({
    @NamedQuery(name = "Issue.findAll", query = "SELECT i FROM Issue i"),
    @NamedQuery(name = "Issue.findByIdissue", query = "SELECT i FROM Issue i WHERE i.idissue = :idissue"),
    @NamedQuery(name = "Issue.findByDesc", query = "SELECT i FROM Issue i WHERE i.description = :desc"),
    @NamedQuery(name = "Issue.findByStatus", query = "SELECT i FROM Issue i WHERE i.status = :status"),
    @NamedQuery(name = "Issue.findByPriority", query = "SELECT i FROM Issue i WHERE i.priority = :priority"),
    @NamedQuery(name = "Issue.findByIssuedate", query = "SELECT i FROM Issue i WHERE i.issuedate = :issuedate")})
public class Issue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idissue")
    private Integer idissue;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @Column(name = "priority")
    private int priority;
    @Column(name = "issuedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issuedate;
    @JoinColumn(name = "assignee", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User assignee;
    @JoinColumn(name = "project", referencedColumnName = "idproject")
    @ManyToOne(optional = false)
    private Project project;
    @JoinColumn(name = "reporter", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User reporter;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "issue")
    private List<Comment> commentList;

    public Issue() {
    }

    public Issue(int status, int priority) {
        this.status = status;
        this.priority = priority;
    }

    public Integer getIdissue() {
        return idissue;
    }

    public void setIdissue(Integer idissue) {
        this.idissue = idissue;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Date issuedate) {
        this.issuedate = issuedate;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    @XmlTransient
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idissue != null ? idissue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Issue)) {
            return false;
        }
        Issue other = (Issue) object;
        if ((this.idissue == null && other.idissue != null) || (this.idissue != null && !this.idissue.equals(other.idissue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "issuetracking.org.datalayer.Issue[ idissue=" + idissue + " ]";
    }

}
