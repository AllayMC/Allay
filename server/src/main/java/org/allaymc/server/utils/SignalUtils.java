package org.allaymc.server.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Signal;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * {@code SignalUtils} collects shutdown tasks and installs handlers for termination and
 * interrupt signals. Tasks can be added at either end of a concurrent deque, and the first
 * handled signal claims shutdown before executing the deque and exiting the process.
 *
 * <p>
 * Handler registration is claimed by an atomic flag before installation finishes. Other
 * callers do not wait for registration, and a failed installation leaves that flag set.
 * Task insertion also follows registration, so callers arrange tasks before shutdown can
 * begin; additions racing with the deque's weakly consistent iteration are not a guaranteed
 * snapshot. A second handled signal returns after observing the shutdown claim, while
 * individual task failures are logged so iteration can continue.
 *
 * @author daoge_cmd
 */
@Slf4j
@UtilityClass
public class SignalUtils {

    // TRIGGERED claims the one shutdown pass, while SIGNAL_REGISTERED claims handler
    // installation before it completes. Neither flag makes adding a task atomic with shutdown.
    /**
     * Claimed shutdown execution flag.
     */
    private static final AtomicBoolean TRIGGERED = new AtomicBoolean(false);
    /**
     * Claimed signal-registration attempt flag.
     */
    private static final AtomicBoolean SIGNAL_REGISTERED = new AtomicBoolean(false);
    /**
     * {@code SIGNAL_LISTEN_TO} lists the termination and interrupt signals whose handlers run
     * the shared shutdown sequence. Handler installation uses these platform signal names
     * directly.
     */
    private static final String[] SIGNAL_LISTEN_TO = {"TERM", "INT"};
    /**
     * {@code TASKS} holds shutdown callbacks in deque order. Shutdown iterates without removing
     * entries, and tasks added during that iteration are not guaranteed inclusion in its weakly
     * consistent traversal.
     */
    private static final Deque<Runnable> TASKS = new ConcurrentLinkedDeque<>();

    /**
     * Appends a non-{@code null} shutdown task after requesting signal-handler registration. Callers add
     * tasks before shutdown begins because registration and insertion are separate operations and
     * concurrent deque iteration does not guarantee inclusion of later additions.
     */
    public static void addTask(Runnable runnable) {
        registerSignalIfNeeded();
        TASKS.addLast(runnable);
    }

    /**
     * Prepends a non-{@code null} shutdown task after requesting signal-handler registration. This
     * operation
     * changes deque priority for future iteration, but cannot reorder callbacks already running
     * or guarantee inclusion after shutdown traversal begins.
     */
    public static void addTaskToFirst(Runnable runnable) {
        registerSignalIfNeeded();
        TASKS.addFirst(runnable);
    }

    /**
     * Claims the first signal-registration attempt and installs handlers on the winning caller.
     * The atomic flag is set before installation, so another caller can continue adding tasks
     * without waiting for the handlers to become active.
     *
     * <p>
     * Registration failures are not rolled back: the flag remains set and later calls do not
     * retry installation. The flag therefore means that registration was attempted, not that
     * every configured signal currently has its handler installed successfully.
     */
    private static void registerSignalIfNeeded() {
        // Claim registration before installing handlers so competing task additions do not
        // install duplicate handlers. This early claim does not publish successful installation.
        if (!SIGNAL_REGISTERED.getAndSet(true)) {
            registerTermSignalIfNeeded();
        }
    }

    /**
     * Installs handlers that run the shared shutdown sequence for each configured signal.
     * Every invocation installs handlers directly; the caller's separate registration claim
     * prevents ordinary duplicate installation through task addition.
     *
     * <p>
     * The first handler that claims {@code TRIGGERED} runs tasks and exits with status zero
     * after traversal. Later handled signals return without starting another traversal. Failures
     * outside individual task suppression are logged before exit status one is requested, so a
     * signal cannot intentionally run the same shutdown sequence twice.
     */
    private static void registerTermSignalIfNeeded() {
        for (var name : SIGNAL_LISTEN_TO) {
            Signal.handle(new Signal(name), signal -> {
                log.info("Signal {} received", name);

                // Multiple signals can reach their handlers independently. Claim shutdown
                // before task traversal so a later handled signal cannot start another pass.
                if (TRIGGERED.getAndSet(true)) {
                    return;
                }

                // Finish the shared task pass before exiting. A traversal-level failure
                // selects the error exit path; individual callback failures are handled inside doTasks.
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

    /**
     * Runs callbacks in the deque's current iteration order without removing them. Each thrown
     * callback failure is logged and traversal continues, while concurrent additions are subject
     * to the deque's weakly consistent iteration behavior.
     */
    private static void doTasks() {
        for (Runnable runnable : TASKS) {
            try {
                runnable.run();
            } catch (Throwable t) {
                // One callback's failure must not prevent later shutdown callbacks from
                // receiving their turn. Report that callback and continue the shared traversal.
                log.error("Error while executing shutdown task: {}", runnable, t);
            }
        }
    }
}