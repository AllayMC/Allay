package cn.allay.scheduler;

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
        //mock tick loop
        Thread.ofVirtual().start(() -> {
            while (true)
                scheduler.ticking();
        });
        AtomicLong total = new AtomicLong(0);
        for (int i = 0; i < 10000; i++) {
            scheduler.scheduleDelayed(() -> {
                total.addAndGet(1);
                return true;
            }, 1, true);
        }
        assertEquals(10000, total.get());
    }
}
