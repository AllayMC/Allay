package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code AnvilDamageEvent} represents the proposed damage transition after an
 * anvil is used for a noncreative repair. Handlers can cancel the transition or
 * replace {@code newState}; the repair processor applies the final state while
 * continuing the remaining repair costs and sounds.
 *
 * @author IWareQ
 */
@Setter
@Getter
@CallerThread(ThreadType.DIMENSION)
public class AnvilDamageEvent extends BlockEvent implements CancellableEvent {
    /**
     * {@code newState} is the proposed replacement state. The repair processor
     * writes the final value after dispatch, so handlers must retain a usable
     * block state.
     */
    protected BlockState newState;

    /**
     * Creates an anvil damage proposal retaining the supplied block and
     * replacement state. Construction stores the references for later dispatch;
     * handlers can replace {@code newState} before the repair processor applies
     * the transition.
     */
    public AnvilDamageEvent(Block block, BlockState newState) {
        super(block);
        this.newState = newState;
    }
}
