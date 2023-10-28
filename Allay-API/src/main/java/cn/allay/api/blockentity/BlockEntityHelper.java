package cn.allay.api.blockentity;

import cn.allay.api.blockentity.init.SimpleBlockEntityInitInfo;
import cn.allay.api.blockentity.registry.BlockEntityTypeRegistry;
import cn.allay.api.world.World;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Objects;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
public final class BlockEntityHelper {
    public static BlockEntity fromNBT(World world, NbtMap nbt) {
        var id = nbt.getString("id");
        var blockEntityType = BlockEntityTypeRegistry.getRegistry().get(id);
        Objects.requireNonNull(blockEntityType, "Unknown block entity type: " + id);
        return blockEntityType.createBlockEntity(SimpleBlockEntityInitInfo.builder().world(world).nbt(nbt).build());
    }
}
