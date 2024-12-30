// Функция abs() гарантированно возвращает число, которое всегда больше или равно нулю и соответствует определению модуля.
// Функция max() всегда возвращает число, которое: больше или равно обоим аргументам, совпадает с одним из них.
// Тройка Хоара для maxAbs(): {true} maxAbs(x,y) {maxAbs(x,y)=max(∣x∣,∣y∣)}
// Доказательство корректности: 
// Вызов abs(x) и abs(y) возвращает ∣x∣ и ∣y∣.
// Вызов max(|x|, |y|) возвращает максимум из ∣x∣ и ∣y∣. 
// Соответственно maxAbs(x,y) вернет max(∣x∣,∣y∣).
//  Ч.т.д.


public class HoareTriplesDemo {
    
    public static int max(int a, int b) {
        return (a >= b) ? a : b;
    }

    public static int abs(int x) {
        return (x >= 0) ? x : -x;
    }

    public static int maxAbs(int x, int y) {
        return max(abs(x), abs(y));
    }

    public static void main(String[] args) {
        int x = -5, y = 3;
        System.out.println("Maximum absolute value: " + maxAbs(x, y)); // Ожидается 5
    }
}