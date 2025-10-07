package org.allaymc.server.world.light;

import it.unimi.dsi.fastutil.ints.IntArrayFIFOQueue;

/**
 * @author daoge_cmd
 */
public class LightUpdateQueue {

    protected final IntArrayFIFOQueue queue;

    public LightUpdateQueue() {
        this.queue = new IntArrayFIFOQueue();
    }

    public void add(int x, int y, int z, int lightValue) {
        queue.enqueue(x);
        queue.enqueue(y);
        queue.enqueue(z);
        queue.enqueue(lightValue);
    }

    public void poll(LightUpdateEntry dest) {
        dest.x = queue.dequeueInt();
        dest.y = queue.dequeueInt();
        dest.z = queue.dequeueInt();
        dest.lightValue = queue.dequeueInt();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /// LightUpdateEntry is used to host the results of the {@link #poll(LightUpdateEntry)}} method.
    public static final class LightUpdateEntry {
        public int x, y, z;
        public int lightValue;
    }
}
