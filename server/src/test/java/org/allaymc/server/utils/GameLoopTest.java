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
 * @author daoge_cmd
 */
class GameLoopTest {

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
        Thread thread = new Thread(gameLoop::startLoop);
        thread.start();
        thread.join();
        assertTrue(gameLoop.getTPS() <= 20);
    }

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

    @Test
    void testGetLoopCountPerSec() {
        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(50)
                .onTick(GameLoop::stop)
                .build();

        assertEquals(50, gameLoop.getLoopCountPerSec());
        gameLoop.startLoop();
    }

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

        Thread thread = new Thread(gameLoop::startLoop);
        thread.start();
        latch.await(1, TimeUnit.SECONDS);
        thread.join();

        assertTrue(runningDuringLoop.get());
        assertFalse(gameLoop.isRunning());
    }

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

        Thread thread = new Thread(gameLoop::startLoop);
        thread.start();
        thread.join();

        float mspt = gameLoop.getMSPT();
        float tickUsage = gameLoop.getTickUsage();

        assertTrue(mspt >= 0);
        assertTrue(tickUsage >= 0);
    }

    @Test
    void testInvalidLoopCountPerSecZero() {
        assertThrows(IllegalArgumentException.class, () ->
                GameLoop.builder().loopCountPerSec(0).build()
        );
    }

    @Test
    void testInvalidLoopCountPerSecNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                GameLoop.builder().loopCountPerSec(-1).build()
        );
    }

    @Test
    void testInvalidLoopCountPerSecTooHigh() {
        assertThrows(IllegalArgumentException.class, () ->
                GameLoop.builder().loopCountPerSec(1025).build()
        );
    }

    @Test
    void testMaxValidLoopCountPerSec() {
        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(1024)
                .onTick(GameLoop::stop)
                .build();

        assertEquals(1024, gameLoop.getLoopCountPerSec());
        gameLoop.startLoop();
    }

    @SneakyThrows
    @Test
    void testStopFromAnotherThread() {
        var tickCount = new AtomicInteger(0);

        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(1024)
                .onTick(loop -> tickCount.incrementAndGet())
                .build();

        Thread loopThread = new Thread(gameLoop::startLoop);
        loopThread.start();

        Thread.sleep(50);
        gameLoop.stop();
        loopThread.join(1000);

        assertFalse(loopThread.isAlive());
        assertTrue(tickCount.get() > 0);
    }

    @Test
    void testDefaultBuilderValues() {
        GameLoop gameLoop = GameLoop.builder()
                .onTick(GameLoop::stop)
                .build();

        assertEquals(20, gameLoop.getLoopCountPerSec());
        assertEquals(0, gameLoop.getTick());
        gameLoop.startLoop();
    }

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

        Thread thread = new Thread(gameLoop::startLoop);
        thread.start();
        thread.join();

        float tps = gameLoop.getTPS();
        assertTrue(tps > 0 && tps <= 20);
    }

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

        Thread thread = new Thread(gameLoop::startLoop);
        thread.start();
        thread.join();

        assertEquals(3, tickCount.get());
        // onIdle should be called at least once during the idle period between ticks
        assertTrue(idleCallCount.get() > 0, "onIdle should be called during idle periods");
    }

    @SneakyThrows
    @Test
    void testWakeUpInterruptsSleep() {
        var idleCallCount = new AtomicInteger(0);
        var wakeUpTime = new AtomicInteger(0);

        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(1) // 1 TPS = 1000ms per tick, giving plenty of idle time
                .onTick(loop -> {
                    if (loop.getTick() >= 1) {
                        loop.stop();
                    }
                })
                .onIdle(() -> {
                    idleCallCount.incrementAndGet();
                    wakeUpTime.set((int) System.currentTimeMillis());
                })
                .build();

        Thread loopThread = new Thread(gameLoop::startLoop);
        loopThread.start();

        // Wait a bit for the loop to start and enter idle
        Thread.sleep(50);

        // Record time before wakeUp
        long beforeWakeUp = System.currentTimeMillis();
        gameLoop.wakeUp();

        // Wait for the loop to finish
        loopThread.join(2000);

        // onIdle should have been called after wakeUp
        assertTrue(idleCallCount.get() > 0, "onIdle should be called after wakeUp");
        // The wakeUp time should be close to when we called wakeUp (not waiting full 1000ms)
        assertTrue(wakeUpTime.get() - beforeWakeUp < 500, "wakeUp should interrupt sleep quickly");
    }

    @SneakyThrows
    @Test
    void testWakeUpFromAnotherThread() {
        var wakeUpReceived = new AtomicBoolean(false);
        var latch = new CountDownLatch(1);

        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(1) // Very slow to ensure we're in idle state
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

        // Wait for the first tick to complete and enter idle
        Thread.sleep(50);

        // Wake up from another thread
        gameLoop.wakeUp();

        // Wait for onIdle to be called
        boolean received = latch.await(500, TimeUnit.MILLISECONDS);
        assertTrue(received, "wakeUp should trigger onIdle callback");
        assertTrue(wakeUpReceived.get());

        // Stop the loop
        gameLoop.stop();
        loopThread.join(1000);
    }

    @Test
    void testOnIdleNullByDefault() {
        // When onIdle is not set, loop should still work without NPE
        var tickCount = new AtomicInteger(0);

        GameLoop gameLoop = GameLoop.builder()
                .loopCountPerSec(1024)
                .onTick(loop -> {
                    if (tickCount.incrementAndGet() >= 5) {
                        loop.stop();
                    }
                })
                .build();

        // Should not throw any exception
        gameLoop.startLoop();
        assertEquals(5, tickCount.get());
    }
}