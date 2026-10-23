package org.allaymc.server.scheduler;

import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scheduler.Task;
import org.allaymc.api.scheduler.TaskCreator;
import org.allaymc.server.utils.GameLoop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code SchedulerTest} checks tick-based task execution, adapter overloads,
 * and cancellation hooks
 * using a fresh scheduler for each scenario. Asynchronous cases coordinate
 * observable callback progress with atomic state or completion latches.
 *
 * @author daoge_cmd
 */
class SchedulerTest {

    /**
     * {@code asyncTaskExecutor} executes asynchronous callbacks for
     * {@code scheduler}. Setup allocates a separate virtual-thread executor before the
     * scenario registers any work.
     */
    private ExecutorService asyncTaskExecutor;
    /**
     * {@code scheduler} holds the fresh scheduler for each scenario. The test
     * advances its clock explicitly through direct tick calls or its game loop.
     */
    private Scheduler scheduler;

    /**
     * Creates a virtual-thread executor and empty scheduler before each test.
     * Tests advance that scheduler's clock themselves, so registration and
     * execution assertions do not depend on a running server instance.
     */
    @BeforeEach
    void setUp() {
        asyncTaskExecutor = Executors.newVirtualThreadPerTaskExecutor();
        scheduler = new AllayScheduler(asyncTaskExecutor);
    }

    /**
     * Queues one thousand asynchronous one-shot tasks for the same due tick.
     * The loop waits for registration accounting to reach zero before checking
     * that every callback incremented the shared total.
     */
    @Test
    void testAsync() {
        AtomicLong total = new AtomicLong(0);
        for (int i = 0; i < 1000; i++) {
            scheduler.scheduleDelayed(VALID_TASK_CREATOR, () -> {
                total.incrementAndGet();
                return false;
            }, 1, true);
        }
        runSchedulerUntilEmpty(20);
        assertEquals(1000, total.get());
    }

    /**
     * Queues one thousand synchronous callbacks at the same deadline.
     * Draining the scheduler must invoke every callback once and leave their
     * accumulated total equal to the registration count.
     */
    @Test
    void testSync() {
        AtomicLong total = new AtomicLong();
        for (int i = 0; i < 1000; i++) {
            scheduler.scheduleDelayed(VALID_TASK_CREATOR, () -> {
                total.incrementAndGet();
                return false;
            }, 1);
        }
        runSchedulerUntilEmpty(20);
        assertEquals(1000, total.get());
    }

    /**
     * Repeats a synchronous task until its thousandth invocation returns {@code false}.
     * The scheduler must stop dispatching that registration without adding any
     * further increments after the terminating callback.
     */
    @Test
    void testRepeating() {
        AtomicLong total = new AtomicLong();
        scheduler.scheduleRepeating(VALID_TASK_CREATOR, () -> total.incrementAndGet() != 1000, 1);
        runSchedulerUntilEmpty(1000);
        assertEquals(1000, total.get());
    }

    /**
     * Registers a run-later callback before advancing the scheduler for the
     * first time. That next tick must execute the callback and expose its
     * state change before returning.
     */
    @Test
    void testRunLater() {
        AtomicBoolean executed = new AtomicBoolean(false);
        scheduler.runLater(VALID_TASK_CREATOR, () -> executed.set(true));

        scheduler.tick();
        assertTrue(executed.get());
    }

    /**
     * Registers an asynchronous run-later callback before the first tick.
     * Ticking must submit it, and the completion latch lets the assertion wait
     * for the executor to publish the callback's state change.
     */
    @Test
    void testRunLaterAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicBoolean executed = new AtomicBoolean(false);

        // Publish the execution flag before releasing the latch so a successful
        // wait observes callback progress, not merely submission to the executor.
        scheduler.runLaterAsync(VALID_TASK_CREATOR, () -> {
            executed.set(true);
            latch.countDown();
        });

