package org.allaymc.server.datastruct.collections.queue;

import lombok.SneakyThrows;

import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * {@code BlockingQueueWrapper} adds semaphore-based blocking reads to a caller-supplied
 * queue. Each wrapper offer publishes one item permit after invoking the underlying queue,
 * and each poll consumes a permit before removing an item. The queue must start empty,
 * accept every offered item, and support the producer and consumer concurrency used by
 * callers.
 *
 * <p>
 * The wrapper retains the queue rather than copying it, and callers must perform all
 * subsequent queue access through this wrapper to keep permits aligned with entries. It
 * does not test the boolean result of {@code Queue.offer}, so a rejecting queue can create
 * permits without items. Blocking waits propagate interruption through Lombok without
 * declaring it in the method signature; a timed or immediate miss returns {@code null}.
 * @author daoge_cmd
 */
public final class BlockingQueueWrapper<E> {
    /**
     * {@code queue} retains the caller's initially empty queue. All offers and removals
     * must pass through this wrapper, and the queue must support the intended concurrency.
     */
    private final Queue<E> queue;
    /**
     * {@code availableItems} counts published items not yet claimed by a poll. Offers
     * publish after queue insertion, and polls claim before removing an item.
     */
    private final Semaphore availableItems;

    /**
     * Creates a wrapper around an initially empty, retained queue. The semaphore starts
     * with no item permits, so preexisting entries or direct external queue access would
     * break the correspondence between permits and entries.
     */
    private BlockingQueueWrapper(Queue<E> queue) {
        this.queue = queue;
        // An initially empty queue needs no item permits; entries inserted before wrapping
        // would otherwise remain unavailable to blocking reads.
        this.availableItems = new Semaphore(0);
    }

    /**
     * Returns a wrapper retaining the supplied initially empty queue. Callers must use
     * only the wrapper afterward, choose a queue that accepts every offer, and respect
     * that queue's concurrency requirements.
     */
    public static <E> BlockingQueueWrapper<E> wrap(Queue<E> queue) {
        return new BlockingQueueWrapper<>(queue);
    }

    /**
     * Offers an element and then publishes one permit for a consumer. The queue must
     * accept the element because this method releases a permit without checking the offer
     * result. A queue rejection can therefore cause a later permitted poll to return
     * {@code null}.
     *
     * <p>
     * Insertion precedes permit publication so a consumer awakened by this operation can
     * observe its corresponding entry. The wrapper does not add mutual exclusion around
     * the queue; the supplied queue must itself support the required producer and consumer
     * concurrency.
     */
    public void offer(E e) {
        queue.offer(e);
        // Publish availability after insertion, so an awakened consumer does not race ahead of
        // the queue entry it is claiming.
        availableItems.release();
    }

    /**
     * Waits for an item permit and removes one element from the queue. The result is the
     * element selected by the underlying queue, provided the queue and permit
     * correspondence has been preserved. Interruption while acquiring propagates through
     * Lombok's unchecked rethrow.
     *
     * <p>
     * Permit acquisition precedes removal so consumers do not remove entries that have not
     * been published. Once acquisition succeeds, this method polls exactly once and does
     * not retry or restore a permit if the supplied queue unexpectedly returns
     * {@code null}.
     */
    @SneakyThrows
    public E poll() {
        // Claim published availability before removal, preventing concurrent consumers from
        // polling entries without corresponding item permits in this wrapper.
        availableItems.acquire();
        return queue.poll();
    }

    /**
     * Waits up to {@code timeout} in {@code timeUnit} for one item permit and then polls
     * the queue once. A timeout returns {@code null}, and interruption during acquisition
     * propagates through Lombok without a declared checked exception.
     *
     * <p>
     * Permit acquisition precedes removal so this caller consumes only published
     * availability. A failed acquisition leaves the queue untouched and does not require a
     * compensating permit. Successful acquisition relies on the wrapper's queue contract:
     * every offer succeeds and external code does not remove entries directly, because
     * this method does not retry an unexpected empty poll.
     */
    @SneakyThrows
    public E tryPoll(long timeout, TimeUnit timeUnit) {
        // A failed permit claim leaves the queue untouched, so this caller cannot remove an
        // entry already reserved by another consumer.
        if (availableItems.tryAcquire(timeout, timeUnit)) {
            return queue.poll();
        } else {
            return null;
        }
    }

    /**
     * Returns one queued element when an item permit is immediately available. A missing
     * permit returns {@code null}, and this method never waits for a later offer to make
     * progress.
     *
     * <p>
     * The permit is claimed before polling, preserving the wrapper's correspondence
     * between published availability and removals. A failed claim leaves the queue
     * untouched. Once a claim succeeds, the method polls exactly once and does not restore
     * a permit if an unsuitable or externally modified queue unexpectedly returns
     * {@code null}.
     */
    public E pollNow() {
        // A failed permit claim leaves the queue untouched, so this caller cannot remove an
        // entry already reserved by another consumer.
        if (availableItems.tryAcquire()) {
            return queue.poll();
        } else {
            return null;
        }
    }

    /**
     * Returns the current entry count reported by the underlying queue. In-flight offers
     * and polls can make this differ from the semaphore permit count, so callers must not
     * use it as an admission reservation.
     */
    public int size() {
        return queue.size();
    }
}
