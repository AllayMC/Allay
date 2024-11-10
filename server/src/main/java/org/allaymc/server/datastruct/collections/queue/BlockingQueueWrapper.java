package org.allaymc.server.datastruct.collections.queue;

import lombok.SneakyThrows;

import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author daoge_cmd
 */
public final class BlockingQueueWrapper<E> {
    private final Queue<E> queue;
    private final Semaphore availableItems;

    public static <E> BlockingQueueWrapper<E> wrap(Queue<E> queue) {
        return new BlockingQueueWrapper<>(queue);
    }

    private BlockingQueueWrapper(Queue<E> queue) {
        this.queue = queue;
        // Start with 0 permits since queue is initially empty
        this.availableItems = new Semaphore(0);
    }

    public void offer(E e) {
        queue.offer(e);
        availableItems.release();
    }

    @SneakyThrows
    public E poll() {
        availableItems.acquire();
        return queue.poll();
    }

    @SneakyThrows
    public E tryPoll(long timeout, TimeUnit timeUnit) {
        if (availableItems.tryAcquire(timeout, timeUnit)) {
            return queue.poll();
        } else {
            return null;
        }
    }

    public E pollNow() {
        if (availableItems.tryAcquire()) {
            return queue.poll();
        } else {
            return null;
        }
    }

    public int size() {
        return queue.size();
    }
}
