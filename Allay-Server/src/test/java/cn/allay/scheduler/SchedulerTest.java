package cn.allay.scheduler;

import cn.allay.utils.GameLoop;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/12 <br>
 * Allay Project <br>
 */
class SchedulerTest {
    protected static Scheduler scheduler = new AllayScheduler();

    @Test
    void testAsync() {
        AtomicLong total = new AtomicLong(0);
        for (int i = 0; i < 1000000; i++) {
            scheduler.scheduleDelayed(() -> {
                total.incrementAndGet();
                return false;
            }, 1, true);
        }
        GameLoop.builder()
                .loopCountPerSec(1000000)
                .onTick(loop -> {
                    if (scheduler.getRunningTaskCount() == 0)
                        loop.stop();
                    scheduler.ticking();
                })
                .build().startLoop();
        assertEquals(1000000, total.get());
    }

    @Test
    void testSync() {
        AtomicLong total = new AtomicLong();
        for (int i = 0; i < 1000; i++) {
            scheduler.scheduleDelayed(() -> {
                total.incrementAndGet();
                return false;
            }, 1);
        }
        GameLoop.builder()
                .loopCountPerSec(1000000)
                .onTick(loop -> {
                    if (scheduler.getRunningTaskCount() == 0)
                        loop.stop();
                    scheduler.ticking();
                })
                .build().startLoop();
        assertEquals(1000, total.get());
    }

    @Test
    void testRepeating() {
        AtomicLong total = new AtomicLong();
        scheduler.scheduleRepeating(() -> total.incrementAndGet() != 1000, 1);
        GameLoop.builder()
                .loopCountPerSec(1000000)
                .onTick(loop -> {
                    if (scheduler.getRunningTaskCount() == 0)
                        loop.stop();
                    scheduler.ticking();
                })
                .build().startLoop();
        assertEquals(1000, total.get());
    }
}
