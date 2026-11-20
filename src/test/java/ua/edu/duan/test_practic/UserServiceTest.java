package ua.edu.duan.test_practic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ua.edu.duan.test_practic.entity.UserEntity;
import ua.edu.duan.test_practic.repository.UserRepository;
import ua.edu.duan.test_practic.service.UserService;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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

    @Test
    public void testGetUsersByName() {
        UserEntity mockUserEntity = new UserEntity("Anastasia", "test", "ana@test.com");
        Mockito.when(userRepository.findByUserName("test"))
                .thenReturn(List.of(mockUserEntity));
        List<UserEntity> realUsers = userService.getUsersByName("test");
        assertEquals(1, realUsers.size());
        assertEquals("test", realUsers.get(0).getUserName());
        Mockito.verify(userRepository, Mockito.times(1))
                .findByUserName("test");
    }
}
