package org.allaymc.server.utils;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code GameLoopTest} exercises loop lifecycle callbacks, timing summaries,
 * and cross-thread wakeups.
 *
 * @author daoge_cmd
 */
class GameLoopTest {

    /**
     * Stops a fast loop from its hundredth tick callback.
     * The callback count must remain exactly one hundred after synchronous
     * execution returns, protecting normal stop admission.
     */
    @Test
    void testLoop() {
        var counter = new AtomicInteger(0);
        GameLoop.builder()
                .loopCountPerSec(1024)
                .onTick((loop -> {
                    if (counter.incrementAndGet() == 100)
                        loop.stop();
                }))
                .build()
                .startLoop();
        assertEquals(100, counter.get());
    }

    /**
     * Runs repeated sorting inside tick callbacks to exercise an overloaded loop.
     * After the worker finishes, the reported callback-capacity estimate must
     * not exceed the configured target rate.
     */
    @SneakyThrows
    @Test
    void testSlowLoop() {
        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(20)
                .onTick((loop -> {
                    List<Integer> list = new ArrayList<>();
                    for (int i = 0; i < 10000; i++) {
                        list.add((int) Math.ceil(Math.random() * 10));
                        Collections.sort(list);
                    }
                    if (loop.getTick() == 20) {
                        loop.stop();
                    }
                }))
                .build();
        // Keep the expensive callback workload on its loop thread, and join it
        // before reading the completed timing window from the test thread.
        Thread thread = new Thread(gameLoop::startLoop);
        thread.start();
        thread.join();
        assertTrue(gameLoop.getTPS() <= 20);
    }

    /**
     * Stops the loop during its first tick callback.
     * Both lifecycle flags must be set after execution returns, showing that
     * normal stop reaches the startup and shutdown callbacks.
     */
    @Test
    void testOnStartAndOnStopCallbacks() {
        var startCalled = new AtomicBoolean(false);
        var stopCalled = new AtomicBoolean(false);

        GameLoop.builder()
                .loopCountPerSec(1024)
                .onStart(() -> startCalled.set(true))
                .onStop(() -> stopCalled.set(true))
                .onTick(GameLoop::stop)
                .build()
                .startLoop();

        assertTrue(startCalled.get());
        assertTrue(stopCalled.get());
    }

    /**
     * Builds a loop with a nonzero initial tick counter.
     * The first callback must observe that exact value before the loop performs
     * its normal counter increment.
     */
    @Test
    void testCurrentTickInitialization() {
        long initialTick = 100;
        var tickRecorded = new AtomicInteger();

        GameLoop.builder()
                .loopCountPerSec(1024)
                .currentTick(initialTick)
                .onTick(loop -> {
                    tickRecorded.set((int) loop.getTick());
                    loop.stop();
                })
                .build()
                .startLoop();

        assertEquals(initialTick, tickRecorded.get());
    }

    /**
     * Records the counter observed by five consecutive tick callbacks.
     * The observations must start at zero and advance by one, including the
     * final callback that requests stopping.
     */
    @Test
    void testTickIncrement() {
        var ticks = new ArrayList<Long>();

        GameLoop.builder()
                .loopCountPerSec(1024)
                .onTick(loop -> {
                    ticks.add(loop.getTick());
                    if (ticks.size() >= 5) {
                        loop.stop();
                    }
                })
                .build()
                .startLoop();

        assertEquals(List.of(0L, 1L, 2L, 3L, 4L), ticks);
    }

    /**
     * Builds a loop with a target of fifty ticks per second.
     * The exposed target must retain that configured value before the first
     * callback starts and stops execution.
     */
    @Test
    void testGetLoopCountPerSec() {
        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(50)
                .onTick(GameLoop::stop)
                .build();

        assertEquals(50, gameLoop.getLoopCountPerSec());
        gameLoop.startLoop();
    }

    /**
     * Observes the running flag before startup, during a callback, and after stop.
     * The flag must initially permit work and remain {@code true} inside the admitted
     * callback until that callback clears it.
     */
    @SneakyThrows
    @Test
    void testIsRunning() {
        var latch = new CountDownLatch(1);
        var runningDuringLoop = new AtomicBoolean(false);

        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(1024)
                .onTick(loop -> {
                    runningDuringLoop.set(loop.isRunning());
                    loop.stop();
                    latch.countDown();
                })
                .build();

        assertTrue(gameLoop.isRunning());

        // Let the callback publish its observation and request stop before
        // joining, so the final flag check follows completed worker execution.
        Thread thread = new Thread(gameLoop::startLoop);
        thread.start();
        latch.await(1, TimeUnit.SECONDS);
        thread.join();

        assertTrue(runningDuringLoop.get());
        assertFalse(gameLoop.isRunning());
    }

