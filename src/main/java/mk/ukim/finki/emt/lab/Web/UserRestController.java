package mk.ukim.finki.emt.lab.Web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.Model.dto.create.CreateUserDTO;
import mk.ukim.finki.emt.lab.Model.dto.display.DisplayUserDTO;
import mk.ukim.finki.emt.lab.Model.projections.UserProjection;
import mk.ukim.finki.emt.lab.Service.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "User API", description = "Endpoints for user authentication and registration") // Swagger tag
public class UserRestController {
    private final UserApplicationService userApplicationService;

    public UserRestController(UserApplicationService userApplicationService) {

        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDTO> register(@RequestBody CreateUserDTO createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
//    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
//        LoginResponseDto body = userApplicationService.login(loginUserDto).orElseThrow();
//        return ResponseEntity.ok(body);
//    }


//    @GetMapping("/role")
//    public ResponseEntity<?> getRole(){
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        return ResponseEntity.ok(userDetails);
//    }

    @GetMapping("/names")
    public List<UserProjection> getAllUserNames() {
        return userApplicationService.getAllUserNames();
    }
}