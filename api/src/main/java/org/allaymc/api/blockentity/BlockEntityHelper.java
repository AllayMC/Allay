package org.allaymc.api.blockentity;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
@Slf4j
@UtilityClass
public final class BlockEntityHelper {
    /**
     * Creates a new block entity from a NBT map.
     *
     * @param dimension the dimension the block entity is in
     * @param nbt       the NBT map to create the block entity from
     * @return the new block entity
     */
    public static BlockEntity fromNBT(Dimension dimension, NbtMap nbt) {
        var id = nbt.getString("id");
        var blockEntityType = Registries.BLOCK_ENTITIES.get(id);
        if (blockEntityType == null) {
            log.warn("Unknown block entity type: {}", id);
            return null;
        }
        return blockEntityType.createBlockEntity(BlockEntityInitInfo.builder().dimension(dimension).nbt(nbt).build());
    }
}
