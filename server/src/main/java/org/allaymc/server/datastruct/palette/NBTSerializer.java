package org.allaymc.server.datastruct.palette;

import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code NBTSerializer} converts one runtime palette value to an NBT compound for storage.
 * Palette writers handle little-endian encoding and entry framing after this conversion,
 * allowing the serializer to describe the value without owning the output buffer.
 * @author JukeboxMC | daoge_cmd
 */
@FunctionalInterface
public interface NBTSerializer<V> {
    /**
     * Returns an NBT compound describing the supplied runtime palette value.
     */
    NbtMap serialize(V value);
}
