/*
Алгоритм Quicksort
Предусловия:
Массив arr не равен null, содержит минимум один элемент
Постусловие:
Алгоритм возвращает массив того же размера упорядоченный по возрастанию
*/

/*
Рекурсивная функция quicksort
Во время выполнения алгоритма, сохраняется следующий инвариант:

В каждой рекурсивной ветке, массив A делится на три части, 
которые не перекрываются, и объединение которых является исходным массивом.

На каждой итерации алгоритм делит массив 
A на три подмножества:
L (меньшие, чем опорный элемент).
E (равные опорному элементу).
G (большие, чем опорный элемент).

Предусловия для рекурсивного вызова:
Массивы L,G являются подмножествами 𝐴
E состоит из одинаковых значений.

Постусловия для рекурсивного вызова:
После выполнения рекурсивной сортировки:
quicksort(L)=sorted(L),
quicksort(G)=sorted(G).
Итоговая конкатенация:
concat(quicksort(L),E,quicksort(G))=sorted(A)

*/


import java.util.Arrays;

public class Quicksort {

    private static int[] swapTwoElements(int[] arr) {
        if (arr.length != 2) {
            throw new IllegalArgumentException("Массив должен содержать ровно два элемента.");
        }
        if (arr[0] > arr[1]) {
            int tmp = arr[0];
            arr[0] = arr[1];
            arr[1] = tmp;
        }
        return arr;
    }

    public static int[] quicksort(int[] arr) {
        if (arr.length <= 1) {
            return arr; 
        }
        if (arr.length == 2) {
            return swapTwoElements(arr); 
        }

        int pivot = arr[arr.length / 2];
        int[] less = Arrays.stream(arr).filter(x -> x < pivot).toArray();
        int[] equal = Arrays.stream(arr).filter(x -> x == pivot).toArray();
        int[] greater = Arrays.stream(arr).filter(x -> x > pivot).toArray();


        less = quicksort(less);
        greater = quicksort(greater);


        int[] result = new int[arr.length];
        System.arraycopy(less, 0, result, 0, less.length);
        System.arraycopy(equal, 0, result, less.length, equal.length);
        System.arraycopy(greater, 0, result, less.length + equal.length, greater.length);

        return result;
    }

    public static void main(String[] args) {
        int[] x = {5,4,5,7,1,7,9,5,23,32};
        int[] x_sorted = quicksort(x);
        System.out.println("Sorted values are: " + Arrays.toString(x_sorted)); 
    }
}
