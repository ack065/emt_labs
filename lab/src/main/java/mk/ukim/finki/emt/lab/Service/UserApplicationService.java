package mk.ukim.finki.emt.lab.Service;

import mk.ukim.finki.emt.lab.Model.dto.create.CreateUserDTO;
import mk.ukim.finki.emt.lab.Model.dto.display.DisplayUserDTO;
import mk.ukim.finki.emt.lab.Model.projections.UserProjection;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDTO> register(CreateUserDTO createUserDto);
    //Optional<LoginResponseDto> login(LoginUserDto loginUserDto);
    Optional<DisplayUserDTO> findByUsername(String username);
    List<UserProjection> getAllUserNames();
}
