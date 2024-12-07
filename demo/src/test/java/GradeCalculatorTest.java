import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class GradeCalculatorTest {
    private GradeCalculator calculator = new GradeCalculator();

    @Test
    void testNormalGradeCalculation() {
        // Тест обычного сценария со стандартным списком оценок
        List<Integer> grades = Arrays.asList(5, 7, 8, 6, 9);
        assertEquals(7.0, calculator.calculateAverage(grades), 0.001);
    }

    @Test
    void testEmptyListReturnsZero() {
        // Тест на пустой список - должен возвращать 0
        List<Integer> emptyGrades = Collections.emptyList();
        assertEquals(0.0, calculator.calculateAverage(emptyGrades), 0.001);
    }

    @Test
    void testBoundaryGrades() {
        // Тест граничных значений оценок (0 и 10)
        List<Integer> boundaryGrades = Arrays.asList(0, 10, 5, 5);
        assertEquals(5.0, calculator.calculateAverage(boundaryGrades), 0.001);
    }

    @Test
    void testNullListThrowsException() {
        // Тест на выброс исключения при null списке
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAverage(null);
        });
    }

    @Test
    void testInvalidGradeThrowsException() {
        // Тест на выброс исключения при некорректной оценке
        List<Integer> invalidGrades = Arrays.asList(5, 11, 7);
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAverage(invalidGrades);
        });
    }
}