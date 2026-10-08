package ua.edu.duan.test_practic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorServiceTest {

    @Test
    void testSum(){
        CalculatorService culculatorservise = new CalculatorService();
        int result = culculatorservise.add(3,5);
        assertEquals(8,result);

    }

    @Test
    void testSubtract(){
        CalculatorService culculatorservise = new CalculatorService();
        int result = culculatorservise.cancel(5,3);
        assertEquals(2,result);
    }

    @Test
    void testMultiply(){
        CalculatorService culculatorservise = new CalculatorService();
        int result = culculatorservise.multiply(5,3);
        assertEquals(15,result);
    }

    @Test
    void testDivide(){
        CalculatorService culculatorservise = new CalculatorService();
        int result = culculatorservise.divide(15,3);
        assertEquals(5,result);
    }

    @ParameterizedTest(name = "{index} => expected={0}, a={1}, b={2}")
    @CsvSource({
            "7, 5, 2",
            "8, 3, 5",
            "11, 4, 7"
    })
    void testAddCsvSource(int expected, int a, int b) {
        CalculatorService calculatorService = new CalculatorService();
        int result = calculatorService.add(a, b);
        assertEquals(expected, result);
    }

}

