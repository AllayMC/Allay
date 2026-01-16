package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a player waxes a sign to prevent further editing.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class SignWaxEvent extends BlockEvent implements CancellableEvent {
    protected EntityPlayer player;

    public SignWaxEvent(Block block, EntityPlayer player) {
        super(block);
        this.player = player;
    }
}
