package org.allaymc.api.blockentity.type;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;

/**
 * @author daoge_cmd
 */
public interface BlockEntityType<T extends BlockEntity> {
    String getName();

    T createBlockEntity(BlockEntityInitInfo info);
}
