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
 * {@code BlockPistonEvent} describes a proposed piston extension or retraction before
 * the server changes the affected blocks. The piston caller dispatches this event on
 * the dimension thread and abandons movement when cancellation is observed immediately
 * after dispatch returns. Retraction can still proceed with empty movement collections
 * when a sticky pull cannot be calculated.
 *
 * <p>
 * The constructor retains the supplied lists and map, and the generated getters expose
 * those same references. Synchronous listeners finish before the piston caller checks
 * cancellation and consumes the plan. Asynchronous handlers are submitted without being
 * awaited, so their later cancellation can miss that check and their collection edits
 * can race with execution.
 *
 * <p>
 * Listeners must complete plan edits synchronously and keep positions consistent with
 * {@code originalStates}. Missing source states skip affected destruction entries or
 * destination placements; a movement source may already have been cleared when
 * destination placement is skipped.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class BlockPistonEvent extends BlockEvent implements CancellableEvent {
    /**
     * The piston facing direction for both extension and retraction.
     */
    protected BlockFace direction;

    /**
     * {@code blocksToMove} holds source positions in the movement plan. The getter
     * exposes the retained list, which the piston caller reads after event dispatch.
     */
    protected List<Vector3ic> blocksToMove;

    /**
     * {@code blocksToDestroy} holds positions selected for breaking. The getter exposes
     * the retained list, and execution only breaks entries also present in
     * {@code originalStates}.
     */
    protected List<Vector3ic> blocksToDestroy;

    /**
     * {@code originalStates} maps planned source positions to their original states.
     * The getter exposes the retained map, which supplies the states placed at movement
     * destinations.
     */
    protected Map<Vector3ic, BlockState> originalStates;

    /**
     * Whether this event describes extension rather than retraction.
     */
    protected boolean extending;

    /**
     * Whether the acting piston uses sticky pulling behavior.
     */
    protected boolean sticky;

    /**
     * Creates a piston event retaining the supplied movement collections without
     * copying them. Synchronous listeners can cancel the proposal or edit its plan
     * before the caller's immediate cancellation check; asynchronous work is not
     * awaited and can race with execution. {@code direction} remains the piston facing
     * during retraction.
     */
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
