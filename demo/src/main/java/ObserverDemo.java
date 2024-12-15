import java.util.Observable;
import java.util.Observer;

class EventSource extends Observable implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);  // Simulate some work
            setChanged();
            notifyObservers("Event occurred!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        EventSource eventSource = new EventSource();
        eventSource.addObserver(new EventObserver());
        new Thread(eventSource).start();
    }
}

class EventObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Received event: " + arg);
    }
}