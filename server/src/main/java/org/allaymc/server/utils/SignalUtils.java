package org.allaymc.server.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Signal;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author daoge_cmd
 */
@Slf4j
@UtilityClass
public class SignalUtils {

    private static final AtomicBoolean TRIGGERED = new AtomicBoolean(false);
    private static final AtomicBoolean SIGNAL_REGISTERED = new AtomicBoolean(false);
    private static final String[] SIGNAL_LISTEN_TO = {"TERM", "INT"};
    private static final Deque<Runnable> TASKS = new ConcurrentLinkedDeque<>();

    public static void addTask(Runnable runnable) {
        registerSignalIfNeeded();
        TASKS.addLast(runnable);
    }

    public static void addTaskToFirst(Runnable runnable) {
        registerSignalIfNeeded();
        TASKS.addFirst(runnable);
    }

    private static void registerSignalIfNeeded() {
        if (!SIGNAL_REGISTERED.getAndSet(true)) {
            registerTermSignalIfNeeded();
        }
    }

    private static void registerTermSignalIfNeeded() {
        for (var name : SIGNAL_LISTEN_TO) {
            Signal.handle(new Signal(name), signal -> {
                log.info("Signal {} received", name);

                if (TRIGGERED.getAndSet(true)) {
                    return;
                }

                try {
                    doTasks();
                    System.exit(0);
                } catch (Throwable t) {
                    log.error("Error while executing shutdown tasks", t);
                    System.exit(1);
                }
            });
        }
    }

    private static void doTasks() {
        for (Runnable runnable : TASKS) {
            try {
                runnable.run();
            } catch (Throwable t) {
                log.error("Error while executing shutdown task: {}", runnable, t);
            }
        }
    }
}