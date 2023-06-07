package cn.allay.server.scheduler;

import cn.allay.api.scheduler.Scheduler;
import cn.allay.api.scheduler.task.Task;
import cn.allay.api.scheduler.taskcreator.TaskCreator;
import cn.allay.server.utils.GameLoop;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/12 <br>
 * Allay Project <br>
 */
class SchedulerTest {
    protected static final Scheduler scheduler = new AllayScheduler();

    protected static final TaskCreator MOCK_TASK_CREATOR = new MockTaskCreator();

    //@Test
    void testAsync() {
        AtomicLong total = new AtomicLong(0);
        for (int i = 0; i < 1000; i++) {
            scheduler.scheduleDelayed(new Task() {
                @Override
                public boolean onRun() {
                    total.incrementAndGet();
                    return false;
                }

                @Override
                public TaskCreator getTaskCreator() {
                    return MOCK_TASK_CREATOR;
                }
            }, 1, true);
        }
        GameLoop.builder()
                .loopCountPerSec(1000)
                .onTick(loop -> {
                    if (scheduler.getRunningTaskCount() == 0)
                        loop.stop();
                    scheduler.ticking();
                })
                .build().startLoop();
        assertEquals(1000, total.get());
    }

    @Test
    void testSync() {
        AtomicLong total = new AtomicLong();
        for (int i = 0; i < 1000; i++) {
            scheduler.scheduleDelayed(new Task() {
                @Override
                public boolean onRun() {
                    total.incrementAndGet();
                    return false;
                }

                @Override
                public TaskCreator getTaskCreator() {
                    return MOCK_TASK_CREATOR;
                }
            }, 1);
        }
        GameLoop.builder()
                .loopCountPerSec(1000)
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
        scheduler.scheduleRepeating(new Task() {
            @Override
            public boolean onRun() {
                return total.incrementAndGet() != 1000;
            }

            @Override
            public TaskCreator getTaskCreator() {
                return MOCK_TASK_CREATOR;
            }
        }, 1);
        GameLoop.builder()
                .loopCountPerSec(1000)
                .onTick(loop -> {
                    if (scheduler.getRunningTaskCount() == 0)
                        loop.stop();
                    scheduler.ticking();
                })
                .build().startLoop();
        assertEquals(1000, total.get());
    }

    private static class MockTaskCreator implements TaskCreator {
        @Override
        public boolean isValid() {
            return true;
        }
    }
}
