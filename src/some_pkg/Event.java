package some_pkg;

import org.jetbrains.annotations.Async;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public final class Event {
    private final @NotNull Executor executor;
    private final @NotNull List<Runnable> listeners = new ArrayList<>();

    public Event(@NotNull Executor executor) {
        this.executor = executor;
    }

    public void addListener(@Async.Schedule @NotNull Runnable listener) {
        listeners.add(listener);
    }

    @Async.Schedule
    public void fire() {
        executor.execute(this::doFire);
    }

    @Async.Execute
    private void doFire() {
        listeners.forEach(Event::run);
    }

    private static void run(@Async.Execute @NotNull Runnable listener) {
        listener.run();
    }
}
