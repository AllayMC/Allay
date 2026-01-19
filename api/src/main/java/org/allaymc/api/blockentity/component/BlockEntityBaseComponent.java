package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.pdc.PersistentDataHolder;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
public interface BlockEntityBaseComponent extends BlockEntityComponent, PersistentDataHolder {

    /**
     * Gets the type of block entity.
     *
     * @return The type of block entity
     */
    BlockEntityType<? extends BlockEntity> getBlockEntityType();

    /**
     * Gets the position of the block entity.
     *
     * @return The position of the block entity
     */
    Position3ic getPosition();

    /**
     * Gets the dimension of the block entity.
     *
     * @return The dimension of the block entity
     */
    default Dimension getDimension() {
        return getPosition().dimension();
    }

    /**
     * Gets the world of the block entity.
     *
     * @return The world of the block entity
     */
    default World getWorld() {
        return getDimension().getWorld();
    }

    /**
     * Saves the NBT data of the block entity.
     *
     * @return The NBT data of the block entity
     */
    NbtMap saveNBT();

    /**
     * Saves a clean NBT data of the block entity without position-related information.
     * <p>
     * This removes position (x, y, z) and pairing information (pairx, pairz, pairlead)
     * which is useful for pistons when moving block entities.
     *
     * @return The clean NBT data of the block entity
     */
    NbtMap saveCleanNBT();

    /**
     * Loads the NBT data into the block entity.
     *
     * @param nbt The NBT data to load
     */
    void loadNBT(NbtMap nbt);

    /**
     * Gets the block state of the block entity.
     *
     * @return The block state of the block entity
     */
    default BlockState getBlockState() {
        var pos = getPosition();
        return pos.dimension().getBlockState(pos);
    }

    /**
     * Gets the custom name of the block entity.
     *
     * @return The custom name of the block entity, null if not present
     */
    String getCustomName();

    /**
     * Sets the custom name of the block entity.
     *
     * @param customName The custom name of the block entity, can be null to remove the custom name
     */
    void setCustomName(String customName);

    /**
     * Whether the block entity has a custom name.
     *
     * @return Whether the block entity has a custom name
     */
    default boolean hasCustomName() {
        return getCustomName() != null;
    }
}
