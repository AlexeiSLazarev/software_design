import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int numThreads = 3;
        CountDownLatch latch = new CountDownLatch(numThreads);

        for (int i = 0; i < numThreads; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " выполняет задачу.");
                try {
                    Thread.sleep((int) (Math.random() * 2000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName() + " завершил задачу.");
                latch.countDown();
            }).start();
        }

        System.out.println("Главный поток ожидает завершения работы других потоков...");
        latch.await();
        System.out.println("Все потоки завершили работу.");
    }
}

import java.util.Observable;
import java.util.Observer;

class WeatherData extends Observable {
    void updateTemperature(float temperature) {
        setChanged();
        notifyObservers(temperature);
    }
}

class Display implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Получены новые данные: температура = " + arg + "°C");
    }
}

public class ObserverExample {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        Display display = new Display();

        weatherData.addObserver(display);

        weatherData.updateTemperature(23.5f);
        weatherData.updateTemperature(25.2f);
    }
}

import java.util.concurrent.SubmissionPublisher;

public class ReactiveStreamsExample {
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        publisher.subscribe(item -> System.out.println("Получено событие: " + item));

        publisher.submit("Событие 1");
        publisher.submit("Событие 2");

        publisher.close();

        Thread.sleep(1000); // Даем время для обработки событий
    }
}

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

class MessageEvent {
    private final String message;

    MessageEvent(String message) {
        this.message = message;
    }

    String getMessage() {
        return message;
    }
}

class MessageListener {
    @Subscribe
    public void handleMessage(MessageEvent event) {
        System.out.println("Сообщение получено: " + event.getMessage());
    }
}

public class GuavaEventBusExample {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        MessageListener listener = new MessageListener();
        eventBus.register(listener);

        eventBus.post(new MessageEvent("Привет, мир!"));
        eventBus.post(new MessageEvent("События работают!"));
    }
}

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Actor {
    private final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();
    private final Thread worker;

    public Actor() {
        worker = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    String message = messageQueue.take();
                    handleMessage(message);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        worker.start();
    }

    public void send(String message) {
        messageQueue.offer(message);
    }

    private void handleMessage(String message) {
        switch (message) {
            case "start" -> System.out.println("Актер получил команду 'start'");
            case "stop" -> System.out.println("Актер получил команду 'stop'");
            default -> System.out.println("Неизвестная команда: " + message);
        }
    }

    public void stop() {
        worker.interrupt();
    }
}

public class ActorModelExample {
    public static void main(String[] args) throws InterruptedException {
        Actor actor = new Actor();

        actor.send("start");
        actor.send("unknown");
        actor.send("stop");

        Thread.sleep(1000); // Даем время для обработки сообщений
        actor.stop();
    }
}
