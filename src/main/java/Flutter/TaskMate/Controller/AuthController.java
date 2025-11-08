package Flutter.TaskMate.Controller;

import Flutter.TaskMate.Entity.UserEntity;
import Flutter.TaskMate.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public UserEntity signup(@RequestBody UserEntity user) {
        return authService.signup(user.getUsername(), user.getEmail(), user.getPassword());
    }
    
    @PutMapping("/update/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity updatedUser) {
        return authService.updateUser(id, updatedUser.getUsername(), updatedUser.getEmail());
    }

    @PostMapping("/login")
    public UserEntity login(@RequestBody UserEntity user) {
        return authService.login(user.getEmail(), user.getPassword());
    }
}
