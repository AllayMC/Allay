package org.allaymc.api.blockentity.initinfo;

import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.world.Dimension;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
public interface BlockEntityInitInfo extends ComponentInitInfo {
    Dimension dimension();

    NbtMap nbt();

    BlockEntityType<?> getBlockEntityType();

    void setBlockEntityType(BlockEntityType<?> blockEntityType);
}
