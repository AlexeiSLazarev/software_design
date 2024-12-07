public class AverageCalculator {
    public double calculateAverage(int[] numbers){

        // if (numbers == null || numbers.length == 0) {
        //     throw new IllegalArgumentException("Array must not be null or empty");
        // }

        double average_sum = 0;
        for (int x : numbers) {
            average_sum += x;
        }

        return average_sum/numbers.length;
    }
}


