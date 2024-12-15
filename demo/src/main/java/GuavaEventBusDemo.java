import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class GuavaEventBusDemo {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();

        // Register a listener
        eventBus.register(new Object() {
            @Subscribe
            public void handleEvent(String event) {
                System.out.println("Received event: " + event);
            }
        });

        // Post an event
        eventBus.post("Hello World!");
    }
}