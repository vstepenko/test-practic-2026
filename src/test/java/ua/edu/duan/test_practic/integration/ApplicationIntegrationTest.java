package ua.edu.duan.test_practic.integration;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ua.edu.duan.test_practic.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration")
// @TestPropertySource має вищий пріоритет, ніж змінні оточення (SPRING_DATASOURCE_URL тощо),
// тому тест гарантовано йде в H2, навіть якщо на машині налаштований PostgreSQL.
@TestPropertySource(locations = "classpath:application-integration.properties")
class ApplicationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void cleanDatabase() {
        userRepository.deleteAll();
    }
}


