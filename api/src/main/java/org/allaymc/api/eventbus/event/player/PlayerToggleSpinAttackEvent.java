package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Called when a player starts or stops a spin attack (riptide).
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerToggleSpinAttackEvent extends PlayerEvent {
    protected boolean value;

    public PlayerToggleSpinAttackEvent(EntityPlayer player, boolean value) {
        super(player);
        this.value = value;
    }
}
