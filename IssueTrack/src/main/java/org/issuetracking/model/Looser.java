package org.issuetracking.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Looser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 4, max = 16)
    private String nick;

    @NotNull
    @Size(min = 4, max = 20)
    private String pass;

    public Looser() {
    }

    public Looser(String nick, String pass) {
        this.nick = nick;
        this.pass = pass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idlooser) {
        this.id = idlooser;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Looser)) {
            return false;
        }
        Looser other = (Looser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "issuetracking.org.datalayer.Looser[ idlooser=" + id + " nick=" + nick + " pass=" + pass + " ]";
    }

}
