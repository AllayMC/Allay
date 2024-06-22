package org.allaymc.api.blockentity;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.blockentity.init.SimpleBlockEntityInitInfo;
import org.allaymc.api.blockentity.registry.BlockEntityTypeRegistry;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
@Slf4j
@UtilityClass
public final class BlockEntityHelper {
    public static BlockEntity fromNBT(Dimension dimension, NbtMap nbt) {
        var id = nbt.getString("id");
        var blockEntityType = BlockEntityTypeRegistry.getRegistry().get(id);
        if (blockEntityType == null) {
            log.warn("Unknown block entity type: {}", id);
            return null;
        }
        return blockEntityType.createBlockEntity(SimpleBlockEntityInitInfo.builder().dimension(dimension).nbt(nbt).build());
    }
}
