package org.allaymc.api.blockentity;

import org.allaymc.api.blockentity.init.SimpleBlockEntityInitInfo;
import org.allaymc.api.blockentity.registry.BlockEntityTypeRegistry;
import org.allaymc.api.common.data.Identifier;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Objects;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public final class BlockEntityHelper {
    public static BlockEntity fromNBT(Dimension dimension, NbtMap nbt) {
        var id = nbt.getString("id");
        var blockEntityType = BlockEntityTypeRegistry.getRegistry().get(new Identifier(id));
        Objects.requireNonNull(blockEntityType, "Unknown block entity type: " + id);
        return blockEntityType.createBlockEntity(SimpleBlockEntityInitInfo.builder().dimension(dimension).nbt(nbt).build());
    }
}
