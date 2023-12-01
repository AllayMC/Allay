package org.allaymc.api.datastruct.collections.nb;

import java.util.Map;

/**
 * Allay Project 2023/7/8
 *
 * @author SuperIceCN
 */
public interface IntObjectEntry<V> extends Map.Entry<Integer, V> {
    @Deprecated
    default Integer getKey() {
        return getIntKey();
    }

    int getIntKey();
}
