import java.util.List;


public class GradeCalculator {
    public double calculateAverage(List<Integer> grades) {

        if (grades == null) {
            throw new IllegalArgumentException("Список оценок не может быть null");
        }

        if (grades.isEmpty()) {
            return 0.0;
        }
        
        double average = 0.0;
        for (Integer grade : grades) {
            if (grade == null) {
                throw new IllegalArgumentException("Оценка не может быть null");
            }
            if (grade < 0 || grade > 10) {
                throw new IllegalArgumentException("Оценка должна быть от 0 до 10");
            }
            average += grade;
        }
        
        return average/grades.size();
    }
}