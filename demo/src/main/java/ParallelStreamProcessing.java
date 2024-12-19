import java.util.Random;
import java.util.Arrays;

public class ParallelStreamProcessing {
    private static final int SIZE = 1000000;
    private static final int[] data = new int[SIZE];

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            data[i] = random.nextInt(100);
        }

        int sum = Arrays.stream(data).parallel().sum();
        System.out.println("Sum of all elements: " + sum);
    }
}