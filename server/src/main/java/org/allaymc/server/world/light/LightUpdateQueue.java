package org.allaymc.server.world.light;

import it.unimi.dsi.fastutil.ints.IntArrayFIFOQueue;

/**
 * {@code LightUpdateQueue} stores pending light positions as four consecutive
 * integers per entry. Each tuple contains dimension x, y, z coordinates
 * followed by a light value. Callers serialize queue operations and poll only
 * complete entries, preserving the correspondence between a position and its
 * illumination.
 *
 * @author daoge_cmd
 */
public class LightUpdateQueue {

    /**
     * {@code queue} stores four integers per logical update in insertion
     * order; callers must not interleave partial tuple operations.
     */
    protected final IntArrayFIFOQueue queue;

    /**
     * Creates an empty integer queue for subsequent light propagation updates.
     */
    public LightUpdateQueue() {
        this.queue = new IntArrayFIFOQueue();
    }

    /**
     * Appends one position and light value in the queue's tuple order.
     * Propagation callers supply {@code lightValue} from {@code 0} through
     * {@code 15} and serialize additions with polling so tuple components
     * cannot interleave.
     */
    public void add(int x, int y, int z, int lightValue) {
        queue.enqueue(x);
        queue.enqueue(y);
        queue.enqueue(z);
        queue.enqueue(lightValue);
    }

    /**
     * Copies and removes the next complete tuple into the supplied
     * destination. Callers first establish that an entry exists and serialize
     * access; a failed dequeue can leave the destination partially changed.
     */
    public void poll(LightUpdateEntry dest) {
        dest.x = queue.dequeueInt();
        dest.y = queue.dequeueInt();
        dest.z = queue.dequeueInt();
        dest.lightValue = queue.dequeueInt();
    }

    /**
     * Returns whether the backing queue contains no pending update integers.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * {@code LightUpdateEntry} holds a reusable mutable position and
     * illumination tuple returned by {@code poll}.
     */
    public static final class LightUpdateEntry {
        /**
         * Dimension block coordinates of the dequeued light update.
         */
        public int x, y, z;
        /**
         * Illumination level carried by the dequeued update.
         */
        public int lightValue;
    }
}
