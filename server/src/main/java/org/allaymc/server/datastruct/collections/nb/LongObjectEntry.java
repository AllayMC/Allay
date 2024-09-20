package org.allaymc.server.datastruct.collections.nb;

import java.util.Map;

/**
 * @author SuperIceCN
 */
public interface LongObjectEntry<V> extends Map.Entry<Long, V> {
    @Deprecated
    default Long getKey() {
        return getLongKey();
    }

    long getLongKey();
}
