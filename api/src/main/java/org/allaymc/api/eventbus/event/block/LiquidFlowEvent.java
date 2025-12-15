package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.joml.Vector3ic;

/**
 * Represent the flowing of a liquid from one block position
 * from into another block position into. The liquid that will replace the
 * block is also passed. This replaced block might also be a liquid.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class LiquidFlowEvent extends BlockEvent implements CancellableEvent {
    /**
     * The block position the liquid is flowing into.
     * <p>
     * It may be same to the pos which the liquid flowing from
     * if this event represents the forming of a source liquid block.
     */
    protected Vector3ic into;

    /**
     * The block state that will be replaced.
     */
    protected BlockState replacedBlockState;

    /**
     * Whether the liquid is forming a source block.
     */
    protected boolean isFormingSource;

    public LiquidFlowEvent(Block block, Vector3ic into, BlockState replacedBlockState) {
        this(block, into, replacedBlockState, false);
    }

    public LiquidFlowEvent(Block block, Vector3ic into, BlockState replacedBlockState, boolean isFormingSource) {
        super(block);
        this.into = into;
        this.replacedBlockState = replacedBlockState;
        this.isFormingSource = isFormingSource;
    }
}
