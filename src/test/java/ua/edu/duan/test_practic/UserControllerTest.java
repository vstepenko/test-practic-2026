package ua.edu.duan.test_practic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ua.edu.duan.test_practic.entity.UserEntity;
import ua.edu.duan.test_practic.service.UserService;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private UserEntity sampleUser() {
        return new UserEntity("test_user", "Anastasia", "test@example.com");
    }

    @Test
    void createUserReturns200AndCallsService() throws Exception {
        mockMvc.perform(post("/users")
                        .param("login", "test_user")
                        .param("name", "Anastasia")
                        .param("email", "test@example.com"))
                .andExpect(status().isOk());

        verify(userService).addUser("test_user", "Anastasia", "test@example.com");
    }

    @Test
    void createUserWithoutParametersReturns400() throws Exception {
        mockMvc.perform(post("/users"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(userService);
    }

    @Test
    void getUserReturns200() throws Exception {
        when(userService.getUser("test_user")).thenReturn(sampleUser());

        mockMvc.perform(get("/users").param("login", "test_user"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.login").value("test_user"))
                .andExpect(jsonPath("$.userName").value("Anastasia"));

        verify(userService).getUser("test_user");
    }

    @Test
    void getAllUsersReturns200() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(sampleUser()));

        mockMvc.perform(get("/users/All"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].login").value("test_user"))
                .andExpect(jsonPath("$[0].userName").value("Anastasia"));

        verify(userService).getAllUsers();
    }
}