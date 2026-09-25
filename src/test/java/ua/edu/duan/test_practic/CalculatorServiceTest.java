package ua.edu.duan.test_practic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorServiceTest {

    @Test
    void testSum(){
        CalculatorService culculatorservise = new CalculatorService();
        int result = culculatorservise.add(3,5);
        assertEquals(8,result);

    }
}
