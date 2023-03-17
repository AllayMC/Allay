package cn.allay.scheduler;

import cn.allay.scheduler.task.Task;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/12 <br>
 * Allay Project <br>
 */
@Slf4j
class SchedulerTest {
    protected static Scheduler scheduler = new AllayScheduler();

    @Test
    void testAsync() throws InterruptedException {
        AtomicLong total = new AtomicLong(0);
        for (int i = 0; i < 1000; i++) {
            scheduler.scheduleDelayed(() -> {
                total.addAndGet(1);
                return false;
            }, 1, true);
        }
        //TODO: mock tick loop, replace it!
        while(scheduler.getRunningTasks().size() != 0) {
            scheduler.ticking();
            Thread.sleep(1);
        }
        assertEquals(1000, total.get());
    }
}
