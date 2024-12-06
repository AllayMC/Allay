package org.allaymc.server.datastruct.palette;

import io.netty.buffer.ByteBuf;

/**
 * @author JukeboxMC | daoge_cmd
 */
@FunctionalInterface
public interface PersistentDataDeserializer<V> {
    /**
     * Unlike {@link PersistentDataSerializer#serialize(Object)}, we use buffer here directly
     * because to get the block state hash, we don't need to deserialize the nbt in most cases.
     * We have a much faster method so that we can load block states very fast.
     */
    V deserialize(ByteBuf buffer);
}
