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
 * @author daoge_cmd
 */
class SchedulerTest {

    private ExecutorService asyncTaskExecutor;
    private Scheduler scheduler;

    @BeforeEach
    void setUp() {
        asyncTaskExecutor = Executors.newVirtualThreadPerTaskExecutor();
        scheduler = new AllayScheduler(asyncTaskExecutor);
    }

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

    @Test
    void testRepeating() {
        AtomicLong total = new AtomicLong();
        scheduler.scheduleRepeating(VALID_TASK_CREATOR, () -> total.incrementAndGet() != 1000, 1);
        runSchedulerUntilEmpty(1000);
        assertEquals(1000, total.get());
    }

    @Test
    void testRunLater() {
        AtomicBoolean executed = new AtomicBoolean(false);
        scheduler.runLater(VALID_TASK_CREATOR, () -> executed.set(true));

        scheduler.tick();
        assertTrue(executed.get());
    }

    @Test
    void testRunLaterAsync() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicBoolean executed = new AtomicBoolean(false);

        scheduler.runLaterAsync(VALID_TASK_CREATOR, () -> {
            executed.set(true);
            latch.countDown();
        });

        scheduler.tick();
        assertTrue(latch.await(1, TimeUnit.SECONDS));
        assertTrue(executed.get());
    }

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
        // delay(3) + period(2) = 5, first repeating task runs at tick 5
        assertEquals(delay + period, firstRunTick.get());
    }

    @Test
    void testGetTicks() {
        assertEquals(0, scheduler.getTicks());

        scheduler.tick();
        assertEquals(1, scheduler.getTicks());

        scheduler.tick();
        scheduler.tick();
        assertEquals(3, scheduler.getTicks());
    }

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

        scheduler.shutdown();

        assertTrue(cancelled.get());
        assertEquals(0, scheduler.getRunningTaskCount());
    }

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
        valid.set(false);

        scheduler.tick();
        assertFalse(taskRan.get());
        assertEquals(0, scheduler.getRunningTaskCount());
    }

    @Test
    void testAsyncRepeating() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(5);

        scheduler.scheduleRepeating(VALID_TASK_CREATOR, () -> {
            counter.incrementAndGet();
            latch.countDown();
            return latch.getCount() > 0;
        }, 1, true);

        GameLoop.builder()
                .loopCountPerSec(100)
                .onTick(loop -> {
                    scheduler.tick();
                    if (scheduler.getRunningTaskCount() == 0) {
                        loop.stop();
                    }
                })
                .build().startLoop();

        assertTrue(latch.await(5, TimeUnit.SECONDS));
        assertEquals(5, counter.get());
    }

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

    private void runSchedulerUntilEmpty(int loopCountPerSec) {
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

    private static final TaskCreator VALID_TASK_CREATOR = () -> true;
}
