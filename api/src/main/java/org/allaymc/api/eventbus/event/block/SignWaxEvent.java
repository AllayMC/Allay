package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@Getter
public class SignWaxEvent extends BlockEvent implements CancellableEvent {
    protected EntityPlayer player;

    public SignWaxEvent(BlockStateWithPos blockState, EntityPlayer player) {
        super(blockState);
        this.player = player;
    }
}
