package org.issuetracking.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Looser")
@Table(name = "Looser")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 4, max = 16)
    @Column(unique = true)
    private String nick;

    @NotNull
    @Size(min = 4, max = 20)
    private String pass;

    @NotNull
    @Size(min = 8, max = 30)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role roles;

    public User() {
        this.roles = Role.Users;
    }

    public User(String nick, String pass) {
        this.nick = nick;
        pass = pass;
        System.out.println(pass);
        this.pass = pass;
        this.roles = Role.Users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long iduser) {
        this.id = iduser;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role role) {
        this.roles = role;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "issuetracking.org.datalayer.User[ iduser=" + id + " nick=" + nick + " ]";
    }

}
