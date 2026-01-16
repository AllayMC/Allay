package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when an anvil is damaged after being used.
 *
 * @author IWareQ
 */
@Setter
@Getter
@CallerThread(ThreadType.DIMENSION)
public class AnvilDamageEvent extends BlockEvent implements CancellableEvent {
    protected BlockState newState;

    public AnvilDamageEvent(Block block, BlockState newState) {
        super(block);
        this.newState = newState;
    }
}
