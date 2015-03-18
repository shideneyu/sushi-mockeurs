package fr.esgi.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserWithBuilder {

    @Id
    private long id;
    private String name;
    private String login;
    private String password;


    private UserWithBuilder(UserBuilder userBuilder) {
        setId(userBuilder.id);
        setName(userBuilder.name);
        setLogin(userBuilder.login);
        setPassword(userBuilder.password);
    }

    public static UserBuilder newUser(long id, String login) {
        return new UserBuilder(id, login);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static final class UserBuilder {
        private final long id;
        private final String login;
        private String name;
        private String password;

        public UserBuilder(long id, String login) {
            this.id = id;
            this.login = login;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserWithBuilder build() {
            return new UserWithBuilder(this);
        }
    }
}
