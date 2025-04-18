package org.allaymc.server.world.service;

import org.allaymc.api.utils.HashUtils;
import org.jctools.maps.NonBlockingHashMapLong;

/**
 * @author daoge_cmd
 */
public class CachedChunkMapAccessor<T> {
    protected final NonBlockingHashMapLong<T> map;
    protected long lastKey = Long.MAX_VALUE;
    protected T lastValue = null;

    protected CachedChunkMapAccessor(NonBlockingHashMapLong<T> map) {
        this.map = map;
    }

    public T get(int x, int z) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        if (hash != lastKey) {
            lastKey = hash;
            lastValue = map.get(hash);
        }
        return lastValue;
    }
}
