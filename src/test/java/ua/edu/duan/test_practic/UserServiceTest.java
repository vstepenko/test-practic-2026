package ua.edu.duan.test_practic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ua.edu.duan.test_practic.repository.UserRepository;
import ua.edu.duan.test_practic.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public  void testAddUser(){
        String login = "Anastasia";
        String name = "test";
        String email = "ana@test.com";

        userService.addUser(login,name,email);

        verify(userRepository, Mockito.times(1)).save(any());
    }


}
