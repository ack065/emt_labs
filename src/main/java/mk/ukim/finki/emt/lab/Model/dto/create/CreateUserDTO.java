package mk.ukim.finki.emt.lab.Model.dto.create;


import mk.ukim.finki.emt.lab.Model.enumerations.Role;
import org.springframework.security.core.userdetails.User;

public record CreateUserDTO(
        String username,
        String password,
        String repeatPassword,
        String name,
        Role role
) {

    public User toUser() {
        return new User(username, password, name, role);
    }
}