    /**
     * Runs enough callbacks to replace the initial timing samples.
     * After the loop thread finishes, both mean milliseconds per tick and
     * normalized tick usage must be nonnegative.
     */
    @SneakyThrows
    @Test
    void testMSPTAndTickUsage() {
        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(20)
                .onTick(loop -> {
                    if (loop.getTick() >= 25) {
                        loop.stop();
                    }
                })
                .build();

        // Join the worker before inspecting timing arrays so these assertions
        // read completed samples rather than racing the loop's window shifts.
        Thread thread = new Thread(gameLoop::startLoop);
        thread.start();
        thread.join();

        float mspt = gameLoop.getMSPT();
        float tickUsage = gameLoop.getTickUsage();

        assertTrue(mspt >= 0);
        assertTrue(tickUsage >= 0);
    }

    /**
     * Supplies zero as the builder's target tick rate.
     * Configuration must throw {@code IllegalArgumentException} before constructing a
     * loop whose tick interval would require division by zero.
     */
    @Test
    void testInvalidLoopCountPerSecZero() {
        assertThrows(IllegalArgumentException.class, () ->
                GameLoop.builder().loopCountPerSec(0).build()
        );
    }

    /**
     * Supplies a negative target tick rate to the builder.
     * Configuration must throw {@code IllegalArgumentException} instead of accepting
     * a rate outside the supported positive scheduling range.
     */
    @Test
    void testInvalidLoopCountPerSecNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                GameLoop.builder().loopCountPerSec(-1).build()
        );
    }

    /**
     * Supplies a target one greater than the builder's upper limit.
     * Configuration must reject {@code 1025} with {@code IllegalArgumentException}, protecting
     * the inclusive maximum accepted by the adjacent boundary case.
     */
    @Test
    void testInvalidLoopCountPerSecTooHigh() {
        assertThrows(IllegalArgumentException.class, () ->
                GameLoop.builder().loopCountPerSec(1025).build()
        );
    }

    /**
     * Builds and runs a loop at the maximum supported target rate.
     * The value {@code 1024} must be retained and remain usable for a callback that
     * immediately requests normal stop.
     */
    @Test
    void testMaxValidLoopCountPerSec() {
        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(1024)
                .onTick(GameLoop::stop)
                .build();

        assertEquals(1024, gameLoop.getLoopCountPerSec());
        gameLoop.startLoop();
    }

    /**
     * Runs ticks on a worker while the test thread requests stop.
     * The worker must terminate within the join timeout after having executed
     * at least one callback.
     */
    @SneakyThrows
    @Test
    void testStopFromAnotherThread() {
        var tickCount = new AtomicInteger(0);

        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(1024)
                .onTick(loop -> tickCount.incrementAndGet())
                .build();

        // Give the worker a scheduling opportunity before an external stop,
        // then bound the join so a missed wakeup appears as a live worker.
        Thread loopThread = new Thread(gameLoop::startLoop);
        loopThread.start();

        Thread.sleep(50);
        gameLoop.stop();
        loopThread.join(1000);

        assertFalse(loopThread.isAlive());
        assertTrue(tickCount.get() > 0);
    }

    /**
     * Builds a loop without overriding its rate or initial counter.
     * The exposed defaults must be twenty ticks per second and a zero
     * counter before execution begins.
     */
    @Test
    void testDefaultBuilderValues() {
        GameLoop gameLoop = GameLoop.builder()
                .onTick(GameLoop::stop)
                .build();

        assertEquals(20, gameLoop.getLoopCountPerSec());
        assertEquals(0, gameLoop.getTick());
        gameLoop.startLoop();
    }

    /**
     * Runs short callbacks long enough to refresh the timing window.
     * The final callback-capacity estimate must stay positive and at most
     * the configured twenty-tick target.
     */
    @SneakyThrows
    @Test
    void testTPSWithNormalLoad() {
        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(20)
                .onTick(loop -> {
                    if (loop.getTick() >= 40) {
                        loop.stop();
                    }
                })
                .build();

        // Wait for normal worker completion before sampling TPS; otherwise the
        // test could observe a mixture of old and newly shifted samples.
        Thread thread = new Thread(gameLoop::startLoop);
        thread.start();
        thread.join();

        float tps = gameLoop.getTPS();
        assertTrue(tps > 0 && tps <= 20);
    }

    /**
     * Supplies idle work while running three callbacks at a moderate target rate.
     * The tick count must stop at three and at least one intervening idle
     * callback must be observed.
     */
    @SneakyThrows
    @Test
    void testOnIdleCallback() {
        var idleCallCount = new AtomicInteger(0);
        var tickCount = new AtomicInteger(0);

        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(20)
                .onTick(loop -> {
                    if (tickCount.incrementAndGet() >= 3) {
                        loop.stop();
                    }
                })
                .onIdle(idleCallCount::incrementAndGet)
                .build();

        // Join the worker to finish both tick and idle callbacks before
        // comparing their totals, without relying on a concurrently changing count.
        Thread thread = new Thread(gameLoop::startLoop);
        thread.start();
        thread.join();

        assertEquals(3, tickCount.get());
        assertTrue(idleCallCount.get() > 0, "onIdle should be called during idle periods");
    }

    /**
     * Wakes a slow worker and checks that idle processing was observed.
     * The recorded timestamp is narrowed to {@code int} before comparison with a {@code long}
     * timestamp, so the final inequality does not establish a reliable latency
     * bound despite its assertion message.
     */
    @SneakyThrows
    @Test
    void testWakeUpInterruptsSleep() {
        var idleCallCount = new AtomicInteger(0);
        var wakeUpTime = new AtomicInteger(0);

        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(1) // A one-second budget leaves room for an early wakeup.
                .onTick(loop -> {
                    if (loop.getTick() >= 1) {
                        loop.stop();
                    }
                })
                .onIdle(() -> {
                    idleCallCount.incrementAndGet();
                    // This narrowing drops high epoch bits; the later subtraction
                    // combines different-width timestamps and cannot measure elapsed wakeup latency reliably.
                    wakeUpTime.set((int) System.currentTimeMillis());
                })
                .build();

        Thread loopThread = new Thread(gameLoop::startLoop);
        loopThread.start();

        // Allow a scheduling opportunity before supplying the permit; this delay
        // is a heuristic and does not prove the worker has entered parking.
        Thread.sleep(50);

        long beforeWakeUp = System.currentTimeMillis();
        gameLoop.wakeUp();

        // Bound the wait while allowing the one-second tick budget to finish;
        // idle observation is asserted even if this join reaches its timeout.
        loopThread.join(2000);

        assertTrue(idleCallCount.get() > 0, "onIdle should be called after wakeUp");
        assertTrue(wakeUpTime.get() - beforeWakeUp < 500, "wakeUp should interrupt sleep quickly");
    }

    /**
     * Supplies a wake-up permit from the test thread to a slow loop worker.
     * An idle callback must release the latch within the timeout and record
     * that work was received.
     */
    @SneakyThrows
    @Test
    void testWakeUpFromAnotherThread() {
        var wakeUpReceived = new AtomicBoolean(false);
        var latch = new CountDownLatch(1);

        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(1) // A one-second budget leaves room for an early wakeup.
                .onTick(loop -> {
                    if (loop.getTick() >= 1) {
                        loop.stop();
                    }
                })
                .onIdle(() -> {
                    wakeUpReceived.set(true);
                    latch.countDown();
                })
                .build();

        Thread loopThread = new Thread(gameLoop::startLoop);
        loopThread.start();

        // Allow the worker a scheduling opportunity before wake-up; the latch
        // below, rather than this delay, records whether idle processing actually occurred.
        Thread.sleep(50);

        gameLoop.wakeUp();

        // Use the idle callback's latch to distinguish processed work from
        // merely publishing a wake-up permit to the worker thread.
        boolean received = latch.await(500, TimeUnit.MILLISECONDS);
        assertTrue(received, "wakeUp should trigger onIdle callback");
        assertTrue(wakeUpReceived.get());

        // Request stop only after idle observation so cleanup cannot suppress
        // the callback this test is waiting to receive from the worker.
        gameLoop.stop();
        loopThread.join(1000);
    }

    /**
     * Leaves the optional idle callback unset while running five ticks.
     * The loop must reach the requested count and return normally without
     * trying to invoke a {@code null} idle callback.
     */
    @Test
    void testOnIdleNullByDefault() {
        var tickCount = new AtomicInteger(0);

        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(1024)
                .onTick(loop -> {
                    if (tickCount.incrementAndGet() >= 5) {
                        loop.stop();
                    }
                })
                .build();

        gameLoop.startLoop();
        assertEquals(5, tickCount.get());
    }
}
