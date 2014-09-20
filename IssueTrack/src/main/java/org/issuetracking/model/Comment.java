package org.issuetracking.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @NotNull
    @Size(min = 10, max = 200)
    private String comment;
    
    @Past
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentdate;
    /*
    @JoinColumn(name = "author", referencedColumnName = "idlooser")
    @ManyToOne(optional = false)
    private Looser author;
    
    @JoinColumn(name = "issue", referencedColumnName = "idissue")
    @ManyToOne(optional = false)
    private Issue issue;    */

    @PrePersist
    private void onCreate() {
        commentdate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Date commentdate) {
        this.commentdate = commentdate;
    }

    /*public Looser getAuthor() {
        return author;
    }

    public void setAuthor(Looser author) {
        this.author = author;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "issuetracking.org.datalayer.Comment[ idcomment=" + id + " comment=" + comment + " date=" + commentdate + " ]";
    }

}
