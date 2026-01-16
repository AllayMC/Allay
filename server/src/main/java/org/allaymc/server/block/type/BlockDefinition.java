package org.allaymc.server.block.type;

import org.cloudburstmc.nbt.NbtMap;

import java.util.List;

/**
 * Block definition is used to be sent to the client to let the client know the attributes
 * of the custom block, such as the geometry, materials, collision box, etc.
 *
 * @param data           The NBT data containing block components.
 * @param properties     The block property definitions for states.
 * @author daoge_cmd
 */
public record BlockDefinition(NbtMap data, List<NbtMap> properties) {
    /**
     * Default definition for vanilla blocks - no components, no properties.
     */
    public static final BlockDefinition DEFAULT = new BlockDefinition(NbtMap.EMPTY, List.of());
}
