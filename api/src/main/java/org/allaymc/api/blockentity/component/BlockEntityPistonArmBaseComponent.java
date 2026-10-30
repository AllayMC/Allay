package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.joml.Vector3ic;

import java.util.List;
import java.util.Map;

/**
 * {@code BlockEntityPistonArmBaseComponent} exposes piston animation phases,
 * attached source positions, and movement preparation. Facing and sticky state
 * describe the piston, while progress and state codes describe the current
 * animation. The server restores moved blocks when its paired progress values
 * converge at an endpoint.
 *
 * <p>
 * Movement callers first prepare extension or retraction, which broadcasts the
 * initial arm state, then create the temporary moving-block entities, and
 * finally call {@code startMoving}. Continued ticking advances the animation;
 * {@code startMoving} does not schedule ticks itself. The attached-position
 * list is copied as a container, while position objects remain shared with the
 * supplied movement description.
 *
 * @author daoge_cmd
 */
public interface BlockEntityPistonArmBaseComponent extends BlockEntityBaseComponent {

    /**
     * {@code MOVE_STEP} advances both animation phases by one quarter per tick;
     * the server finishes only after both clamped phases reach the same
     * endpoint.
     */
    float MOVE_STEP = 0.25f;

    /**
     * Returns the stored direction used for the piston's outward movement.
     */
    BlockFace getFacing();

    /**
     * Stores the direction used by later animation and restoration operations.
     * The server retains the supplied enum without a viewer update or
     * validation; callers provide a non-{@code null} direction before movement
     * or saving.
     */
    void setFacing(BlockFace facing);

    /**
     * Returns whether the retained piston configuration marks this piston
     * sticky.
     */
    boolean isSticky();

    /**
     * Replaces the retained sticky-piston configuration flag without starting
     * an animation.
     */
    void setSticky(boolean sticky);

    /**
     * Returns whether the prepared animation represents outward rather than
     * retracting movement.
     */
    boolean isExtending();

    /**
     * Returns the current main animation phase used for collision and
     * persistence.
     */
    float getProgress();

    /**
     * Returns the second animation phase used to derive entity displacement.
     * The server advances it alongside {@code progress}; {@code startMoving}
     * places it one step outside the normal interval, so it is not simply the
     * previous tick's value.
     */
    float getLastProgress();

    /**
     * Returns whether this component currently marks animation processing
     * finished. The server changes this flag through preparation and
     * completion; loading NBT alone does not reconstruct the flag from
     * persisted state codes.
     */
    boolean isFinished();

    /**
     * Returns a copied list of attached source-position references. The server
     * copies the list container but retains its coordinate objects, so callers
     * can modify the returned list independently while mutable positions remain
     * shared.
     */
    List<Vector3ic> getAttachedBlocks();

    /**
     * Prepares and broadcasts an extension before moving-block entities are
     * created. The server shallowly copies source positions and original
     * states, resets both phases to zero, and leaves temporary-block creation
     * to the caller.
     */
    void preExtending(List<Vector3ic> blocksToMove, Map<Vector3ic, BlockState> originalStates);

    /**
     * Prepares and broadcasts a retraction before moving-block entities are
     * created. The server shallowly copies source positions and original
     * states, resets both phases to one, and leaves temporary-block creation to
     * the caller.
     */
    void preRetracting(List<Vector3ic> blocksToMove, Map<Vector3ic, BlockState> originalStates);

    /**
     * Separates animation phases after the caller has created the moving
     * blocks. The server adjusts only the second phase; callers must prepare
     * movement first and continue normal ticking because this operation
     * schedules no work.
     */
    void startMoving();

    /**
     * Returns whether the piston currently remembers an active redstone input.
     */
    boolean isPowered();

    /**
     * Replaces the remembered redstone-input flag without directly starting
     * piston movement.
     */
    void setPowered(boolean powered);

    /**
     * Returns the retained piston state code used by NBT. Ordinary transitions
     * use {@code 0} for retracted, {@code 1} for extending, {@code 2} for
     * extended, and {@code 3} for retracting.
     */
    byte getState();

    /**
     * Returns the paired target-state code retained for piston NBT
     * serialization.
     */
    byte getNewState();
}
