package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.cloudburstmc.protocol.bedrock.data.GameType;

/**
 * Allay Project 2024/8/3
 *
 * @author daoge_cmd | Dhaiven
 */
@Getter
public class PlayerGameTypeChangeEvent extends PlayerEvent implements CancellableEvent {
    protected GameType oldGameType;
    @Setter
    protected GameType newGameType;

    public PlayerGameTypeChangeEvent(EntityPlayer player, GameType oldGameType, GameType newGameType) {
        super(player);
        this.oldGameType = oldGameType;
        this.newGameType = newGameType;
    }
}
