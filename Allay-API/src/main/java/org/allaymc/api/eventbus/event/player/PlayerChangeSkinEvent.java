package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd | Dhaiven
 */
@Getter
public class PlayerChangeSkinEvent extends PlayerEvent implements CancellableEvent {
    protected Skin oldSkin;
    @Setter
    protected Skin newSkin;

    public PlayerChangeSkinEvent(EntityPlayer player, Skin oldSkin, Skin newSkin) {
        super(player);
        this.oldSkin = oldSkin;
        this.newSkin = newSkin;
    }
}
