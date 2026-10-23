package org.allaymc.server.world.light;

import org.allaymc.api.utils.hash.HashUtils;
import org.jctools.maps.NonBlockingHashMapLong;

/**
 * {@code CachedChunkMapAccessor} reads chunk-keyed data from dimension block
 * coordinates and retains the last successful lookup. Missing entries are
 * retried on later access, allowing chunks to appear after an earlier miss.
 * Cached values are not invalidated when the backing map changes, and each
 * accessor requires serialized use.
 *
 * @author daoge_cmd
 */
public class CachedChunkMapAccessor<T> {
    /**
     * {@code map} retains the backing store without copying; removals or
     * replacements do not invalidate an already cached value.
     */
    protected final NonBlockingHashMapLong<T> map;
    // lastKey and lastValue describe one successful lookup. get updates both
    // without synchronization, so concurrent callers must not share this accessor.
    /**
     * Packed chunk coordinates of the cached lookup.
     */
    protected long lastKey = Long.MAX_VALUE;
    /**
     * Value retained from the cached lookup.
     */
    protected T lastValue = null;

    /**
     * Creates a one-entry accessor retaining the supplied chunk map. Callers
     * serialize accesses and account for cached values surviving changes to
     * the backing map; the constructor does not populate the cache.
     */
    protected CachedChunkMapAccessor(NonBlockingHashMapLong<T> map) {
        this.map = map;
    }

    /**
     * Returns the value for the chunk containing the supplied block
     * coordinates. A missing map entry returns {@code null} without replacing
     * the previous cache entry; an existing cached key bypasses the map even
     * after replacement or removal.
     */
    public T get(int x, int z) {
        var hash = HashUtils.hashXZ(x >> 4, z >> 4);
        if (hash != lastKey) {
            var value = map.get(hash);
            if (value != null) {
                // Cache successful lookups only. Retrying misses lets a later chunk
                // load supply data for coordinates that previously had no map entry.
                lastKey = hash;
                lastValue = value;
            } else {
                return null;
            }
        }
        return lastValue;
    }
}
