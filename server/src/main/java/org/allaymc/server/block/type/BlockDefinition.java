package org.allaymc.server.block.type;

import org.cloudburstmc.nbt.NbtMap;

import java.util.List;

/**
 * Block definition is used to be sent to the client to let the client know the attributes
 * of the custom block, such as the geometry, materials, collision box, etc.
 *
 * @param data the NBT data containing the definition of the block.
 * @author daoge_cmd
 */
public record BlockDefinition(NbtMap data) {
    /**
     * Default definition for vanilla blocks.
     */
    public static final BlockDefinition DEFAULT = new BlockDefinition(NbtMap.EMPTY);
}
