package org.allaymc.api.blockentity.type;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.BlockEntityInitInfo;

/**
 * {@code BlockEntityType} creates block entities sharing a registered
 * persistence name and component configuration. Initialization information
 * supplies per-instance state and dimension context. Type creation constructs
 * the object but leaves chunk attachment to the caller, keeping construction
 * separate from world membership.
 *
 * @author daoge_cmd
 */
public interface BlockEntityType<T extends BlockEntity> {
    /**
     * Constructs a block entity using the supplied initialization state. The
     * server implementation assigns this type into {@code info} before invoking
     * component providers; construction does not insert the returned instance
     * into its dimension or chunk.
     */
    T createBlockEntity(BlockEntityInitInfo info);

    /**
     * Returns the registered name used in this block entity's NBT identifier.
     */
    String getName();
}
