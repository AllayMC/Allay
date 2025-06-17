package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.cloudburstmc.protocol.bedrock.data.skin.SerializedSkin;

/**
 * @author daoge_cmd | Dhaiven
 */
@Getter
public class PlayerChangeSkinEvent extends PlayerEvent implements CancellableEvent {
    protected SerializedSkin oldSkin;
    @Setter
    protected SerializedSkin newSkin;

    public PlayerChangeSkinEvent(EntityPlayer player, SerializedSkin oldSkin, SerializedSkin newSkin) {
        super(player);
        this.oldSkin = oldSkin;
        this.newSkin = newSkin;
    }
}
