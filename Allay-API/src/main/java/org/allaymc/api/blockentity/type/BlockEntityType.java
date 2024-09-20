package org.allaymc.api.blockentity.type;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;

/**
 * @author daoge_cmd
 */
public interface BlockEntityType<T extends BlockEntity> {
    /**
     * Creates a new block entity of this type.
     *
     * @param info the block entity initialization information
     *
     * @return a new block entity
     */
    T createBlockEntity(BlockEntityInitInfo info);

    /**
     * Gets the name of this block entity type.
     *
     * @return the name
     */
    String getName();
}
