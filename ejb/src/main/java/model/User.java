package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.find",
                query = "SELECT u FROM User u WHERE u.login=:login")
})
public class User implements Serializable{

    @Id
    @Column(name = "login")
    private String login;

    @Column(name="passwd")
    @NotNull
    private String pass;

    @OneToMany(cascade={CascadeType.ALL}, orphanRemoval = true,
            fetch=FetchType.EAGER, mappedBy = "user")
    private Collection<Category> categories;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    protected Collection<Category> getCategories() {
        return categories;
    }

    protected void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }
}
