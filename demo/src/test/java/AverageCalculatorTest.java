
import org.junit.jupiter.api.Test;

import software.design.AverageCalculator;

import static org.junit.jupiter.api.Assertions.*;

public class AverageCalculatorTest {

    @Test
    public void testCalculateAverage_ValidInput() {
        AverageCalculator calculator = new AverageCalculator();
        int[] numbers = {1, 2, 3, 4, 5};
        double result = calculator.calculateAverage(numbers);
        assertEquals(3.0, result, 0.0001, "Среднее значение рассчитано неправильно");
    }

    @Test
    public void testCalculateAverage_SingleElement() {
        AverageCalculator calculator = new AverageCalculator();
        int[] numbers = {42};
        double result = calculator.calculateAverage(numbers);
        assertEquals(42.0, result, 0.0001, "Среднее значение для одного элемента рассчитано неправильно");
    }

    @Test
    public void testCalculateAverage_NegativeNumbers() {
        AverageCalculator calculator = new AverageCalculator();
        int[] numbers = {-1, -2, -3, -4, -5};
        double result = calculator.calculateAverage(numbers);
        assertEquals(-3.0, result, 0.0001, "Среднее значение для отрицательных чисел рассчитано неправильно");
    }

    @Test
    public void testCalculateAverage_MixedNumbers() {
        AverageCalculator calculator = new AverageCalculator();
        int[] numbers = {-2, 2, -2, 2};
        double result = calculator.calculateAverage(numbers);
        assertEquals(0.0, result, 0.0001, "Среднее значение для смешанных чисел рассчитано неправильно");
    }

    @Test
    public void testCalculateAverage_EmptyArrayThrowsException() {
        AverageCalculator calculator = new AverageCalculator();
        int[] numbers = {};
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateAverage(numbers), "Пустой массив не вызвал исключение");
    }

    @Test
    public void testCalculateAverage_NullArrayThrowsException() {
        AverageCalculator calculator = new AverageCalculator();
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateAverage(null), "null массив не вызвал исключение");
    }
}
