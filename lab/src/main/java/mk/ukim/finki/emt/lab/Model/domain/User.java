package mk.ukim.finki.emt.lab.Model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt.lab.Model.enumerations.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    private String username;

    @JsonIgnore
    private String password;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToMany
    private List<Booking> temporaryBookings;

    @ManyToMany
    private List<Booking> bookings;


    public User(String username, String password, String name, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        temporaryBookings = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = Role.ROLE_USER;
        temporaryBookings = new ArrayList<>();
        bookings = new ArrayList<>();
    }
    public User() {
    }
}