package ua.edu.duan.test_practic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import ua.edu.duan.test_practic.entity.UserEntity;
import ua.edu.duan.test_practic.service.UserService;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;
    @Test
    void createUserReturns200AndCallsService() throws Exception {

        String body = """
                {
                    "login": "test_user",
                    "userName": "Anastasia",
                    "email": "test@example.com"
                }
                """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());

        verify(userService).addUser(
                "test_user",
                "Anastasia",
                "test@example.com"
        );
    }
    @Test
    void getAllUsersReturns200() throws Exception {

        UserEntity user = new UserEntity();
        user.setLogin("test_user");
        user.setUserName("Anastasia");
        user.setEmail("test@example.com");

        when(userService.getAllUsers())
                .thenReturn(List.of(user));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].login").value("test_user"))
                .andExpect(jsonPath("$[0].userName").value("Anastasia"))
                .andExpect(jsonPath("$[0].email").value("test@example.com"));

        verify(userService).getAllUsers();
    }
    @Test
    void getUsersByNameReturns200() throws Exception {

        UserEntity user = new UserEntity();
        user.setLogin("test_user");
        user.setUserName("Anastasia");
        user.setEmail("test@example.com");

        when(userService.getUsersByName("Anastasia"))
                .thenReturn(List.of(user));

        mockMvc.perform(get("/users")
                        .param("name", "Anastasia"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].login").value("test_user"))
                .andExpect(jsonPath("$[0].userName").value("Anastasia"))
                .andExpect(jsonPath("$[0].email").value("test@example.com"));

        verify(userService).getUsersByName("Anastasia");
    }
}