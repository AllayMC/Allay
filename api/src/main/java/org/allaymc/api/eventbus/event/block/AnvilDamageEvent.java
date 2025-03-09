package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author IWareQ
 */
@Setter
@Getter
public class AnvilDamageEvent extends BlockEvent implements CancellableEvent {
    protected BlockState newState;

    public AnvilDamageEvent(BlockStateWithPos blockState, BlockState newState) {
        super(blockState);
        this.newState = newState;
    }
}
