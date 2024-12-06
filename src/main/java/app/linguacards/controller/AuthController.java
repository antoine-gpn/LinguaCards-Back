package app.linguacards.controller;

import app.linguacards.model.User;
import app.linguacards.repository.UserRepository;
import app.linguacards.service.UserService;
import app.linguacards.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        User user = userService.login(username, password);
        return jwtUtils.generateToken(username, user.getId());
    }

    @PostMapping("/register")
    public String register(@RequestBody User newUser) {
        String hashedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);
        String username = newUser.getUsername();
        User user = this.userRepository.save(newUser);
        return jwtUtils.generateToken(username, user.getId());
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "Logged out successfully";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> logout(@PathVariable String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable String id){
        return this.userRepository.findById(id);
    }
}
