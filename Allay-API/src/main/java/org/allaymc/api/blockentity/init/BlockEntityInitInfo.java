package org.allaymc.api.blockentity.init;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityInitInfo<T extends BlockEntity> extends ComponentInitInfo {
    Dimension dimension();

    NbtMap nbt();

    BlockEntityType<T> getBlockEntityType();

    void setBlockEntityType(BlockEntityType<T> blockEntityType);
}
