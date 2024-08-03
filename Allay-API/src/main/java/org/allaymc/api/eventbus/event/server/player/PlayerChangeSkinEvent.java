package org.allaymc.api.eventbus.event.server.player;

import lombok.Getter;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.PlayerEvent;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerChangeSkinEvent extends PlayerEvent implements CancellableEvent {

    protected Skin oldSkin;
    protected Skin newSkin;

    public PlayerChangeSkinEvent(EntityPlayer player, Skin oldSkin, Skin newSkin) {
        super(player);
        this.oldSkin = oldSkin;
        this.newSkin = newSkin;
    }
}
