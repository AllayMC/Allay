package cn.allay.api.blockentity;

import cn.allay.api.blockentity.component.base.BlockEntityBaseComponent;
import cn.allay.api.blockentity.init.SimpleBlockEntityInitInfo;
import cn.allay.api.blockentity.type.BlockEntityTypeRegistry;
import cn.allay.api.math.position.Position3i;
import cn.allay.api.world.World;
import org.cloudburstmc.nbt.NbtMap;

import java.util.Objects;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntity extends
        BlockEntityBaseComponent {

     static BlockEntity fromNBT(World world, NbtMap nbt) {
        var id = nbt.getString("id");
        var blockEntityType = BlockEntityTypeRegistry.getRegistry().get(id);
        Objects.requireNonNull(blockEntityType, "Unknown block entity type: " + id);
        return blockEntityType.createBlockEntity(SimpleBlockEntityInitInfo.builder().world(world).nbt(nbt).build());
    }
}
