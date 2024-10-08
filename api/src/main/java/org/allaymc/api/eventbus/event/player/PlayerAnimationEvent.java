package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;

/**
 * @author daoge_cmd
 */
@Getter
public class PlayerAnimationEvent extends PlayerEvent implements CancellableEvent {
    protected AnimatePacket.Action action;
    protected float rowingTime;

    public PlayerAnimationEvent(EntityPlayer player, AnimatePacket.Action action, float rowingTime) {
        super(player);
        this.action = action;
        this.rowingTime = rowingTime;
    }
}
