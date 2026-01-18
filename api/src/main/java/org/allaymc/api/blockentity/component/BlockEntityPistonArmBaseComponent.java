package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.joml.Vector3ic;

import java.util.List;
import java.util.Map;

/**
 * Base component interface for piston arm block entities.
 *
 * @author daoge_cmd
 */
public interface BlockEntityPistonArmBaseComponent extends BlockEntityBaseComponent {

    /**
     * The movement step per tick (0.25f = 2 game ticks to complete).
     */
    float MOVE_STEP = 0.25f;

    /**
     * Get the facing direction of the piston.
     *
     * @return the facing direction
     */
    BlockFace getFacing();

    /**
     * Set the facing direction of the piston.
     *
     * @param facing the facing direction
     */
    void setFacing(BlockFace facing);

    /**
     * Check if this piston is sticky.
     *
     * @return true if sticky
     */
    boolean isSticky();

    /**
     * Set whether this piston is sticky.
     *
     * @param sticky true if sticky
     */
    void setSticky(boolean sticky);

    /**
     * Check if the piston is currently extending.
     *
     * @return true if extending
     */
    boolean isExtending();

    /**
     * Get the current progress of the piston animation (0.0 to 1.0).
     *
     * @return the progress
     */
    float getProgress();

    /**
     * Get the previous tick's progress value.
     *
     * @return the last progress
     */
    float getLastProgress();

    /**
     * Check if the piston animation has finished.
     *
     * @return true if finished
     */
    boolean isFinished();

    /**
     * Get the list of attached block positions being moved.
     *
     * @return the attached block positions
     */
    List<Vector3ic> getAttachedBlocks();

    /**
     * Prepare for extending animation. This sends initial state to client
     * and must be called BEFORE creating MOVING_BLOCK entities.
     *
     * @param blocksToMove    the blocks to move
     * @param originalStates  the original states of the blocks
     */
    void preExtending(List<Vector3ic> blocksToMove, Map<Vector3ic, BlockState> originalStates);

    /**
     * Prepare for retracting animation. This sends initial state to client
     * and must be called BEFORE creating MOVING_BLOCK entities.
     *
     * @param blocksToMove   the blocks to move (for sticky piston)
     * @param originalStates the original states of the blocks
     */
    void preRetracting(List<Vector3ic> blocksToMove, Map<Vector3ic, BlockState> originalStates);

    /**
     * Start the actual movement animation after MOVING_BLOCK entities are created.
     * This adjusts lastProgress and schedules tick updates.
     */
    void startMoving();

    /**
     * Get the current state of the piston (for NBT).
     * 0 = retracted, 1 = extending, 2 = extended, 3 = retracting
     *
     * @return the state
     */
    byte getState();

    /**
     * Get the new state of the piston (for NBT).
     *
     * @return the new state
     */
    byte getNewState();
}
