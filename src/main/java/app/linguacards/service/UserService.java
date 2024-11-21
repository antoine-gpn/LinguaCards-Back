package app.linguacards.service;

import app.linguacards.model.User;
import app.linguacards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(String username, String password){
        User user = this.userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
