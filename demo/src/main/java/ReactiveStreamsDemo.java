import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class ReactiveStreamsDemo {
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        // Define a simple subscriber
        Subscriber<String> subscriber = new Subscriber<>() {
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1); // Request one item initially
            }

            @Override
            public void onNext(String item) {
                System.out.println("Received: " + item);
                subscription.request(1); // Request the next item
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        };

        // Add the subscriber to the publisher
        publisher.subscribe(subscriber);
        
        // Publish some items
        publisher.submit("Event 1");
        publisher.submit("Event 2");

        // Close the publisher
        publisher.close();

        // Give some time to the subscriber to receive events
        Thread.sleep(1000);
    }
}