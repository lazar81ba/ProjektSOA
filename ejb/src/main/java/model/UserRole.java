package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_roles")
@NamedQueries({
        @NamedQuery(name = "UserRole.find",
                query = "SELECT u FROM UserRole u WHERE u.login=:login")
})
public class UserRole {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name="role")
    @NotNull
    private String role;

    public UserRole() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
