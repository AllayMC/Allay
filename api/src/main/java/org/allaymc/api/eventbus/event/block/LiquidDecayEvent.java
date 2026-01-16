package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Represent the decaying of a liquid block at a position.
 * liquid decaying happens when there is no liquid that can serve as the
 * supply liquid block neighbouring it. The state of the liquid before and after
 * the decaying is passed. The liquid after is nil if the liquid is
 * completely removed as a result of the decay.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class LiquidDecayEvent extends BlockEvent implements CancellableEvent {

    protected BlockState newBlockState;

    public LiquidDecayEvent(Block block, BlockState newBlockState) {
        super(block);
        this.newBlockState = newBlockState;
    }
}
