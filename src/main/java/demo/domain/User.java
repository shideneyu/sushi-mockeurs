package demo.domain;

import javax.persistence.*;

import static javax.persistence.EnumType.*;


/**
 * Created by Benoit on 18/03/2015.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false,unique = true)
    private  String login;
    @Column
    private String password;

    @Enumerated(STRING)
    private Role role;

    //@Column
    //@OneToMany
    //private UserConfiguration userConfiguration;

    public Long getId() {return id;}
    public String getLogin() {return login;}
    public String getPassword() {return password;}
    public Role getRole() {return role;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
