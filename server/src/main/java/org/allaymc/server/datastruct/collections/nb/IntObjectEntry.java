package org.allaymc.server.datastruct.collections.nb;

import java.util.Map;

/**
 * @author SuperIceCN
 */
public interface IntObjectEntry<V> extends Map.Entry<Integer, V> {
    @Deprecated
    default Integer getKey() {
        return getIntKey();
    }

    int getIntKey();
}
