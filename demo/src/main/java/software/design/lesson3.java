package software.design;
public class lesson3 {
    public static void main(String[] args) {
        AverageCalculator ac = new AverageCalculator();

        int [] xx = {1,2,3,100};
        double val = ac.calculateAverage(xx);
        System.err.println(val);
    }
    
}
