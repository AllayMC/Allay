package org.allaymc.server.datastruct.collections.queue;

import io.netty.util.internal.PlatformDependent;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author daoge_cmd
 */
class BlockingQueueWrapperTest {
    @SneakyThrows
    @Test
    void test() {
        var queue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
        AtomicInteger integer = new AtomicInteger(0);
        AtomicBoolean running = new AtomicBoolean(true);

        var thread = Thread.ofPlatform().start(() -> {
            while (running.get() || queue.size() != 0) {
                // Should block here if queue is empty
                queue.poll();
                integer.incrementAndGet();
            }
        });

        for (int i = 0; i < 100; i++) {
            queue.offer(i);
        }
        running.set(false);
        thread.join();
        assertEquals(100, integer.get());
        assertNull(queue.pollNow());
    }
}