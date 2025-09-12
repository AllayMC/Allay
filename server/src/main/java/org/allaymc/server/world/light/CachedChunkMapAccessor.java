package org.allaymc.server.world.light;

import org.allaymc.api.utils.hash.HashUtils;
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
            var value = map.get(hash);
            if (value != null) {
                // Only cache the value if it exists, null value is not cached since
                // it may be a valid (non-null) value in the future
                lastKey = hash;
                lastValue = value;
            } else {
                return null;
            }
        }
        return lastValue;
    }
}
