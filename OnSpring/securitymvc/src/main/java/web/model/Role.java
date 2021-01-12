package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Transient
    @ManyToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();


    public Role () {
    }


    public Role (String role){
        if (role.equals("ADMIN")){
            this.id = 1L;
        }
        else if (role.equals("USER")){
            this.id = 2L;
        }
        this.name = role;
    }

    public long getId () {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Set<User> getUsers () {
        return users;
    }

    public void setUsers (Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString () {
        return name;
    }

    @Override
    public String getAuthority () {
        return name;
    }
}
