package org.allaymc.api.eventbus.event.server.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.PlayerEvent;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
public class PlayerAnimationEvent extends PlayerEvent implements CancellableEvent {

    protected AnimatePacket.Action action;
    protected float rowingTime;

    public PlayerAnimationEvent(EntityPlayer player, AnimatePacket.Action action, float rowingTime) {
        super(player);
        this.action = action;
        this.rowingTime = rowingTime;
    }
}
