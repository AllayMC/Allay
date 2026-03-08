package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.joml.Vector3ic;

import java.util.List;
import java.util.Map;

/**
 * Called when a piston extends or retracts.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class BlockPistonEvent extends BlockEvent implements CancellableEvent {
    /**
     * The direction the piston is pushing/pulling.
     */
    protected BlockFace direction;

    /**
     * The list of block positions that will be moved.
     */
    protected List<Vector3ic> blocksToMove;

    /**
     * The list of block positions that will be destroyed.
     */
    protected List<Vector3ic> blocksToDestroy;

    /**
     * The original block states of the blocks being moved.
     * Key is the position, value is the block state.
     */
    protected Map<Vector3ic, BlockState> originalStates;

    /**
     * Whether the piston is extending (true) or retracting (false).
     */
    protected boolean extending;

    /**
     * Whether the piston is sticky.
     */
    protected boolean sticky;

    public BlockPistonEvent(
            Block pistonBlock,
            BlockFace direction,
            List<Vector3ic> blocksToMove,
            List<Vector3ic> blocksToDestroy,
            Map<Vector3ic, BlockState> originalStates,
            boolean extending,
            boolean sticky
    ) {
        super(pistonBlock);
        this.direction = direction;
        this.blocksToMove = blocksToMove;
        this.blocksToDestroy = blocksToDestroy;
        this.originalStates = originalStates;
        this.extending = extending;
        this.sticky = sticky;
    }
}
