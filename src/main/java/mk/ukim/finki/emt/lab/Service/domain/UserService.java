package mk.ukim.finki.emt.lab.Service.domain;

import mk.ukim.finki.emt.lab.Model.domain.User;
import mk.ukim.finki.emt.lab.Model.enumerations.Role;
import mk.ukim.finki.emt.lab.Model.projections.UserProjection;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    User register(String username, String password, String repeatPassword, String name, Role role);
    User login(String username, String password);
    User getAuthenticatedUser(String token);
    User findByUsername(String username);
    List<UserProjection> getAllUserNames();

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}