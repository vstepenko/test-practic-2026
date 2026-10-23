package ua.edu.duan.test_practic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.duan.test_practic.entity.UserEntity;
import ua.edu.duan.test_practic.repository.UserRepository;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void addUser (String login, String name, String email) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(login);
        userEntity.setUserName(name);
        userEntity.setEmail(email);
        userRepository.save(userEntity);
    }
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUser(String login){
        return userRepository.findById(login).orElseThrow(
                () -> new RuntimeException("user not found")
        );
    }
}