        scheduler.tick();
        // Bound the completion wait so a missing callback fails this assertion
        // instead of blocking the test indefinitely while the executor stays open.
        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertTrue(executed.get());
    }

    /**
     * Starts a repeating task through a separate delayed setup registration.
     * The first callback must occur after delay plus period, and the {@code false}
     * result on its fifth invocation must end repetition.
     */
    @Test
    void testScheduleDelayedRepeating() {
        AtomicInteger counter = new AtomicInteger(0);
        AtomicLong firstRunTick = new AtomicLong(-1);
        int delay = 3;
        int period = 2;

        scheduler.scheduleDelayedRepeating(VALID_TASK_CREATOR, () -> {
            if (firstRunTick.get() == -1) {
                firstRunTick.set(scheduler.getTicks());
            }
            return counter.incrementAndGet() < 5;
        }, delay, period);

        runSchedulerUntilEmpty(100);

        assertEquals(5, counter.get());
        // Delayed setup only registers repetition; the repeating registration
        // contributes its own first period, so the callback starts after both intervals.
        assertEquals(delay + period, firstRunTick.get());
    }

    /**
     * Advances a newly created scheduler without registering any tasks.
     * Its tick counter must start at zero and increase once for every explicit
     * call, independently of whether work is available.
     */
    @Test
    void testGetTicks() {
        assertEquals(0, scheduler.getTicks());

        scheduler.tick();
        assertEquals(1, scheduler.getTicks());

        scheduler.tick();
        scheduler.tick();
        assertEquals(3, scheduler.getTicks());
    }

    /**
     * Registers delayed callbacks while none is yet executing.
     * The reported count must include those waiting registrations and return to
     * zero after the loop executes and cancels them.
     */
    @Test
    void testGetRunningTaskCount() {
        assertEquals(0, scheduler.getRunningTaskCount());

        scheduler.scheduleDelayed(VALID_TASK_CREATOR, () -> false, 5);
        assertEquals(1, scheduler.getRunningTaskCount());

        scheduler.scheduleDelayed(VALID_TASK_CREATOR, () -> false, 5);
        scheduler.scheduleDelayed(VALID_TASK_CREATOR, () -> false, 5);
        assertEquals(3, scheduler.getRunningTaskCount());

        runSchedulerUntilEmpty(20);
        assertEquals(0, scheduler.getRunningTaskCount());
    }

    /**
     * Shuts down a scheduler while a synchronous repeating task is waiting for
     * its next invocation. The queued registration must receive cancellation
     * notification and disappear from the live task count.
     */
    @Test
    void testShutdown() {
        AtomicInteger counter = new AtomicInteger(0);
        AtomicBoolean cancelled = new AtomicBoolean(false);

        scheduler.scheduleRepeating(VALID_TASK_CREATOR, new Task() {
            @Override
            public boolean onRun() {
                counter.incrementAndGet();
                return true;
            }

            @Override
            public void onCancel() {
                cancelled.set(true);
            }
        }, 1);

        scheduler.tick();
        scheduler.tick();

        // Synchronous completion has already requeued this registration, making
        // shutdown's queue traversal responsible for delivering its cancellation hook and count update.
        scheduler.shutdown();

        assertTrue(cancelled.get());
        assertEquals(0, scheduler.getRunningTaskCount());
    }

    /**
     * Runs a one-shot task whose callback returns {@code false}.
     * Its cancellation hook must still be delivered when execution ends, even
     * though no repeating invocation was ever requested.
     */
    @Test
    void testOnCancelCallback() {
        AtomicBoolean cancelCalled = new AtomicBoolean(false);

        scheduler.scheduleDelayed(VALID_TASK_CREATOR, new Task() {
            @Override
            public boolean onRun() {
                return false;
            }

            @Override
            public void onCancel() {
                cancelCalled.set(true);
            }
        }, 1);

        runSchedulerUntilEmpty(20);
        assertTrue(cancelCalled.get());
    }

    /**
     * Throws a known runtime exception from a scheduled callback.
     * The error hook must receive that same exception instance before the
     * scheduler finishes cancellation accounting for the task.
     */
    @Test
    void testOnErrorCallback() {
        AtomicBoolean errorCalled = new AtomicBoolean(false);
        RuntimeException testException = new RuntimeException("Test exception");

        scheduler.scheduleDelayed(VALID_TASK_CREATOR, new Task() {
            @Override
            public boolean onRun() {
                throw testException;
            }

            @Override
            public void onError(Throwable error) {
                if (error == testException) {
                    errorCalled.set(true);
                }
            }
        }, 1);

        runSchedulerUntilEmpty(20);
        assertTrue(errorCalled.get());
    }

    /**
     * Invalidates a creator after its repeating task has run once.
     * The next due-task scan must cancel the registration without invoking the
     * callback again and must reduce the task count to zero.
     */
    @Test
    void testInvalidTaskCreatorCancelsTask() {
        AtomicBoolean taskRan = new AtomicBoolean(false);
        AtomicBoolean valid = new AtomicBoolean(true);

        TaskCreator invalidatableCreator = valid::get;

        scheduler.scheduleRepeating(invalidatableCreator, () -> {
            taskRan.set(true);
            return true;
        }, 1);

        scheduler.tick();
        assertTrue(taskRan.get());

        taskRan.set(false);
        // Invalidate between ticks so the next dispatch check observes the
        // changed creator state before it can invoke this repeating callback again.
        valid.set(false);

        scheduler.tick();
        assertFalse(taskRan.get());
        assertEquals(0, scheduler.getRunningTaskCount());
    }

    /**
     * Repeats an asynchronous callback until five executions have signalled a
     * latch. Completion must reinsert work between invocations and eventually
     * stop registration accounting when the callback returns {@code false}.
     */
    @Test
    void testAsyncRepeating() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(5);

        // Count each invocation before signalling completion; the remaining
        // latch count also supplies the callback's decision to request another scheduled execution.
        scheduler.scheduleRepeating(VALID_TASK_CREATOR, () -> {
            counter.incrementAndGet();
            latch.countDown();
            return latch.getCount() > 0;
        }, 1, true);

        // Keep ticking while executor callbacks complete and requeue themselves;
        // the live registration count terminates the loop after the final callback cancels.
        GameLoop.builder()
                .loopCountPerSec(100)
                .onTick(loop -> {
                    scheduler.tick();
                    if (scheduler.getRunningTaskCount() == 0) {
                        loop.stop();
                    }
                })
                .build().startLoop();

        // Verify every callback signalled before accepting the final total;
        // this bounded latch wait does not impose a timeout on the preceding game loop.
        assertTrue(latch.await(5, TimeUnit.SECONDS));
        assertEquals(5, counter.get());
    }

    /**
     * Registers a callback with a five-tick delay on an empty scheduler.
     * Each earlier tick must leave the recorded execution sentinel unchanged,
     * and the due tick must become the recorded execution time.
     */
    @Test
    void testDelayedTaskExecutesAtCorrectTick() {
        AtomicLong executionTick = new AtomicLong(-1);
        int delay = 5;

        scheduler.scheduleDelayed(VALID_TASK_CREATOR, () -> {
            executionTick.set(scheduler.getTicks());
            return false;
        }, delay);

        for (int i = 0; i < delay; i++) {
            scheduler.tick();
            if (i < delay - 1) {
                assertEquals(-1, executionTick.get());
            }
        }

        assertEquals(delay, executionTick.get());
    }

    /**
     * Schedules two callbacks for one tick and a third for the following tick.
     * Both earlier callbacks must precede the later one, without requiring a
     * particular order among entries sharing a deadline.
     */
    @Test
    void testMultipleTasksExecutionOrder() {
        StringBuilder order = new StringBuilder();

        scheduler.scheduleDelayed(VALID_TASK_CREATOR, () -> {
            order.append("A");
            return false;
        }, 1);

        scheduler.scheduleDelayed(VALID_TASK_CREATOR, () -> {
            order.append("B");
            return false;
        }, 2);

        scheduler.scheduleDelayed(VALID_TASK_CREATOR, () -> {
            order.append("C");
            return false;
        }, 1);

        runSchedulerUntilEmpty(20);

        assertTrue(order.toString().startsWith("A") || order.toString().startsWith("C"));
        assertTrue(order.toString().endsWith("B"));
        assertEquals(3, order.length());
    }

    /**
     * Records the execution ticks of four synchronous repeating invocations.
     * Consecutive callbacks must remain separated by the configured period,
     * while the final {@code false} result terminates further repetition.
     */
    @Test
    void testRepeatingTaskPeriod() {
        AtomicInteger counter = new AtomicInteger(0);
        var executionTicks = new java.util.ArrayList<Long>();
        int period = 3;

        scheduler.scheduleRepeating(VALID_TASK_CREATOR, () -> {
            executionTicks.add(scheduler.getTicks());
            return counter.incrementAndGet() < 4;
        }, period);

        runSchedulerUntilEmpty(20);

        assertEquals(4, executionTicks.size());
        for (int i = 1; i < executionTicks.size(); i++) {
            assertEquals(period, executionTicks.get(i) - executionTicks.get(i - 1));
        }
    }

    /**
     * Schedules a runnable through the synchronous delayed overload.
     * Its adapter must preserve the requested two-tick delay and expose the
     * runnable's state change only when the second tick executes.
     */
    @Test
    void testScheduleDelayedWithRunnable() {
        AtomicBoolean executed = new AtomicBoolean(false);

        scheduler.scheduleDelayed(VALID_TASK_CREATOR, () -> executed.set(true), 2);

        scheduler.tick();
        assertFalse(executed.get());

        scheduler.tick();
        assertTrue(executed.get());
    }

    /**
     * Schedules a runnable through the asynchronous delayed overload.
     * The first tick must submit its adapter, and a latch verifies the
     * runnable actually completes rather than only entering the executor queue.
     */
    @Test
    void testScheduleDelayedAsyncWithRunnable() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicBoolean executed = new AtomicBoolean(false);

        // Signal only after updating the execution flag, allowing the waiting
        // test thread to distinguish completed runnable work from an executor submission.
        scheduler.scheduleDelayed(VALID_TASK_CREATOR, () -> {
            executed.set(true);
            latch.countDown();
        }, 1, true);

        scheduler.tick();
        // Limit this asynchronous observation to one second so a callback
        // missing from the executor becomes an assertion failure instead of an unbounded wait.
        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertTrue(executed.get());
    }

    /**
     * Repeats a runnable with a creator whose validity can change.
     * The adapter must keep executing after normal returns and stop before
     * another invocation once the creator becomes invalid.
     */
    @Test
    void testScheduleRepeatingWithRunnable() {
        AtomicInteger counter = new AtomicInteger(0);
        AtomicBoolean valid = new AtomicBoolean(true);

        TaskCreator invalidatableCreator = valid::get;

        scheduler.scheduleRepeating(invalidatableCreator, counter::incrementAndGet, 1);

        for (int i = 0; i < 5; i++) {
            scheduler.tick();
        }
        assertEquals(5, counter.get());

        // The runnable adapter always requests another invocation, so changing
        // creator validity supplies the cancellation condition exercised by the next tick.
        valid.set(false);
        scheduler.tick();

        assertEquals(5, counter.get());
    }

    /**
     * Starts runnable repetition after a delayed setup phase and later
     * invalidates its creator. The adapter must preserve the extra first
     * period and stop without increasing the counter after invalidation.
     */
    @Test
    void testScheduleDelayedRepeatingWithRunnable() {
        AtomicInteger counter = new AtomicInteger(0);
        AtomicBoolean valid = new AtomicBoolean(true);
        int delay = 2;
        int period = 1;

        TaskCreator invalidatableCreator = valid::get;

        scheduler.scheduleDelayedRepeating(invalidatableCreator, counter::incrementAndGet, delay, period);

        // The delayed callback installs a repeating registration without running
        // its runnable; that registration still waits through its first period before execution.
        scheduler.tick();
        scheduler.tick();
        assertEquals(0, counter.get());

        scheduler.tick();
        assertEquals(1, counter.get());

        scheduler.tick();
        assertEquals(2, counter.get());

        // Invalidation occurs while the repeating registration is queued, so
        // the next tick cancels it without entering the runnable's increment operation again.
        valid.set(false);
        scheduler.tick();
        assertEquals(2, counter.get());
    }

    /**
     * Runs a ticking loop until no registrations remain in scheduler accounting.
     * The requested frequency controls tick pacing rather than an execution
     * limit; a registration that never cancels can keep this helper running
     * indefinitely, and the final loop callback still advances one tick.
     */
    private void runSchedulerUntilEmpty(int loopCountPerSec) {
        // Poll accounting rather than queue size because asynchronous tasks
        // leave the queue while executing and may reinsert themselves after their callback finishes.
        GameLoop.builder()
                .loopCountPerSec(loopCountPerSec)
                .onTick(loop -> {
                    if (scheduler.getRunningTaskCount() == 0) {
                        loop.stop();
                    }
                    scheduler.tick();
                })
                .build().startLoop();
    }

    /**
     * {@code VALID_TASK_CREATOR} keeps test registrations eligible at every
     * dispatch check so their own
     * callback results determine completion unless a scenario supplies another creator.
     */
    private static final TaskCreator VALID_TASK_CREATOR = () -> true;
}
