package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.block.poi.BlockStateWithPos;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Allay Project 2024/8/16
 *
 * @author daoge_cmd
 */
@Getter
public class SignWaxEvent extends BlockEvent implements CancellableEvent {
    protected EntityPlayer player;

    public SignWaxEvent(BlockStateWithPos block, EntityPlayer player) {
        super(block);
        this.player = player;
    }
}
