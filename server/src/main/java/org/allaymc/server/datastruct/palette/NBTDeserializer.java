package org.allaymc.server.datastruct.palette;

import io.netty.buffer.ByteBuf;

/**
 * {@code NBTDeserializer} reads one palette value directly from an NBT-containing buffer.
 * The buffer-based interface permits block-state resolution from encoded bytes without
 * always constructing a complete NBT map. Implementations must consume exactly the entry
 * they resolve so later entries remain aligned.
 * @author JukeboxMC | daoge_cmd
 */
@FunctionalInterface
public interface NBTDeserializer<V> {
    /**
     * Reads one encoded palette entry and returns its runtime value. The implementation
     * advances {@code buffer} past that entry and must leave subsequent palette entries
     * available to the next deserializer call.
     */
    V deserialize(ByteBuf buffer);
}
