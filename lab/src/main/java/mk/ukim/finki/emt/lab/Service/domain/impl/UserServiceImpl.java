package mk.ukim.finki.emt.lab.Service.domain.impl;

import mk.ukim.finki.emt.lab.Model.domain.User;
import mk.ukim.finki.emt.lab.Model.enumerations.Role;
import mk.ukim.finki.emt.lab.Model.projections.UserProjection;
import mk.ukim.finki.emt.lab.Repository.UserRepository;
import mk.ukim.finki.emt.lab.Service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new RuntimeException();

        if (!password.equals(repeatPassword)) throw new RuntimeException();

        if (userRepository.findById(username).isPresent())
            throw new RuntimeException(username);

        User user = new User(username, password, name, role);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new RuntimeException();
        }
        User user = findByUsername(username);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findById(username).orElseThrow();
    }

    @Override
    public List<UserProjection> getAllUserNames() {
        return userRepository.findAllProjectedBy();
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findById(username).orElseThrow();
//    }


}