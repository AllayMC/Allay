package cn.allay.api.blockentity.init;

import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.world.World;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityInitInfo<T extends BlockEntity> extends ComponentInitInfo {
    World world();

    NbtMap nbt();

    BlockEntityType<T> getBlockEntityType();

    void setBlockEntityType(BlockEntityType<T> blockEntityType);
}
