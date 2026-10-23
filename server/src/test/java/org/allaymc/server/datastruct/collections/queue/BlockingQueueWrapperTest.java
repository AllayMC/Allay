package org.allaymc.server.datastruct.collections.queue;

import io.netty.util.internal.PlatformDependent;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * {@code BlockingQueueWrapperTest} exercises blocking consumption of items offered through
 * a wrapper over an MPSC queue.
 * @author daoge_cmd
 */
class BlockingQueueWrapperTest {
    /**
     * Offers one hundred items while a separate consumer polls through the blocking
     * wrapper. After the consumer finishes, the count must match the offers and immediate
     * polling must report no remaining item.
     */
    @SneakyThrows
    @Test
    void test() {
        var queue = BlockingQueueWrapper.wrap(PlatformDependent.newMpscQueue());
        AtomicInteger integer = new AtomicInteger(0);
        AtomicBoolean running = new AtomicBoolean(true);

        var thread = Thread.ofPlatform().start(() -> {
            while (running.get() || queue.size() != 0) {
                // Wait for an item permit instead of counting an empty poll as a consumed item
                // while the producer is still active.
                queue.poll();
                integer.incrementAndGet();
            }
        });

        for (int i = 0; i < 100; i++) {
            queue.offer(i);
        }
        // Stop after publishing all offers, then join before checking the count so assertions
        // observe the completed consumer's increments.
        running.set(false);
        thread.join();
        assertEquals(100, integer.get());
        assertNull(queue.pollNow());
    }
}
