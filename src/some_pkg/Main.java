package some_pkg;

import java.util.concurrent.*;

public final class Main {
    private final ExecutorService executor;
    private final Event event;

    private Main() {
        executor = Executors.newSingleThreadExecutor();
        event = new Event(executor);

        executor.execute(this::task1);
        executor.execute(this::task2);
    }

    public static void main(String[] args) {
        new Main();
    }

    private void task1() {
        event.addListener(this::task3);
    }

    private void task2() {
        event.fire();
    }

    private void task3() {
        // Place the breakpoint here.
        System.out.println("Listener called");
        executor.shutdown();
    }
}
