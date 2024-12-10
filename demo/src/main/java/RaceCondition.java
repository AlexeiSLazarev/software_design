// 1. Итоговое значение счетчика неверно из-за того что переменная не является атомарной 
// -> Оба потока сразу ее изменяют и изменения перекрываются.
// Исправленный вариант - с атомарной переменной.

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondition {

    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        int numberOfThreads = 10;
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    counter.incrementAndGet();
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final counter value: " + counter.get());
    }
}
