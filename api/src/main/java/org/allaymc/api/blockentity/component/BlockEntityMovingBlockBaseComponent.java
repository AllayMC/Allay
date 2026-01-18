package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.type.BlockState;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3ic;

/**
 * Base component interface for moving block block entities.
 *
 * @author daoge_cmd
 */
public interface BlockEntityMovingBlockBaseComponent extends BlockEntityBaseComponent {

    /**
     * Get the block state that is being moved.
     *
     * @return the moving block state
     */
    BlockState getMovingBlockState();

    /**
     * Set the block state that is being moved.
     *
     * @param blockState the block state
     */
    void setMovingBlockState(BlockState blockState);

    /**
     * Get the position of the piston that is moving this block.
     *
     * @return the piston position
     */
    Vector3ic getPistonPos();

    /**
     * Set the position of the piston that is moving this block.
     *
     * @param pos the piston position
     */
    void setPistonPos(Vector3ic pos);

    /**
     * Get the NBT data of the block entity being moved, if any.
     *
     * @return the moving block entity NBT, or {#code null} if none
     */
    NbtMap getMovingBlockEntityNBT();

    /**
     * Set the NBT data of the block entity being moved.
     *
     * @param nbt the block entity NBT, or {@code null} if there is no block entity
     */
    void setMovingBlockEntityNBT(NbtMap nbt);

    /**
     * Check if the block is expanding (moving away from piston) or retracting (moving towards piston).
     *
     * @return true if expanding, false if retracting
     */
    boolean isExpanding();

    /**
     * Set whether the block is expanding or retracting.
     *
     * @param expanding true if expanding, false if retracting
     */
    void setExpanding(boolean expanding);
}
