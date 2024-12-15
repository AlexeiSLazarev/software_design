import java.util.concurrent.CountDownLatch;

public class EventDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        
        new Thread(() -> {
            try {
                System.out.println("Thread started. Waiting for event...");
                latch.await();
                System.out.println("Event fired, thread resumes!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        Thread.sleep(2000); // Simulate some work
        System.out.println("Event is fired!");
        latch.countDown();
    }
} 